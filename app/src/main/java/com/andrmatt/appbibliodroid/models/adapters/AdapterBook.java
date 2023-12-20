package com.andrmatt.appbibliodroid.models.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andrmatt.appbibliodroid.R;
import com.andrmatt.appbibliodroid.databinding.ItemBooksBinding;
import com.andrmatt.appbibliodroid.models.dto.BookResponse;

import java.security.PublicKey;
import java.util.List;

public class AdapterBook extends RecyclerView.Adapter<AdapterBook.HolderBook>{

    private Context contex;

    public List<BookResponse.Datos> booklist;

    public AdapterBook(Context contex, List<BookResponse.Datos> booklist) {
        this.contex = contex;
        this.booklist = booklist;
    }

    public AdapterBook(List<BookResponse.Datos> booklist) {
        this.booklist = booklist;
    }


    @NonNull
    @Override
    public HolderBook onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new AdapterBook.HolderBook(layoutInflater.inflate(R.layout.item_books, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HolderBook holder, int position) {
        BookResponse.Datos item = (BookResponse.Datos) booklist.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return booklist.size();
    }

    public final class HolderBook extends RecyclerView.ViewHolder{

        private ItemBooksBinding binding;

        public HolderBook(@NonNull View itemView) {
            super(itemView);
            this.binding = ItemBooksBinding.bind(itemView);
        }

        public void bind(BookResponse.Datos item) {
            binding.tvBookTitle.setText(item.getAttributes().getTitulo());
            binding.tvSipnosis.setText(item.getAttributes().getSipnosis());
        }
    }
}
