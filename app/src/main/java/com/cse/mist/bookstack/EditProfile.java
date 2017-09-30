package com.cse.mist.bookstack;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class EditProfile extends AppCompatActivity {

    String id;
    private EditText e_name, e_uni, e_roll, e_pass, e_mail, e_phone;
    private String name, uni, roll, pass, email, phone;
    private Button regbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);

        e_name = (EditText) findViewById(R.id.etName);
        e_uni = (EditText) findViewById(R.id.etUni);
        e_roll = (EditText) findViewById(R.id.etroll);
        e_pass = (EditText) findViewById(R.id.etPassword);
        e_mail = (EditText) findViewById(R.id.etEmail);
        e_phone = (EditText) findViewById(R.id.etphn);
        regbtn = (Button) findViewById(R.id.tvRegBtn);
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
                updateUser();//call when button click and need to check the input
            }
        });
    }


    public void register() {
        initialize();//initialize the input to string variable
        if (!validate()) {
            Toast.makeText(this, "EDIT failed", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(getApplicationContext(), MainPageNav.class));
        }

    }


    public boolean validate() {
        boolean valid = true;
        if (name.isEmpty() || name.length() > 10) {
            e_name.setError("please enter valid name");
            valid = false;
        }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            e_mail.setError("please enter valid email");
            valid = false;
        }
        if (phone.isEmpty()) {
            e_phone.setError("please enter valid phone number");
            valid = false;
        }
        if (pass.isEmpty()) {
            e_pass.setError("please enter valid password");
            valid = false;
        }
        if (uni.isEmpty()) {
            e_uni.setError("please enter valid password");
            valid = false;
        }
        if (roll.isEmpty()) {
            e_roll.setError("please enter valid password");
            valid = false;
        }
        return valid;

    }

    public void initialize() {
        name = e_name.getText().toString().trim();
        uni = e_uni.getText().toString().trim();
        phone = e_phone.getText().toString().trim();
        email = e_mail.getText().toString().trim();
        pass = e_pass.getText().toString().trim();
        roll = e_roll.getText().toString().trim();
    }

    private void updateUser() {

        // getting the specific artist reference
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("User").child(name);

        // getting the value
        String name = e_name.getText().toString().trim();
        String uni = e_uni.getText().toString();
        String roll = e_phone.getText().toString().trim();
        String email = e_mail.getText().toString();
        String pass = e_pass.getText().toString();
        String phone = e_roll.getText().toString().trim();


        // creating the object
        User user = new User(name, uni, roll, email, pass, phone);

        // set the value
        databaseRef.setValue(user);

        Toast.makeText(this, "User Info Updated!", Toast.LENGTH_SHORT).show();
    }
}


