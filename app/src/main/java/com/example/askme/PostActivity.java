package com.example.askme;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class PostActivity extends AppCompatActivity {

    private ImageButton mSelectImage;
    private static final int GALLERY_REQUEST = 1;
    private EditText mPostTitle;
    private EditText mPostDesc;
    private Button mSubmitBtn;

//    private StorageTask uploadTask;

    private Uri mImageUri = null;
    //instance for firebase storage and Storagereference
    private StorageReference mStorage;
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabaseUser;
    // instance for progress dailog
    private ProgressDialog mProgress;
    private FirebaseAuth mAuth;

    private FirebaseUser mCurrentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mSelectImage = findViewById(R.id.ImageSelect);
        mPostTitle = findViewById(R.id.TitleField);
        mPostDesc = findViewById(R.id.DescField);
        mSubmitBtn = findViewById(R.id.SubmitBtn);
        mStorage= FirebaseStorage.getInstance().getReference();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Blog");
//        mDatabaseUser=FirebaseDatabase.getInstance().getReference().child("Users").child(mCurrentUser.getUid());

        mProgress=new ProgressDialog(this);
  //      mCurrentUser=mAuth.getCurrentUser();

        mSelectImage.setOnClickListener(view -> {
            Intent galleryIntent = new Intent();
            galleryIntent.setType("image/*");
            galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(galleryIntent,"Select Image from here.."),GALLERY_REQUEST);

        });

        mSubmitBtn.setOnClickListener(view -> startPosting());
    }
    private void startPosting() {

        mProgress.setMessage("Uploading....");
        mProgress.show();

        final String title_val = mPostTitle.getText().toString().trim();
        final String desc_val = mPostDesc.getText().toString().trim();

        Log.d("Rapp" ,title_val+""+desc_val);

        if (!TextUtils.isEmpty(title_val) && (!TextUtils.isEmpty(desc_val)) && mImageUri != null) {
            Log.d("Rapp","here");
            //Code for showing progressDailog while uploading..
            StorageReference filepath = mStorage.child("Blog_Images/").child(mImageUri.getLastPathSegment());
            filepath.putFile(mImageUri).addOnSuccessListener(taskSnapshot -> {

                String downloadUrl=taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();

                DatabaseReference newPost=mDatabase.push();
                newPost.child("title").setValue(title_val);
                newPost.child("desc").setValue(desc_val);
                newPost.child("image").setValue(downloadUrl);
                //newPost.child("uid").setValue(mCurrentUser.getUid());
                mProgress.dismiss();
                startActivity(new Intent(PostActivity.this,Blogs_Main.class));
            });


        }else{

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK ) {
//
//             Uri mImageUri = data.getData();
//            mSelectImage.setImageURI(mImageUri);
//        }
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK && data!=null &&data.getData()!=null){
            mImageUri=data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),mImageUri);

                mSelectImage.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
