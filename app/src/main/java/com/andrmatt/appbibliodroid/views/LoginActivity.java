package com.andrmatt.appbibliodroid.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.andrmatt.appbibliodroid.R;
import com.andrmatt.appbibliodroid.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    //viewBinding;
    private ActivityLoginBinding _binding;

    //Firebase Auth
    private FirebaseAuth auth;

    //progress dialog
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(_binding.getRoot());

        //inicialize Firebase Auth
        auth = FirebaseAuth.getInstance();

        //inicilize Progress Dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Load User Session");
        progressDialog.setCanceledOnTouchOutside(false);
        _binding.linkVistaRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        _binding.btnLoguearse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validData();
            }
        });
    }

    String email, password;

    private void validData() {

        email = _binding.txtEmail.getText().toString().trim();
        password = _binding.txtPassword.getText().toString().trim();

       if(TextUtils.isEmpty(email)){
           Toast.makeText(this, "Enter Email Please ...", Toast.LENGTH_SHORT).show();
       }
       else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
           Toast.makeText(this, "Enter valid Email Please ...", Toast.LENGTH_SHORT).show();
       }
       else{
           LoginAccount();
       }
    }

    private void LoginAccount() {
        progressDialog.setMessage("Logging In ...");
        progressDialog.show();

        //login User with using FirebaseAuth
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                checkUser();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "Error : "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Valid if User is Logged
    private void checkUser() {
        progressDialog.setMessage("checking User ...");
        progressDialog.show();

        FirebaseUser user = auth.getCurrentUser();

        //User not Logged in
        if(user == null){

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        //user Logged
        else{
            startActivity(new Intent(LoginActivity.this, DashboardUserActivity.class));
            finish();
        }
    }
}