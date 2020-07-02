package com.example.askme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

import static androidx.core.content.ContextCompat.startActivity;

public class Logout extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //creating app bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }
    public void logout (View view)
    {
        FirebaseAuth.getInstance().signOut();
        //
        finish();
        Intent logoutIntent = new Intent(Logout.this,Login.class);
        startActivity(logoutIntent);


    }
}
