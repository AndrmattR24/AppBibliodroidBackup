package com.andrmatt.appbibliodroid.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
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

        // events
        binding.enterSearch.setOnQueryTextListener(new SearchListener());

        // room
        showSearches();
    }

    private void showSearches() {
        SearchEntity search = new SearchEntity("Goku", "11111");
        listSearches.add(search);
        searchAdapter = new SearchAdpter(listSearches, getApplicationContext());
        recyclerView.setAdapter(searchAdapter);
    }

    private class SearchListener implements SearchView.OnQueryTextListener {

        @Override
        public boolean onQueryTextSubmit(String query) {
            Toast.makeText(HistorialBooksActivity.this, "maldito chivo :v", Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    }
}