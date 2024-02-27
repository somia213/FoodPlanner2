package com.example.foodplannerapp.startSreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplannerapp.MainActivity;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.login.LoginActivity;
import com.example.foodplannerapp.signUp.SignupActivity;
import com.example.foodplannerapp.view.home.HomeFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class StartActivity extends AppCompatActivity {
    Button signup, google, guest;
    TextView login;
    FirebaseAuth auth;
    FirebaseDatabase database;
    GoogleSignInClient googleSignInClient;
    int RC_SIGN_IN = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        signup = findViewById(R.id.sign_up);
        google = findViewById(R.id.tv_google);
        guest = findViewById(R.id.button3);
        login = findViewById(R.id.loginTxtView);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        SharedPreferences preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        boolean isLogin = preferences.getBoolean("isLogin", false);
        if(isLogin){
            Intent intent = new Intent(this , MainActivity.class);
            startActivity(intent);
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();


        googleSignInClient = GoogleSignIn.getClient(this, gso);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignIn();
            }
        });

        signup.setOnClickListener(v -> {
            Intent intent = new Intent(
                    StartActivity.this, SignupActivity.class);
            startActivity(intent);
        });

        guest.setOnClickListener(v -> {
            Intent intent = new Intent(
                    StartActivity.this, MainActivity.class);
            startActivity(intent);
        });

        login.setOnClickListener(v -> {
            Intent intent = new Intent(
                    StartActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void googleSignIn() {
        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }
    

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } catch (ApiException e) {
//                e.printStackTrace();
                Toast.makeText(StartActivity.this, "Sorry can not sign in, there are something wrong", Toast.LENGTH_SHORT);
            }
        }
    }
}