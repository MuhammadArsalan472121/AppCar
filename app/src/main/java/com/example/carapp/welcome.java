package com.example.carapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class welcome extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    TextView userEmail;
    Button google,music,contacts,logout;
    ImageView map,mus,con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null){
            startActivity(new Intent(this,login.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        google = (Button) findViewById(R.id.button5);
        music = (Button) findViewById(R.id.button6);
        contacts = (Button) findViewById(R.id.button7);
        map = (ImageView) findViewById(R.id.imageView1);
        mus = (ImageView) findViewById(R.id.imageView2);
        con = (ImageView) findViewById(R.id.imageView3);
        logout = (Button) findViewById(R.id.button8);
        userEmail = (TextView) findViewById(R.id.textView6);
        userEmail.setText("Welcome " +user.getEmail());


        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengoogle();
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengoogle();
            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmusic();
            }
        });
        mus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmusic();
            }
        });

         contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencontacts();
            }
        });
        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencontacts();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v == logout){
                    firebaseAuth.signOut();
                    finish();
                    openlogin();
                }
            }
        });


    }

    private void opengoogle(){
        Intent i = getPackageManager().getLaunchIntentForPackage("com.google.android.apps.maps");
        startActivity(i);
    }

    private void openmusic(){
        Intent i = getPackageManager().getLaunchIntentForPackage("com.google.android.music");
        startActivity(i);
    }

    private void opencontacts(){
        Intent i = getPackageManager().getLaunchIntentForPackage("com.android.contacts");
        startActivity(i);
    }

    private void openlogin(){
        Intent i =new Intent(this,login.class);
        startActivity(i);
    }

}
