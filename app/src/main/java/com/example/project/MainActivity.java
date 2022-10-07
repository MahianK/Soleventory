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
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView register;
    public EditText Email;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int count = 5;
    private TextView Register;
    private TextView Reset;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = (EditText) findViewById(R.id.etEmail);
        Password = (EditText) findViewById(R.id.etPassword);
        Info = (TextView) findViewById(R.id.tvInfo);
        Login = (Button) findViewById(R.id.btnLogin);
        Info.setText("No of attempts remaining: " + count);
        Register = (TextView) findViewById((R.id.register));
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Reset = (TextView) findViewById(R.id.fgpassword);

        Register.setOnClickListener(this);
        Login.setOnClickListener(this);
        Reset.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.register:
                Intent intent = new Intent(MainActivity.this, RegisterUser.class);
                startActivity(intent);

            case R.id.btnLogin:
                userLogin();
                break;
            case R.id.fgpassword:
                Intent intent1 = new Intent(MainActivity.this, ForgotPasword.class);
                startActivity(intent1);



        }
    }



    private void userLogin() {
        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();

        if(email.isEmpty()){
            Email.setError("Email is required!");
            Email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Email.setError("Please enter a valid email!");
            Email.requestFocus();
            return;
        }

        if(password.isEmpty()){
            Password.setError("Password is required!");
            Password.requestFocus();
            return;
        }
        if(password.length() < 6){
            Password.setError("Min password length is 6 chars!");
            Password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){
                        // go to user profile
                        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    } else{
                        user.sendEmailVerification();
                        Toast.makeText(MainActivity.this, "Check email to verify your account!", Toast.LENGTH_SHORT).show();
                    }

                    
                    
                }else {
                    Toast.makeText(MainActivity.this, "Failed to login, Please check your credentials!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}