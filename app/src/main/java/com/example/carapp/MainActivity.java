package com.example.carapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;
    Button login,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button)findViewById(R.id.button);
        signup= (Button)findViewById(R.id.button2);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openlogin();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensignup();
            }
        });
    }

    private void openlogin(){
        Intent i =new Intent(this,login.class);
        startActivity(i);
    }

    private void opensignup(){
        Intent i = new Intent(this,signup.class);
        startActivity(i);
    }

    public void onBackPressed(){
        if (backPressedTime + 2000> System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(),"Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

}
