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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddPollActivity extends AppCompatActivity {

    // creating variables for our button, edit text,
    // firebase database, database reference, progress bar.
    private Button addPollBtn;
    private TextInputEditText pollNameEdt, pollDescEdt, pollImgEdt, Option1Edt, Option2Edt, Option3Edt;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String homeID, retrieveID, userID;
    private FirebaseAuth mAuth;
    private ProgressBar loadingPB;
    private String pollID;
    private Integer votes1, votes2, votes3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_poll);
        // initializing all our variables.
        addPollBtn = findViewById(R.id.idBtnAdd);
        pollNameEdt = findViewById(R.id.idEdtPollName);
        pollDescEdt = findViewById(R.id.idEdtPollDescription);
        pollImgEdt = findViewById(R.id.idEdtPollImageLink);
        Option1Edt = findViewById(R.id.idEdtOption1);
        Option2Edt = findViewById(R.id.idEdtOption2);
        Option3Edt = findViewById(R.id.idEdtOption3);
        loadingPB = findViewById(R.id.idPBLoading);
        votes1 = 0;
        votes2 = 0;
        votes3 = 0;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser newUser = mAuth.getCurrentUser();


        //Creates a string called uId and ties it to newUser.getUid that will retrieve the users generated ID.
        String uId = newUser.getUid();
        userID = uId;
        System.out.println(uId);
        System.out.println(userID);
        firebaseDatabase = FirebaseDatabase.getInstance("https://polling-3351e-default-rtdb.europe-west1.firebasedatabase.app/");
        // on below line creating our database reference.
        databaseReference = firebaseDatabase.getReference();
        getData();
        addPollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPoll();
            }
        });

    }

    private void addPoll() {

                loadingPB.setVisibility(View.VISIBLE);
                // getting data from our edit text.
                String pollName = pollNameEdt.getText().toString();
                String pollDesc = pollDescEdt.getText().toString();
                String pollImg = pollImgEdt.getText().toString();
                String option1 = Option1Edt.getText().toString();
                String option2 = Option2Edt.getText().toString();
                String option3 = Option3Edt.getText().toString();
                pollID = pollName;

                // on below line we are passing all data to our modal class.
                PollRVModal pollRVModal = new PollRVModal(pollID, pollName, pollDesc, pollImg, option1, option2, option3, votes1, votes2, votes3);
                // on below line we are calling a add value event
                // to pass data to firebase database.
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // on below line we are setting data in our firebase database.
                        // databaseReference.child(pollID).setValue(pollRVModal);
                        //databaseReference.child(pollID).setValue(pollRVModal);
                        databaseReference.child("Homes").child(retrieveID).child("polls").child(pollID).setValue(pollRVModal);

                        // displaying a toast message.
                        Toast.makeText(AddPollActivity.this, "poll Added..", Toast.LENGTH_SHORT).show();
                        // starting a main activity.
                        startActivity(new Intent(AddPollActivity.this, PollActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // displaying a failure message on below line.
                        Toast.makeText(AddPollActivity.this, "Fail to add poll..", Toast.LENGTH_SHORT).show();
                    }
                });
            }



    private void getData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Creates a string called uId and ties it to newUser.getUid that will retrieve the users generated ID.

                homeID = snapshot.child("NewUsers").child(userID).child("home").getValue(String.class);

                //  after getting the value we are setting
                // our value to our text view in below line.
                retrieveID = homeID;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
