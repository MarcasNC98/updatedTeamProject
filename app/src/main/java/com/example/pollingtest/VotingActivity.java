package com.example.pollingtest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VotingActivity extends AppCompatActivity {

    private Button voteBtn;
    private CheckBox checkBox, checkBox2, checkBox3;
    private Integer checkCounter,checkCounter2,checkCounter3;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ProgressBar loadingPB;
    private String pollID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);
        // initializing all our variables.
        voteBtn = findViewById(R.id.idBtnVote);
        checkBox = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        loadingPB = findViewById(R.id.idPBLoading);
        checkCounter = 0;
        checkCounter2 = 0;
        checkCounter3 = 0;
        firebaseDatabase = FirebaseDatabase.getInstance();
        // on below line creating our database reference.???*******


        // adding click listener for our vote  button.
        voteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingPB.setVisibility(View.VISIBLE);
                // getting data from our checkbox .

                checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkBox.isChecked()) {
                            checkCounter++;
                        }
                    }
                });
                checkBox2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkBox2.isChecked()) {
                            checkCounter2++;
                        }
                    }
                });
                checkBox3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkBox3.isChecked()) {
                            checkCounter3++;
                        }
                    }
                });
            }
        });
    }
}