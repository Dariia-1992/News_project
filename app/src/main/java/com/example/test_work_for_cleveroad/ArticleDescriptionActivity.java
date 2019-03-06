package com.example.test_work_for_cleveroad;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.test_work_for_cleveroad.model.Article;

public class ArticleDescriptionActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "position";

    private Article mArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_description);

        Intent intent = getIntent();
        int position = intent.getIntExtra(EXTRA_POSITION, -1);
        if (position == -1){
            finish();
            return;
        }
        mArticle = MainActivity.mList.get(position);

        TextView textViewTitle = findViewById(R.id.title_articles);
        TextView textViewContent = findViewById(R.id.content_articles);
        ImageView imageView = findViewById(R.id.image_description);
        Button button = findViewById(R.id.go_original);

        textViewTitle.setText(mArticle.getTitle());
        textViewContent.setText(mArticle.getContent());
        Glide.with(ArticleDescriptionActivity.this).load(mArticle.getUrlToImage()).into(imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentUrl = new Intent(Intent.ACTION_VIEW, Uri.parse(mArticle.getUrl()));
                startActivity(intentUrl);
            }
        });
    }
}
