package com.andrmatt.appbibliodroid.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.andrmatt.appbibliodroid.R;
import com.andrmatt.appbibliodroid.databinding.ActivityDashboardUserBinding;
import com.andrmatt.appbibliodroid.models.adapters.BookAdapter;
import com.andrmatt.appbibliodroid.models.dto.BookResponse;
import com.andrmatt.appbibliodroid.service.ApiBook;
import com.andrmatt.appbibliodroid.service.ApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardUserActivity extends AppCompatActivity {

    //viewBinding
    private ActivityDashboardUserBinding _binding;
    private List<BookResponse.BookItem> books;
    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = ActivityDashboardUserBinding.inflate(this.getLayoutInflater());
        setContentView(_binding.getRoot());

        auth = FirebaseAuth.getInstance();
        checkuser();

        recyclerView = findViewById(R.id.rvFavorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        showBooks();

        //Evaluate On Click ImgLogout
        _binding.ivBtnLogoutUserDashboardUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builderMessage = new AlertDialog.Builder(DashboardUserActivity.this);
                builderMessage.setTitle("Logout").setMessage("Are You Sure Exit ?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                auth.signOut();
                                Toast.makeText(DashboardUserActivity.this, "Closed Session", Toast.LENGTH_SHORT).show();
                                checkuser();
                                finish();
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();

            }
        });

        _binding.ivBtnProfileDashboardUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardUserActivity.this, UserProfileActivity.class));
            }
        });
    }

    private void showBooks() {
        Call<BookResponse> call = ApiClient.getClient().create(ApiBook.class).listBooks();
        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                if (response.isSuccessful()) {
                    books = response.body().data;
                    bookAdapter = new BookAdapter(books, getApplicationContext());
                    recyclerView.setAdapter(bookAdapter);
                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                Log.i("test", "err " + t.getLocalizedMessage());
                Toast.makeText(DashboardUserActivity.this, "Error de Conexión", Toast.LENGTH_SHORT);
            }
        });
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    private void checkuser() {
        //obtener usuario actual de firebase
        FirebaseUser user = auth.getCurrentUser();

        if (user == null) {
            //el usuario no esta logueado
            startActivity(new Intent(DashboardUserActivity.this, MainActivity.class));
            finish();
        } else {
            //usuario logueado
            String email = user.getEmail();
            _binding.userEmail.setText(email);
        }

    }
}