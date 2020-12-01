package com.example.grocebud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class  login extends AppCompatActivity {
    EditText emailId,Password;
    Button btnsignIn;
    Button tvsignup;

    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        mFirebaseAuth = FirebaseAuth.getInstance();

        emailId = findViewById(R.id.editText2);
        Password=findViewById(R.id.editText3);

        btnsignIn = findViewById(R.id.button4);


        tvsignup = findViewById(R.id.button5);


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser != null ){
                    Toast.makeText(login.this, "You are Logged In",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(login.this , get_started.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(login.this, "Please Login",Toast.LENGTH_SHORT).show();

                }
            }
        };
        btnsignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                String email = emailId.getText().toString();
                String password = Password.getText().toString();


                if (email.isEmpty()){
                    emailId.setError("Please Enter Email ID");
                    emailId.requestFocus();
                }

                else if (password.isEmpty()){
                    Password.setError("Please Enter the Password");
                    Password.requestFocus();
                }
                else if (email.isEmpty() && password.isEmpty()){
                    Toast.makeText(login.this,"Fields are Empty",Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && password.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(login.this,"Login Error Occurred! Try Again",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent intTogetStarted = new Intent(login.this,get_started.class);
                                startActivity(intTogetStarted);
                            }
                        }
                    });
                }
                else {

                    Toast.makeText(login.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                }
            }


        });
        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent isign = new Intent(login.this, Register.class);
                startActivity(isign);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
