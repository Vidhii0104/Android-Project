package com.example.askme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

//import com.example.askme.R;
import com.google.android.material.bottomnavigation.*;

import hotchemi.android.rate.AppRate;

public class QuestionAnswer extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_appbar, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppRate.with(this)
                .setInstallDays(1)
                .setLaunchTimes(3)
                .setRemindInterval(2)
                .monitor();

        AppRate.showRateDialogIfMeetsConditions(this);
        AppRate.with(this).showRateDialog(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answer);
        //Initialize and assign variables
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        //Set home selected
        bottomNavigationView.setSelectedItemId(R.id.question);
        //perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){

                    case R.id.recyclerview_video:
                        startActivity(new Intent(getApplicationContext(), VideoActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.blog_list:
                        startActivity(new Intent(getApplicationContext()
                                ,Blogs_Main.class));
                        overridePendingTransition(0,0);
                        return  true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,Home.class));
                        overridePendingTransition(0,0);
                        return  true;

                    case R.id.question:
                        /*startActivity(new Intent(getApplicationContext(), VideoActivity.class));
                        overridePendingTransition(0, 0);*/

                        return  true;
                }
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_about:
                startActivity(new Intent(QuestionAnswer.this,PostActivity.class));
                overridePendingTransition(0, 0);
                return true;

            case R.id.action_contact:
                startActivity(new Intent(getApplicationContext(),Contact.class));
                Toast.makeText(this, "About is clicked.", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_settings:
                Toast.makeText(this, "Settings is clicked.", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_logout:
                startActivity(new Intent(getApplicationContext(), Logout.class));
                Toast.makeText(this, "Logout is clicked.", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
