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
import java.util.List;

public class SearchAdpter extends RecyclerView.Adapter<SearchAdpter.ViewHolder> {

    private List<SearchEntity> listSearches;
    private Context context;

    public SearchAdpter(List<SearchEntity> listSearches, Context context) {
        this.listSearches = listSearches;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchAdpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdpter.ViewHolder holder, int position) {
        holder.bind(listSearches.get(position));
    }

    @Override
    public int getItemCount() {
        return listSearches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemSearchBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.binding = ItemSearchBinding.bind(itemView);
        }

        public void bind(SearchEntity search) {
            this.binding.txtHBook.setText(search.getContent());


            binding.touchDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Chivo eliminado", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
