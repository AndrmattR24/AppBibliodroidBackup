package com.andrmatt.appbibliodroid.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.andrmatt.appbibliodroid.databinding.ActivitySplashScreenBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreenActivity extends AppCompatActivity {

    //viewBinding
    private ActivitySplashScreenBinding _binding;

    //Wait Time
    int delayTime = 5000;

    //Firebase Auth
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(_binding.getRoot());

        //Inicialize Firebasee auth
        auth =  FirebaseAuth.getInstance();

        //wait 5sec for checkUser
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkUser();
            }
        },delayTime);
    }

    private void checkUser() {

        FirebaseUser user = auth.getCurrentUser();

        //User not Logged in
        if(user == null){

            startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            finish();
        }
        //user Logged
        else{
            startActivity(new Intent(SplashScreenActivity.this, DashboardUserActivity.class));
            finish();
        }
    }
}