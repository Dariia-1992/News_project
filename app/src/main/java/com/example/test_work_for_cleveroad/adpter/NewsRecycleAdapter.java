package com.example.test_work_for_cleveroad.adpter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test_work_for_cleveroad.R;
import com.example.test_work_for_cleveroad.model.Article;

import java.util.List;

public class NewsRecycleAdapter extends RecyclerView.Adapter<NewsRecycleAdapter.ViewHolder> {

    private List<Article> mArticleList;

    public NewsRecycleAdapter(List<Article> articleList) {
        mArticleList = articleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.articles_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Article article = mArticleList.get(i);
        viewHolder.mTitle.setText(article.getTitle());
      //  viewHolder.mDescription.setText(article.getDescription());
        //viewHolder.mImageTitle = article.getUrlToImage();
    }

    @Override
    public int getItemCount() {
        return mArticleList != null ? mArticleList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        TextView mDescription;
        ImageView mImageTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.title_tv);
            mDescription = itemView.findViewById(R.id.description_tv);
            mImageTitle = itemView.findViewById(R.id.image_article);
        }
    }
}