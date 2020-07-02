package com.example.askme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.askme.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.askme.R.id.bottom_navigation;
import static com.example.askme.R.id.polls;

public class Polls extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polls);

        //Initialize and assign variables
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(bottom_navigation);
        //Set home selected
        bottomNavigationView.setSelectedItemId(polls);
        //perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext()
                                ,Dashboard.class));
                        overridePendingTransition(0,0);
                        return  true;
                    case R.id.blogs:
                        startActivity(new Intent(getApplicationContext()
                                ,Blog.class));
                        overridePendingTransition(0,0);
                        return  true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,Home.class));
                        overridePendingTransition(0,0);
                        return  true;
                    case polls:
                        return  true;
                    case R.id.question:
                        startActivity(new Intent(getApplicationContext()
                                ,QuestionAnswer.class));
                        overridePendingTransition(0,0);
                        return  true;
                }
                return false;
            }
        });
    }
}
