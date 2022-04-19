package com.example.pollingtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

public class VotingActivity extends AppCompatActivity {

    private String Option1Edt, Option2Edt, Option3Edt;
    private PollRVModal pollRVModal;
    private TextView opt1,opt2,opt3;
    private String pollID;
    private String homeID,retrieveID,userID;
    private Integer votes1, votes2, votes3;
    private Button voteBtn1, voteBtn2, voteBtn3;
    private FirebaseAuth mAuth;
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
        opt2= (TextView)findViewById(R.id.opt2);
        opt3= (TextView)findViewById(R.id.opt3);
        loadingPB = findViewById(R.id.idPBLoading);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser newUser=mAuth.getCurrentUser();

        //Creates a string called uId and ties it to newUser.getUid that will retrieve the users generated ID.
        String uId=newUser.getUid();
        userID=uId;

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        // on below line we are getting our modal class on which we have passed.
        pollRVModal = getIntent().getParcelableExtra("poll");
        // on below line we are initialing our database reference and we are adding a child as our Poll id.


        if (pollRVModal != null) {
            // on below line we are setting data to our edit text from our modal class.
            opt1.setText(pollRVModal.getOption1());
            opt2.setText(pollRVModal.getOption2());
            opt3.setText(pollRVModal.getOption3());
            pollID = pollRVModal.getPollId();
        }
        // adding click listener for our vote buttons.
        getData();

        voteBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getVotes1();
            }
        });
        voteBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getVotes2();
            }
        });
        voteBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getVotes3();
            }
        });

    }
    private void getData(){

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Creates a string called uId and ties it to newUser.getUid that will retrieve the users generated ID.

                homeID = snapshot.child("NewUsers").child(userID).child("home").getValue(String.class);

                // after getting the value we are setting
                // our value to our text view in below line.
                retrieveID=homeID;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                // Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getVotes1() {
        databaseReference = firebaseDatabase.getInstance().getReference("Homes").child(retrieveID).child("polls").child(pollID);
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
        loadingPB.setVisibility(View.VISIBLE);
        databaseReference.child("votes1").setValue(ServerValue.increment(1));

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // making progress bar visibility as gone.
                loadingPB.setVisibility(View.GONE);
                // adding a map to our database.

                // on below line we are displaying a toast message.
                Toast.makeText(VotingActivity.this, "Votes Added..", Toast.LENGTH_SHORT).show();
                // opening a new activity after updating our votes.
                startActivity(new Intent(VotingActivity.this, PollActivity.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // displaying a failure message on toast.
                Toast.makeText(VotingActivity.this, "Fail to vote..", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getVotes2() {
        databaseReference = firebaseDatabase.getInstance().getReference("Homes").child(retrieveID).child("polls").child(pollID);
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
        loadingPB.setVisibility(View.VISIBLE);
        databaseReference.child("votes2").setValue(ServerValue.increment(1));

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // making progress bar visibility as gone.
                loadingPB.setVisibility(View.GONE);
                // adding a map to our database.

                // on below line we are displaying a toast message.
                Toast.makeText(VotingActivity.this, "Votes Added..", Toast.LENGTH_SHORT).show();
                // opening a new activity after updating our votes.
                startActivity(new Intent(VotingActivity.this, PollActivity.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // displaying a failure message on toast.
                Toast.makeText(VotingActivity.this, "Fail to vote..", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getVotes3() {
        databaseReference = firebaseDatabase.getInstance().getReference("Homes").child(retrieveID).child("polls").child(pollID);
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
        loadingPB.setVisibility(View.VISIBLE);
        databaseReference.child("votes3").setValue(ServerValue.increment(1));

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // making progress bar visibility as gone.
                loadingPB.setVisibility(View.GONE);
                // adding a map to our database.

                // on below line we are displaying a toast message.
                Toast.makeText(VotingActivity.this, "Votes Added..", Toast.LENGTH_SHORT).show();
                // opening a new activity after updating our votes.
                startActivity(new Intent(VotingActivity.this, PollActivity.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // displaying a failure message on toast.
                Toast.makeText(VotingActivity.this, "Fail to vote..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}