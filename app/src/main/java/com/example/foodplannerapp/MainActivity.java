package com.example.foodplannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.foodplannerapp.databinding.ActivityMainBinding;
import com.example.foodplannerapp.favourite.view.FavouritFragment;
import com.example.foodplannerapp.search.view.SearchFragment;
import com.example.foodplannerapp.view.home.HomeFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    public static final int HOME = R.id.home;
    public static final int SEARCH = R.id.search;
    public static final int FAV = R.id.fav;
    public static final int CALENDAR = R.id.calender;
    private boolean isUserLoggedIn =true;
    BottomNavigationView nav_bar;
    GoogleSignInAccount account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        account= GoogleSignIn.getLastSignedInAccount(this);

        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);
        nav_bar = binding.bottomNavigationView;
        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            MenuItem myMenu = nav_bar.getMenu().findItem(R.id.fav);
            myMenu.setVisible(false);
        }

        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            MenuItem myMenu = nav_bar.getMenu().findItem(R.id.calender);
            myMenu.setVisible(false);
        }

        binding.bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == HOME) {
                    replaceFragment(new HomeFragment());
                } else if (item.getItemId() == SEARCH) {
                    replaceFragment(new SearchFragment());
                } else if (item.getItemId() == FAV) {
                    if (isUserLoggedIn) {
                        replaceFragment(new FavouritFragment());
                    } else {
                        Toast.makeText(MainActivity.this, "Please log in or sign up to access favorites.", Toast.LENGTH_SHORT).show();
                    }
                } else if (item.getItemId() == CALENDAR) {
                    if (isUserLoggedIn) {
                        replaceFragment(new CalenderFragment());
                    } else {
                        Toast.makeText(MainActivity.this, "Please log in or sign up to access the calendar.", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            }
        });

    }

//    protected void isGeust() {
//        super.onStart();
//        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//        if (currentUser == null && account == null) {
//            isUserLoggedIn = false;
//            Toast.makeText(this, "Please SignIn", Toast.LENGTH_SHORT).show();
//        }else{
//            isUserLoggedIn = true;
//        }
//    }


//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//        if(item.getItemId() == (FAV) || item.getItemId() ==(CALENDAR)) {
//
//            if(!isUserLoggedIn) {
//                Toast.makeText(this, "Please login", Toast.LENGTH_SHORT).show();
//                return true;
//            }
//
//        }

//        return true;
//    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
