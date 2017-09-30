package com.cse.mist.bookstack;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class UserProfile extends AppCompatActivity {


    ImageView imageView;
    TextView p_name, p_uni, p_roll, p_contact, p_email;
    private Button selectImage, edit;
    private int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile);

        selectImage = (Button) findViewById(R.id.selectInmage);
        imageView = (ImageView) findViewById(R.id.imageView);
        p_name = (TextView) findViewById(R.id.etNam);//
        p_uni = (TextView) findViewById(R.id.etUn);//
        p_roll = (TextView) findViewById(R.id.etrol);//
        p_contact = (TextView) findViewById(R.id.etcontac);//
        p_email = (TextView) findViewById(R.id.etEmai);//
        edit = (Button) findViewById(R.id.edit);


        selectImage.setOnClickListener(new View.OnClickListener() {

                                           @Override
                                           public void onClick(View view) {

                                               Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                                               photoPickerIntent.setType("image/*");
                                               startActivityForResult(photoPickerIntent, REQUEST_CODE);
                                           }
                                       }
        );

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(), EditProfile.class);
                view.getContext().startActivity(Intent);
            }
        });
    }


    protected void onStart() {
        super.onStart();

        String id = new ParseEmail().Parse(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        DatabaseReference resdata;
        resdata = FirebaseDatabase.getInstance().getReference("User").child(id);
        resdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User pc = dataSnapshot.getValue(User.class);

                p_name.setText(pc.getName());
                p_uni.setText(pc.getUni());
                p_roll.setText(pc.getRoll());
                p_contact.setText(pc.getPhone());
                p_email.setText(pc.getEmail());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imageView.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(UserProfile.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(UserProfile.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }
}

