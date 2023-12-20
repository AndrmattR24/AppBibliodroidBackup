package com.andrmatt.appbibliodroid.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.andrmatt.appbibliodroid.R;
import com.andrmatt.appbibliodroid.databinding.ActivityDashboardUserBinding;
import com.andrmatt.appbibliodroid.guards.LocalGuard;
import com.andrmatt.appbibliodroid.guards.LocalGuardActivity;
import com.andrmatt.appbibliodroid.guards.LocalGuardView;
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

public class DashboardUserActivity extends LocalGuardActivity implements LocalGuardView {

    private ActivityDashboardUserBinding _binding;
    private List<BookResponse.BookItem> books;
    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = ActivityDashboardUserBinding.inflate(this.getLayoutInflater());
        setContentView(_binding.getRoot());
        recyclerView = findViewById(R.id.rvFavorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        // render info auth
        this.renderInfo(this);
        // loadData
        showBooks();
        // events
        _binding.ivBtnLogoutUserDashboardUser.setOnClickListener(new OnClickLogout(localGuard));
        _binding.ivBtnProfileDashboardUser.setOnClickListener(new OnRedirectProfile());
    }

    private void showBooks() {
        String querySearch = getIntent().getExtras().getString("querySearch");
        Call<BookResponse> call = ApiClient.getClient().create(ApiBook.class).listBooks(querySearch);
        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                if (response.isSuccessful()) {
                    _binding.cantResults.setText(String.valueOf(response.body().meta.pagination.getTotal()));
                    books = response.body().data;
                    bookAdapter = new BookAdapter(books, getApplicationContext());
                    recyclerView.setAdapter(bookAdapter);
                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                Toast.makeText(DashboardUserActivity.this, "Error de Conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public TextView getViewTextEmail() {
        return _binding.userEmail;
    }
}