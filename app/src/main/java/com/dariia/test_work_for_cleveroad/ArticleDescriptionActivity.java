package com.dariia.test_work_for_cleveroad;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dariia.test_work_for_cleveroad.model.Article;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

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
        TextView textViewDescription = findViewById(R.id.description_articles);
        TextView textViewData = findViewById(R.id.date);
        TextView textViewAuthor = findViewById(R.id.author);
        ImageView imageView = findViewById(R.id.image_description);
        Button button = findViewById(R.id.go_original);

        textViewTitle.setText(mArticle.getTitle());
        textViewDescription.setText(mArticle.getDescription());
        textViewData.setText(getPrettyTime(mArticle.getPublishedAt()));
        textViewAuthor.setText(mArticle.getAuthor());

        Glide.with(ArticleDescriptionActivity.this).load(mArticle.getUrlToImage()).into(imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentUrl = new Intent(Intent.ACTION_VIEW, Uri.parse(mArticle.getUrl()));
                startActivity(intentUrl);
            }
        });
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
}
