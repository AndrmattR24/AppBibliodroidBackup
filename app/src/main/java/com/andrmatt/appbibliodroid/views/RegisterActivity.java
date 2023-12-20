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
import com.andrmatt.appbibliodroid.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    //viewBinding
    private ActivityRegisterBinding _binding;

    //Firebase
    private FirebaseAuth auth;

    //Progress Dialog
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(_binding.getRoot());

        //inicialize FirebaseAuth
        auth = FirebaseAuth.getInstance();

        //inicialize ProgressDialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setCanceledOnTouchOutside(false);

        //Go Login View
        _binding.linkVistaLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        _binding.btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validData();
            }
        });
    }
    private String name, email, password;

    private void validData() {
        //Validate the Fields before creating a Account

        //get data
        name = _binding.txtNombre.getText().toString().trim();
        email = _binding.txtEmail.getText().toString().trim();
        password = _binding.txtPassword.getText().toString().trim();
        String confirmPass = _binding.txtConfirmPassword.getText().toString().trim();

        //We Evaluate the Data
        if(TextUtils.isEmpty(name)){
            Toast.makeText(RegisterActivity.this, "Enter Name Please ...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Enter Email Please ....", Toast.LENGTH_SHORT).show();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Enter Valid Email please ...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Enter Password Please", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(confirmPass)){
            Toast.makeText(this, "Repeat Password Please", Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals(confirmPass)){
            Toast.makeText(this, "Password Do Not Matches", Toast.LENGTH_SHORT).show();
        }
        else{
            CreateAccount();
        }
    }

    private void CreateAccount() {

        //show ProgressDialog
        progressDialog.setMessage("Creating Account ...");
        progressDialog.show();

        //created Account Using FirebaseAuth
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                updateUserInformationData();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Update UserData in FirebaseDatabase
    private void updateUserInformationData() {
        progressDialog.setMessage("Saving User Data ...");
        progressDialog.show();

        //get realTime
        long timestamp = System.currentTimeMillis();

        //get unique User Uid
        String uid = auth.getUid();

        //Set Data in Memory
        HashMap<String, Object> tempDb = new HashMap<>();
        tempDb.put("uid", uid);
        tempDb.put("name",name);
        tempDb.put("email",email);
        tempDb.put("profileImag","");
        tempDb.put("userType","user");
        tempDb.put("timestamp",timestamp);

        //set MemoryData in FirebaseDb
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
        ref.child(uid)
                .setValue(tempDb).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "Account Created ...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, DashboardUserActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}