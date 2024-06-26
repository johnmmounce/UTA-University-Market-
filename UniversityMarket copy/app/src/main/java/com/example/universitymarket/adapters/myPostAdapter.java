package com.example.universitymarket.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universitymarket.R;
import com.example.universitymarket.models.Post;
import com.example.universitymarket.utilities.Data;

import java.util.List;

public class myPostAdapter extends RecyclerView.Adapter<myPostAdapter.ViewHolder> {
    private final List<Post> myposts;
    private OnItemClickListener itemClickListener;
    private OnItemBtnClickListener itemBtnClickListener;


    public myPostAdapter(Context context, List<Post> posts,
                         OnItemClickListener item, OnItemBtnClickListener itemBtn) {
        myposts = posts;
        this.itemClickListener = item;
        this.itemBtnClickListener = itemBtn;
    }

    public void update(List<Post> posts) {
        myposts.clear();
        if(posts != null) { myposts.addAll(posts); }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public myPostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_mypost_item,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myPostAdapter.ViewHolder holder, int position) {
        Post post = myposts.get(position);
        holder.bind(post, itemClickListener, itemBtnClickListener);
    }

    @Override
    public int getItemCount() {
        return myposts.size();
    }

    public interface OnItemClickListener {
        void onItemClicked(Post post);
    }

    public interface OnItemBtnClickListener {
        void onItemBtnClicked(Post post);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView price;
        private final TextView date;
        private final TextView genre;
        private final Button trash;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.mypost_title_text);
            price = itemView.findViewById(R.id.mypost_price_text);
            date = itemView.findViewById(R.id.mypost_date_text);
            genre = itemView.findViewById(R.id.mypost_genre_text);
            trash = itemView.findViewById(R.id.mypost_trash_btn);
        }

        public void bind(final Post post, final OnItemClickListener clickListener,
                         final OnItemBtnClickListener clickBtnListener) {
            title.setText(post.getItemTitle());
            price.setText("$"+post.getListPrice());
            date.setText(Data.formatDate(Data.parseDate(post.getDateCreated()), "MMM dd, yyyy"));
            genre.setText(post.getGenre());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClicked(post);
                }
            });

            trash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { clickBtnListener.onItemBtnClicked(post); }
            });
        }
    }
}