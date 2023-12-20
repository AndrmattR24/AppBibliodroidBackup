package com.andrmatt.appbibliodroid.views;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.andrmatt.appbibliodroid.R;
import com.andrmatt.appbibliodroid.databinding.ActivityHistorialBooksBinding;
import com.andrmatt.appbibliodroid.guards.LocalGuardActivity;
import com.andrmatt.appbibliodroid.guards.LocalGuardView;
import com.andrmatt.appbibliodroid.models.adapters.SearchAdapter;
import com.andrmatt.appbibliodroid.models.entities.SearchEntity;
import com.andrmatt.appbibliodroid.models.repository.SearchRepository;

import java.util.ArrayList;
import java.util.List;

public class HistorialBooksActivity extends LocalGuardActivity implements LocalGuardView {

    private ActivityHistorialBooksBinding binding;
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    private SearchRepository searchRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistorialBooksBinding.inflate(this.getLayoutInflater());
        setContentView(binding.getRoot());
        recyclerView = findViewById(R.id.rvSearches);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        // render info auth
        this.renderInfo(this);
        // events
        binding.enterSearch.setOnQueryTextListener(new SearchListener());
        // repositories
        searchRepository = new SearchRepository(this.getApplication());
        // room
        showSearches();
    }

    private void showSearches() {
        List<SearchEntity> searches = searchRepository.getSearches(localGuard.getUser().getUid());
        searchAdapter = new SearchAdapter(getApplicationContext(), searchRepository, searches);
        recyclerView.setAdapter(searchAdapter);
    }

    @Override
    public TextView getViewTextEmail() {
        return binding.userEmail;
    }

    private class SearchListener implements SearchView.OnQueryTextListener {
        @Override
        public boolean onQueryTextSubmit(String query) {
            SearchEntity search = new SearchEntity(query, localGuard.getUser().getUid());
            searchRepository.insertSearch(search);
            Intent intent = new Intent(HistorialBooksActivity.this, DashboardUserActivity.class);
            intent.putExtra("querySearch", search.getContent());
            startActivity(intent);
            showSearches();
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    }
}