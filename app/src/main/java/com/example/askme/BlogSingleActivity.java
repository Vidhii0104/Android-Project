package com.example.askme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class BlogSingleActivity extends AppCompatActivity {

    private String mPost_key=null;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    private ImageView mblogSingleImage;
    private TextView mblogSingleTitle;
    private TextView mblogSingleDesc;
    private Button mSingleRemoveBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_single);


        mblogSingleDesc=(TextView)findViewById(R.id.singleBlogDesc);
        mblogSingleImage=(ImageView)findViewById(R.id.singleBlogImageView);
        mblogSingleTitle=(TextView)findViewById(R.id.singleBlogTitle);
        mSingleRemoveBtn=(Button)findViewById(R.id.singleRemoveBtn);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Blog");
        mAuth= FirebaseAuth.getInstance();

        String mPost_key=getIntent().getExtras().getString("blog_id");
        //Toast.makeText(BlogSingleActivity.this,post_key,Toast.LENGTH_LONG).show();

        mDatabase.child(mPost_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String post_title=(String) dataSnapshot.child("title").getValue();
                String post_desc=(String)dataSnapshot.child("desc").getValue();
                String post_image=(String)dataSnapshot.child("image").getValue();
                String post_uid=(String)dataSnapshot.child("uid").getValue();

                mblogSingleTitle.setText(post_title);
                mblogSingleDesc.setText(post_desc);

                Picasso.get().load(post_image).into(mblogSingleImage);

                Toast.makeText(BlogSingleActivity.this,mAuth.getCurrentUser().getUid(),Toast.LENGTH_LONG).show();

                if (mAuth.getCurrentUser().getUid().equals(post_uid)){

                   // Toast.makeText(BlogSingleActivity.this,post_uid,Toast.LENGTH_LONG).show();
                    mSingleRemoveBtn.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mSingleRemoveBtn.setOnClickListener(view -> {

            mDatabase.child(mPost_key).removeValue();
            Intent mainIntent=new Intent(BlogSingleActivity.this,Blogs_Main.class);
            startActivity(mainIntent);

        });

    }

}
