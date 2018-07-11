package com.example.user16.demofirebaselogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.user16.demofirebaselogin.pojo.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    User user;
    TextView tv_name;
    DatabaseReference reference;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseApp.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        tv_name = findViewById(R.id.tv_name);
        user = new User();
        user.setGender_name("Male");
        user.setUser_name("Don");
        //writeNewUser();

    }

    @Override
    public void onStart() {
        super.onStart();
        readfirebasedata();
    }

    private void readfirebasedata() {
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        mDatabase.child("1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String fullName = dataSnapshot.getValue(User.class).getUser_name();
                Log.d("Fullname", fullName);
                tv_name.setText(fullName);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void writeNewUser() {
        mDatabase.child("users").child("1").setValue(user);
    }

}