package com.example.carapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity implements View.OnClickListener {
    private EditText Uname,Uemail,Upassword;
    private TextView signin;
    private Button signup;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser()!= null){
            finish();
            welcomeact();
        }


        progressDialog = new ProgressDialog(this);
        Uname = (EditText) findViewById(R.id.editText3);
        Uemail = (EditText) findViewById(R.id.editText4);
        Upassword = (EditText) findViewById(R.id.editText5);
        signup = (Button) findViewById(R.id.button4);
        signin = (TextView) findViewById(R.id.textView8);


        signup.setOnClickListener(this);
        signin.setOnClickListener(this);

    }

    private void registerUser() {
        String name = Uname.getText().toString();
        String email = Uemail.getText().toString().trim();
        String password = Upassword.getText().toString().trim();

        if (TextUtils.isEmpty(name)){
            //user name is empty
            Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //successfully registered
                    Toast.makeText(signup.this,"Registered Successfully",Toast.LENGTH_LONG).show();
                    finish();
                    welcomeact();
                }else{
                    Toast.makeText(signup.this,"Could not register. please try again",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();

            }
        });

    }


    @Override
    public void onClick(View v) {
        if (v == signup){
            registerUser();
        }

        if(v == signin){
            openlogin();
        }
    }


    private void openlogin(){
        Intent i = new  Intent(this,login.class);
        startActivity(i);
    }

    private void welcomeact(){
        Intent i = new  Intent(this,welcome.class);
        startActivity(i);
    }

}
