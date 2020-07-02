package com.example.askme;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//import com.firebase.ui.database.FirebaseRecyclerOptions;


public class Blogs_Main extends AppCompatActivity {
    private FirebaseRecyclerAdapter adapter;
    private ImageView postView;
    private  DatabaseReference mDatabaseLikes;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    private RecyclerView mBlogList;
    private ImageButton mLikebtn;
    //    ImageView post_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatabaseLikes=FirebaseDatabase.getInstance().getReference().child("Likes");
        mDatabaseLikes.keepSynced(true);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setContentView(R.layout.activity_blog_main);
        findViewById(R.id.SubmitBtn);
        //RecyclerView recyclerView = findViewById(R.id.blog_list);

        mBlogList = findViewById(R.id.blog_list);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));
        //adapter = new MainAdapter(memberList,context);
        mBlogList.setAdapter(adapter);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Blog");

        mLikebtn=(ImageButton)findViewById(R.id.like_btn);

        //mDatabaseUsers.keepSynced(true);
        databaseReference.keepSynced(true);
        //mBlogList= findViewById(R.id.blog_list);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);


        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));
        /*postView = (ImageView) findViewById(R.id.post_image);
        postView.setOnClickListener(view  -> {
           // Intent intentCv = new Intent(Blogs_Main.this,BlogSingleActivity.class);
            startActivity(new Intent(Blogs_Main.this,BlogSingleActivity.class));*//*
        });*/
        //checkUserExists();

      /*  mLikebtn.setOnClickListener(v -> Toast.makeText(Blogs_Main.this, "Liked",Toast.LENGTH_SHORT).show());
*/
    }

protected void onStart(){
    super.onStart();
    FirebaseRecyclerAdapter<Blog,BlogViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Blog,BlogViewHolder>(

            Blog.class,
            R.layout.blog_row,
            BlogViewHolder.class,
            databaseReference

    ) {
        @Override
        protected void populateViewHolder(BlogViewHolder blogViewHolder, Blog blog, int i) {
            String post_key=getRef(i).toString();
            blogViewHolder.setTitle(blog.getTitle());
            blogViewHolder.setDesc(blog.getDesc());
            blogViewHolder.setImage(blog.getImage());
          //  blogViewHolder.setLikebtn(post_key);
        }
    };
    mBlogList.setAdapter(firebaseRecyclerAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_appbar, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.action_add:
                startActivity(new Intent(Blogs_Main.this,PostActivity.class));
            case R.id.action_about:
                //startActivity(new Intent(getApplicationContext(), Logout.class));
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
    }public void logout (View view)
    {
        FirebaseAuth.getInstance().signOut();
        //logout

        Intent logoutIntent = new Intent(Blogs_Main.this,Login.class);
        startActivity(logoutIntent);
        finish();

    }



}