package com.example.pollingtest.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pollingtest.GroceryList.GroceryActivity;
import com.example.pollingtest.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChooseHouseActivity extends AppCompatActivity {

    private Button createButton;
    private Button joinButton;
    private FirebaseDatabase newDatabase;
    private DatabaseReference newReference;
    String houseID;
    private FirebaseAuth newAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_house);

        createButton=findViewById(R.id.createHouse);
        joinButton=findViewById(R.id.joinHouse);
        newDatabase= FirebaseDatabase.getInstance("https://polling-3351e-default-rtdb.europe-west1.firebasedatabase.app/");
        newReference= newDatabase.getReference();
        //Returns an instance of FirebaseAuth and ties it to newAuth
        newAuth=FirebaseAuth.getInstance();
        //Creates a FirebaseUser class called newUser and ties it to newAuth.getCurrentUser that will retrieve the current users credentials
        FirebaseUser newUser=newAuth.getCurrentUser();

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //When clicked, the dialogBox view will be shown
            public void onClick(View view) {
                houseID=newReference.push().getKey();
                newReference.child("Homes").child(houseID).child("blank").setValue("");

                String uId=newUser.getUid();
                newReference.child("NewUsers").child(uId).child("home").setValue(houseID);
                startActivity(new Intent(getApplicationContext(), GroceryActivity.class));
            }
        });



    }


}