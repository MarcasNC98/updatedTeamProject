package com.example.pollingtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class VotingActivity extends AppCompatActivity {

    private String Option1Edt, Option2Edt, Option3Edt;
    private PollRVModal pollRVModal;
    private TextView opt1,opt2,opt3;
    private String pollID;
    private Integer votes1, votes2, votes3;
    private Button voteBtn1, voteBtn2, voteBtn3;


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference, DatabaseRef;
    private ProgressBar loadingPB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);
        // initializing all our variables.
        votes1 = 0;
        votes2 = 0;
        votes3 = 0;
        voteBtn1 = findViewById(R.id.idBtnVote1);
        voteBtn2 = findViewById(R.id.idBtnVote2);
        voteBtn3 = findViewById(R.id.idBtnVote3);
        opt1= (TextView)findViewById(R.id.opt1);
        System.out.println(opt1);
        opt2= (TextView)findViewById(R.id.opt2);
        opt3= (TextView)findViewById(R.id.opt3);
        loadingPB = findViewById(R.id.idPBLoading);


        firebaseDatabase = FirebaseDatabase.getInstance();
        // on below line we are getting our modal class on which we have passed.
        pollRVModal = getIntent().getParcelableExtra("poll");
        // on below line we are initialing our database reference and we are adding a child as our Poll id.


        if (pollRVModal != null) {
            // on below line we are setting data to our edit text from our modal class.
            String Choice1 = dataSnapshot.child(pollID).child("option1").getValue;
            opt1.setText();

            opt2.setText(pollRVModal.getOption2());
            opt3.setText(pollRVModal.getOption3());
            voteBtn2.setText(pollRVModal.getOption2());
            voteBtn3.setText(pollRVModal.getOption3());
            pollID = pollRVModal.getPollId();
        }
        databaseReference = firebaseDatabase.getReference("polls").child(pollID);
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
        // adding click listener for our vote buttons.

        voteBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingPB.setVisibility(View.VISIBLE);
                databaseReference.child("votes1").setValue(ServerValue.increment(1));
                //votes1++;
                // on below line we are creating a map for
                // passing a data using key and value pair.
                //Map<String, Object> map = new HashMap<>();

                // map.put("votes1", votes1);

                databaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // making progress bar visibility as gone.
                        loadingPB.setVisibility(View.GONE);
                        // adding a map to our database.
                        // databaseReference.updateChildren(map);
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

        });
        voteBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingPB.setVisibility(View.VISIBLE);
                databaseReference.child("votes2").setValue(ServerValue.increment(1));

                databaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // making progress bar visibility as gone.
                        loadingPB.setVisibility(View.GONE);
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

        });
        voteBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingPB.setVisibility(View.VISIBLE);
                databaseReference.child("votes3").setValue(ServerValue.increment(1));
                databaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // making progress bar visibility as gone.
                        loadingPB.setVisibility(View.GONE);
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

        });



    }
}