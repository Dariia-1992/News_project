package com.example.test_work_for_cleveroad.adpter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.test_work_for_cleveroad.R;
import com.example.test_work_for_cleveroad.model.Article;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class NewsRecycleAdapter extends RecyclerView.Adapter<NewsRecycleAdapter.ViewHolder> {

    public interface OnItemSelected{
        void onItemSelected(int position);
    }

    private List<Article> mArticleList;
    private OnItemSelected mListener;

    public NewsRecycleAdapter(List<Article> articleList, OnItemSelected listener) {
        mArticleList = articleList;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.articles_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Article article = mArticleList.get(i);
        viewHolder.mTitle.setText(article.getTitle());


        viewHolder.mDate.setText(getPrettyTime(article.getPublishedAt()));
        viewHolder.mAuthor.setText(article.getAuthor());

        Glide.with(viewHolder.itemView)
                .load(article.getUrlToImage())
                .into(viewHolder.mImageTitle);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null)
                    mListener.onItemSelected(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArticleList != null ? mArticleList.size() : 0;
    }

    private String getPrettyTime(String date) {
        try {
            Calendar cal = Calendar.getInstance(TimeZone.getDefault());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            sdf.setCalendar(cal);
            cal.setTime(sdf.parse(date));

            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY/dd/MM HH:mm a");
            return dateFormat.format(cal.getTime());
        } catch (Exception ex) {
            return date;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        ImageView mImageTitle;
        TextView mDate;
        TextView mAuthor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.title_tv);
            mImageTitle = itemView.findViewById(R.id.image_article);
            mDate = itemView.findViewById(R.id.date);
            mAuthor = itemView.findViewById(R.id.author);
        }
    }
}