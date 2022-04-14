package com.example.pollingtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class VotingActivity extends AppCompatActivity {

    private TextInputEditText PollNameEdt, PollDescEdt, PollImgEdt, voteOption1Edt, voteOption2Edt, voteOption3Edt;
    private PollRVModal pollRVModal;
    private String pollID, votes1, votes2, votes3;
    private Button voteBtn;
    private CheckBox checkBox, checkBox2, checkBox3;
    private Integer checkCounter, checkCounter2, checkCounter3,Counter,Counter2,Counter3;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ProgressBar loadingPB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);
        // initializing all our variables.
        PollNameEdt = findViewById(R.id.idEdtPollName);
        PollDescEdt = findViewById(R.id.idEdtPollDescription);
        PollImgEdt = findViewById(R.id.idEdtPollImageLink);
        voteBtn = findViewById(R.id.idBtnVote);
        checkBox = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        loadingPB = findViewById(R.id.idPBLoading);
        voteOption1Edt = findViewById(R.id.idEdtVoteOption1);
        voteOption2Edt = findViewById(R.id.idEdtVoteOption2);
        voteOption3Edt = findViewById(R.id.idEdtVoteOption3);
        votes1 = "0";
        votes2 = "0";
        votes3 = "0";
        checkCounter = 0;
        checkCounter2 = 0;
        checkCounter3 = 0;
        firebaseDatabase = FirebaseDatabase.getInstance();
        // on below line we are getting our modal class on which we have passed.
        pollRVModal = getIntent().getParcelableExtra("poll");
        // on below line we are initialing our database reference and we are adding a child as our Poll id.


        if (pollRVModal != null) {
            // on below line we are setting data to our edit text from our modal class.

            checkBox.setText(pollRVModal.getOption1());
            checkBox2.setText(pollRVModal.getOption2());
            checkBox3.setText(pollRVModal.getOption3());
            pollID = pollRVModal.getPollId();
        }
        databaseReference = firebaseDatabase.getReference("polls").child(pollID);
        // adding click listener for our vote  button.
        voteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingPB.setVisibility(View.VISIBLE);
               // String pollName = PollNameEdt.getText().toString();
               // String pollDesc = PollDescEdt.getText().toString();
               // String pollImg = PollImgEdt.getText().toString();
              //  String option1= voteOption1Edt.getText().toString();
              //  String option2= voteOption2Edt.getText().toString();
              //  String option3= voteOption3Edt.getText().toString();
                // getting data from our checkbox .

                checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkBox.isChecked()) {
                            checkCounter++;
                            Counter=checkCounter;
                          votes1=Counter.toString();;
                        }
                    }
                });
                checkBox2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkBox2.isChecked()) {
                            checkCounter2++;
                           Counter2=checkCounter2;
                            votes2=Counter2.toString();;
                        }
                    }
                });
                checkBox3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkBox3.isChecked()) {
                            checkCounter3++;
                            Counter3=checkCounter3;
                            votes3=Counter3.toString();;
                        }
                    }
                });
                // on below line we are creating a map for
                // passing a data using key and value pair.
                Map<String, Object> map = new HashMap<>();
               // map.put("pollName", pollName);
              //  map.put("pollDescription", pollDesc);
               // map.put("pollImg", pollImg);
               // map.put("option1", option1);
              //  map.put("option2", option2);
               // map.put("option3", option3);
                map.put("votes1", votes1);
                map.put("votes3", votes3);
                map.put("votes2", votes2);
              //  map.put("option2", option2);
              //  map.put("option3", option3);
                map.put("pollId", pollID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // making progress bar visibility as gone.
                        loadingPB.setVisibility(View.GONE);
                        // adding a map to our database.
                        databaseReference.updateChildren(map);
                        // on below line we are displaying a toast message.
                        Toast.makeText(VotingActivity.this, "Votes Added..", Toast.LENGTH_SHORT).show();
                        // opening a new activity after updating our votes.
                        startActivity(new Intent(VotingActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // displaying a failure message on toast.
                        Toast.makeText(VotingActivity.this, "Fail to vote..", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            ;
        });
    }
}