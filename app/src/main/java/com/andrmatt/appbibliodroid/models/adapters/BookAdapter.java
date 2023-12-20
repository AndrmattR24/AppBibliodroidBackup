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
import com.squareup.picasso.Picasso;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private List<BookResponse.BookItem> books;
    private Context context;
    public BookAdapter(List<BookResponse.BookItem> books, Context context){
        this.books = books;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_books, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(books.get(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemBooksBinding binding;
        public ViewHolder(View itemView) {
            super(itemView);
            this.binding = ItemBooksBinding.bind(itemView);
        }

        public void bind(BookResponse.BookItem book) {
            binding.tvBookTitle.setText(book.attributes.titulo);
            binding.tvSipnosis.setText(book.attributes.sipnosis);
            Picasso.get().load(book.attributes.url_imagen).into(this.binding.ivFontPage);
        }
    }
}
