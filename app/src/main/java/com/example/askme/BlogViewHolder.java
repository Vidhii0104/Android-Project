package com.example.askme;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class BlogViewHolder extends RecyclerView.ViewHolder {
    private DatabaseReference mDatabaseLike;
    private ImageButton mLikebtn;
    private FirebaseAuth mAuth;
    public LinearLayout root;
    View mView;

    public BlogViewHolder(@NonNull View itemView) {
        super(itemView);
//        mLikebtn = mLikebtn.findViewById(R.id.like_btn);
        mDatabaseLike= FirebaseDatabase.getInstance().getReference().child("Likes");
        mView = itemView;
    }

    public void setTitle(String title) {

        TextView post_title = (TextView) mView.findViewById(R.id.post_title);
        post_title.setText(title);
    }

    public void setDesc(String desc) {
        TextView post_desc = (TextView) mView.findViewById(R.id.post_desc);
        post_desc.setText(desc);
    }

    public void setImage(String image) {

        //Log.d("Vii",image.toString());
        /*ImageView post_image = (ImageView) mView.findViewById(R.id.post_image);
        Picasso.get().load(image).into(post_image);*/

    }

  /*  public  void setLikebtn(String post_key){

        mDatabaseLike.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(post_key).hasChild(mAuth.getCurrentUser().getUid())){

                    mLikebtn.setImageResource(R.drawable.outline_thumb_up);

                }else {

                    mLikebtn.setImageResource(R.drawable.thumb_up);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }*/
}
