package com.example.askme;

import android.content.Intent;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

//import com.example.askme.adapter.MainAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import hotchemi.android.rate.AppRate;

public class MainActivity extends AppCompatActivity {
    //CharSequence iCharSequence[]={"Blog","Question","Video"};
    TextView fullName, email, phone;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;


    // @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //creating app bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();


        //Initialize and assign variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //Set home selected
        bottomNavigationView.setSelectedItemId(R.id.home);
        //perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.recyclerview_video:
                        startActivity(new Intent(getApplicationContext(), VideoActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.blog_list:
                        startActivity(new Intent(getApplicationContext(), Blogs_Main.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.home:
//                        startActivity(new Intent(getApplicationContext(), Home.class));
//                        overridePendingTransition(0,0);
                        return true;

                    case R.id.question:
                        startActivity(new Intent(getApplicationContext(), QuestionAnswer.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_appbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add:
                startActivity(new Intent(MainActivity.this,PostActivity.class));
                overridePendingTransition(0, 0);
                return true;

            case R.id.action_about:
                startActivity(new Intent(getApplicationContext(),AboutActivity.class));
                Toast.makeText(this, "About is clicked.", Toast.LENGTH_SHORT).show();
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
    public void logout (View view)
    {
        FirebaseAuth.getInstance().signOut();
        //logout

        Intent logoutIntent = new Intent(MainActivity.this,Login.class);
        startActivity(logoutIntent);
        finish();

    }


}

