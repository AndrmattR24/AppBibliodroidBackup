package com.andrmatt.appbibliodroid.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.andrmatt.appbibliodroid.R;
import com.andrmatt.appbibliodroid.databinding.ActivityDashboardUserBinding;
import com.andrmatt.appbibliodroid.databinding.ActivityLoginBinding;
import com.andrmatt.appbibliodroid.models.adapters.AdapterBook;
import com.andrmatt.appbibliodroid.models.dto.BookResponse;
import com.andrmatt.appbibliodroid.service.ApiBook;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardUserActivity extends AppCompatActivity {

    //viewBinding
    private ActivityDashboardUserBinding _binding;
    private List<BookResponse.Datos> books;
    private AdapterBook adapterBookK;
    private BookResponse bookResponse;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = ActivityDashboardUserBinding.inflate(this.getLayoutInflater());
        setContentView(_binding.getRoot());

        auth = FirebaseAuth.getInstance();

        loadData();
        checkuser();


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

    private void loadData() {
        Thread thread = new Thread(
                ()->{
                    try{
                        Response<List<BookResponse>> ApiResponse = getRetrofit()
                                .create(ApiBook.class). listBooks().execute();
                        bookResponse = (BookResponse) ApiResponse.body();
                        runOnUiThread(
                                () ->{
                                    if(bookResponse != null){
                                        showList(bookResponse);
                                    }else{
                                        Toast.makeText(this,"No se Hallaron Libros",Toast.LENGTH_SHORT).show();
                                    };
                                }
                        );
                    }catch (Exception e){

                    }
                }
        );
        thread.start();
    }

    private void showList(BookResponse bookResponse) {
        if(bookResponse.getData().size() > 0){
          books = bookResponse.getData();
        }

        adapterBookK = new AdapterBook(books);
        _binding.rvFavorites.setHasFixedSize(true);
        _binding.rvFavorites.setLayoutManager(new LinearLayoutManager(this));
        _binding.rvFavorites.setAdapter(adapterBookK);
    }

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://books.unu-planilla.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClienteRetrofit()).build();
    }

    private OkHttpClient getClienteRetrofit() {
        return new OkHttpClient.Builder().build();
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