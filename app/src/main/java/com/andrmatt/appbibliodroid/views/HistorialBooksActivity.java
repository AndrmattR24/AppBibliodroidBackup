package com.andrmatt.appbibliodroid.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.andrmatt.appbibliodroid.R;
import com.andrmatt.appbibliodroid.databinding.ActivityHistorialBooksBinding;
import com.andrmatt.appbibliodroid.databinding.ItemSearchBinding;
import com.andrmatt.appbibliodroid.models.adapters.BookAdapter;
import com.andrmatt.appbibliodroid.models.adapters.SearchAdpter;
import com.andrmatt.appbibliodroid.models.entities.SearchEntity;

import java.util.ArrayList;
import java.util.List;

public class HistorialBooksActivity extends AppCompatActivity {

    private ActivityHistorialBooksBinding binding;
    private List<SearchEntity> listSearches = new ArrayList<SearchEntity>();
    private RecyclerView recyclerView;
    private SearchAdpter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistorialBooksBinding.inflate(this.getLayoutInflater());
        setContentView(binding.getRoot());
        recyclerView = findViewById(R.id.rvSearches);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HistorialBooksActivity.this, "Hans es chivo", Toast.LENGTH_SHORT).show();
            }
        });        
    }

    private void showSearches() {
        SearchEntity search = new SearchEntity("Goku", "11111");
        listSearches.add(search);
        searchAdapter = new SearchAdpter(listSearches, getApplicationContext());
        recyclerView.setAdapter(searchAdapter);
    }
}