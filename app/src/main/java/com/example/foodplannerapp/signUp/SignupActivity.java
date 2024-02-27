package com.example.foodplannerapp.signUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplannerapp.MainActivity;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    EditText Email , Password;
    Button signup;

    TextView loginTV;
    //FirebaseAuth mAuth;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Email = findViewById(R.id.signupEmailTxtView);
        Password = findViewById(R.id.passwordSignupTxtView);
        signup = findViewById(R.id.signUpBtn);
        loginTV = findViewById(R.id.loginTxtView);
        auth=FirebaseAuth.getInstance();
//        signup.setOnClickListener(v -> {
//            Intent intent = new Intent(
//                    SignupActivity.this, MainActivity.class);
//            startActivity(intent);
//        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignupActivity.this, "You clicked", Toast.LENGTH_SHORT).show();
                String user =Email.getText().toString().trim();
                String pass = Password.getText().toString().trim();

                if(user.isEmpty()){
                    Email.setError("Email cannot be empty");
                }

                if(pass.isEmpty()){
                    Password.setError("Password cannot be empty");
                }else{
                    auth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(SignupActivity.this,"SignUp Successfully",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignupActivity.this, LoginActivity.class));

//                                SharedPreferences preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
//                                SharedPreferences.Editor editor = preferences.edit();
//                                editor.putBoolean("isLogin", true);
//                                editor.apply();
                            }else{
                                Toast.makeText(SignupActivity.this, "SignUp Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        loginTV.setOnClickListener(v -> {
            Intent intent = new Intent(
                    SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });



    }

}