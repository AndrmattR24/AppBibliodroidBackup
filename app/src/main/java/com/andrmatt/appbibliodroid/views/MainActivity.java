package com.andrmatt.appbibliodroid.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.andrmatt.appbibliodroid.R;
import com.andrmatt.appbibliodroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //viewBinding
    private ActivityMainBinding _binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding =  ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(_binding.getRoot());

        //Click On Register
        _binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //init View Register
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

        //Click On Login
        _binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //init View Login
                startActivity(new Intent(MainActivity.this, LoginActivity.class));

            }
        });

    }
}