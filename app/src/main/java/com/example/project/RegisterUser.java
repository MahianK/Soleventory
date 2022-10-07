package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private TextView GoBack;
    private EditText editFullName;
    private EditText editEmail;
    private EditText editPassword;
    private Button RegisterUser;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);

        mAuth = FirebaseAuth.getInstance();

        RegisterUser = (Button) findViewById(R.id.btnRegister);
        RegisterUser.setOnClickListener(this);

        GoBack = (TextView) findViewById(R.id.textView2);
        GoBack.setOnClickListener(this);

        editFullName = (EditText) findViewById(R.id.registerName);
        editEmail = (EditText) findViewById(R.id.registerEmail);
        editPassword = (EditText) findViewById(R.id.registerPassword);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textView2:
                startActivity(new Intent(RegisterUser.this, MainActivity.class));
                break;
            case R.id.btnRegister:
                registerUser();
                break;
        }

    }

    private void registerUser(){
        String registerEmail = editEmail.getText().toString().trim();
        String registerPassword = editPassword.getText().toString().trim();
        String registerName = editFullName.getText().toString().trim();

        if(registerName.isEmpty()){
            editFullName.setError("Full name is required!");
            editFullName.requestFocus();
            return;
        }

        if(registerEmail.isEmpty()){
            editEmail.setError("Email is required!");
            editEmail.requestFocus();
            return;

        }

        if(!Patterns.EMAIL_ADDRESS.matcher(registerEmail).matches()){
            editEmail.setError("PLease provide valid email!");
            editEmail.requestFocus();
            return;
        }

        if(registerPassword.isEmpty()){
            editPassword.setError("Password is required!");
            editPassword.requestFocus();
            return;
        }

        if(registerPassword.length() < 6){
            editPassword.setError("Password length should be 6 characters!");
            editPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(registerEmail, registerPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            User user = new User(registerName, registerEmail);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterUser.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(RegisterUser.this, "Failed to register", Toast.LENGTH_LONG).show();
                                    }
                                    progressBar.setVisibility(View.GONE);
                                }
                            });
                        }
                    }
                });



    }


}