package com.example.askme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.askme.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //super.onCreate(savedInstanceState);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Initialize and assign variables
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        //Set home selected
        bottomNavigationView.setSelectedItemId(R.id.dashboard);
        //perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.dashboard:
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
                    case R.id.polls:
                        startActivity(new Intent(getApplicationContext()
                                ,Polls.class));
                        overridePendingTransition(0,0);
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