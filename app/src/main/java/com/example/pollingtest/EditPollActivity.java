package com.example.pollingtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class EditPollActivity extends AppCompatActivity {

    // creating variables for our edit text, firebase database,
    // database reference, Poll rv modal,progress bar.
    private TextInputEditText PollNameEdt, PollDescEdt, PollImgEdt,Option1Edt,Option2Edt,Option3Edt;
    private Button updatePollBtn,deletePollBtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private PollRVModal pollRVModal;
    private ProgressBar loadingPB;
    // creating a string for our Poll id.
    private String pollID,votes1,votes2,votes3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_poll);
        // initializing all our variables on below line.
        updatePollBtn = findViewById(R.id.idBtnUpdate);
        deletePollBtn = findViewById(R.id.idBtnDeletePoll);
        PollNameEdt = findViewById(R.id.idEdtPollName);
        PollDescEdt = findViewById(R.id.idEdtPollDescription);
        PollImgEdt = findViewById(R.id.idEdtPollImageLink);
        Option1Edt= findViewById(R.id.idEdtOption1);
        Option2Edt= findViewById(R.id.idEdtVoteOption2);
        Option3Edt= findViewById(R.id.idEdtOption3);
        loadingPB = findViewById(R.id.idPBLoading);
        votes1="0";
        votes2="0";
        votes3="0";
        firebaseDatabase = FirebaseDatabase.getInstance();
        // on below line we are getting our modal class on which we have passed.
        pollRVModal = getIntent().getParcelableExtra("poll");

        if (pollRVModal != null) {
            // on below line we are setting data to our edit text from our modal class.
            PollNameEdt.setText(pollRVModal.getPollName());
            PollImgEdt.setText(pollRVModal.getPollImg());
            PollDescEdt.setText(pollRVModal.getPollDescription());
            Option1Edt.setText(pollRVModal.getOption1());
            Option2Edt.setText(pollRVModal.getOption2());
            Option3Edt.setText(pollRVModal.getOption3());
            pollID = pollRVModal.getPollId();
        }
        // on below line we are initialing our database reference and we are adding a child as our Poll id.
        databaseReference = firebaseDatabase.getReference("polls").child(pollID);

        // on below line we are adding click listener for our update Poll button.
        updatePollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are making our progress bar as visible.
                loadingPB.setVisibility(View.VISIBLE);
                // on below line we are getting data from our edit text.
                String pollName = PollNameEdt.getText().toString();
                String pollDesc = PollDescEdt.getText().toString();
                String pollImg = PollImgEdt.getText().toString();
                String option1= Option1Edt.getText().toString();
                String option2= Option2Edt.getText().toString();
                String option3= Option3Edt.getText().toString();
                // on below line we are creating a map for
                // passing a data using key and value pair.
                Map<String, Object> map = new HashMap<>();
                map.put("pollName", pollName);
                map.put("pollDescription", pollDesc);
                map.put("pollImg", pollImg);
                map.put("option1", option1);
                map.put("option2", option2);
                map.put("option3", option3);
                map.put("votes1", votes1);
                map.put("votes3", votes3);
                map.put("votes2", votes2);
                map.put("option2", option2);
                map.put("option3", option3);
                map.put("pollId", pollID);

                // on below line we are calling a database reference on
                // add value event listener and on data change method
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // making progress bar visibility as gone.
                        loadingPB.setVisibility(View.GONE);
                        // adding a map to our database.
                        databaseReference.updateChildren(map);
                        // on below line we are displaying a toast message.
                        Toast.makeText(EditPollActivity.this, "Poll Updated..", Toast.LENGTH_SHORT).show();
                        // opening a new activity after updating our coarse.
                        startActivity(new Intent(EditPollActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // displaying a failure message on toast.
                        Toast.makeText(EditPollActivity.this, "Fail to update Poll..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // adding a click listener for our delete Poll button.
        deletePollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete a Poll.
                deletePoll();
            }
        });

    }

    private void deletePoll() {
        // on below line calling a method to delete the Poll.
        databaseReference.removeValue();
        // displaying a toast message on below line.
        Toast.makeText(this, "Poll Deleted..", Toast.LENGTH_SHORT).show();
        // opening a main activity on below line.
        startActivity(new Intent(EditPollActivity.this, MainActivity.class));
    }
}