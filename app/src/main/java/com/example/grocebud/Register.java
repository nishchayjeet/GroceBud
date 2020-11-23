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

public class Register extends AppCompatActivity {
    EditText name,emailId,Password;
    Button btnsignup;
    Button tvsignin;

    FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Register");

        mFirebaseAuth = FirebaseAuth.getInstance();

        name=findViewById(R.id.editText3);
        emailId = findViewById(R.id.editText4);
        Password=findViewById(R.id.editText6);
        btnsignup = findViewById(R.id.button3);

        tvsignin = findViewById(R.id.button4);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nm = name.getText().toString();
                String email = emailId.getText().toString();
                String password = Password.getText().toString();

                if (nm.isEmpty()){
                    name.setError("Please Enter Your Name");
                    name.requestFocus();
                }
                else if (email.isEmpty()){
                    emailId.setError("Please Enter Email ID");
                    emailId.requestFocus();
                }

                else if (password.isEmpty()){
                    Password.setError("Please Enter the Password");
                    Password.requestFocus();
                }
                else if (nm.isEmpty() && email.isEmpty() && password.isEmpty()){
                    Toast.makeText(Register.this,"Fields are Empty",Toast.LENGTH_SHORT).show();
                }
                else if (!(nm.isEmpty() && email.isEmpty() && password.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Register.this, "SignUp Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity(new Intent(Register.this, get_started.class));
                            }
                        }
                    });
                }
                else {

                    Toast.makeText(Register.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                }
            }
        });
        tvsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this,login.class);
                startActivity(i);

            }
        });

    }

}

