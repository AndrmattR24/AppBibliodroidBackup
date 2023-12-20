package com.andrmatt.appbibliodroid.models.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.andrmatt.appbibliodroid.R;
import com.andrmatt.appbibliodroid.databinding.ItemSearchBinding;
import com.andrmatt.appbibliodroid.models.entities.SearchEntity;
import com.andrmatt.appbibliodroid.models.repository.SearchRepository;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private SearchRepository searchRepository;
    private List<SearchEntity> listSearches;


    public SearchAdapter(Context context, SearchRepository searchRepository, List<SearchEntity> listSearches) {
        this.context = context;
        this.searchRepository = searchRepository;
        this.listSearches = listSearches;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        holder.bind(listSearches.get(position));
    }

    @Override
    public int getItemCount() {
        return listSearches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SearchEntity search;
        private ItemSearchBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.binding = ItemSearchBinding.bind(itemView);
        }
        public void bind(SearchEntity search) {
            this.search = search;
            this.binding.txtHBook.setText(search.getContent());
            binding.touchDelete.setOnClickListener(new OnDeleteSearch());
        }

        public class OnDeleteSearch  implements View.OnClickListener {
            @Override
            public void onClick(View v) {
                searchRepository.deleteSearch(search.getId());
                listSearches.remove(search);
            }
        }
    }
}
