package com.dariia.test_work_for_cleveroad;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dariia.test_work_for_cleveroad.adpter.NewsRecycleAdapter;
import com.dariia.test_work_for_cleveroad.model.Article;
import com.dariia.test_work_for_cleveroad.model.ArticlesResponse;
import com.dariia.test_work_for_cleveroad.network.ArticlesListAPI;
import com.dariia.test_work_for_cleveroad.network.ArticlesListRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter mAdapter;
    private SwipeRefreshLayout mSwipe;

    public static List<Article> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        mSwipe = findViewById(R.id.swipe);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new NewsRecycleAdapter(mList, mOnItemSelected);
        recyclerView.setAdapter(mAdapter);
        loadArticles();

        mSwipe.setRefreshing(true);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadArticles();
            }
        });
    }

    private void loadArticles(){
        ArticlesListAPI listAPI = ArticlesListRetrofit.getApiService();
        Call<ArticlesResponse> call = listAPI.getArticles(ArticlesListRetrofit.API_KEY, "android");
        call.enqueue(new Callback<ArticlesResponse>() {
            @Override
            public void onResponse(Call<ArticlesResponse> call, Response<ArticlesResponse> response) {
                mList.clear();
                mList.addAll(response.body().getArticles());
                mAdapter.notifyDataSetChanged();
                mSwipe.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<ArticlesResponse> call, Throwable t) {
            }
        });
    }

    private final NewsRecycleAdapter.OnItemSelected mOnItemSelected = new NewsRecycleAdapter.OnItemSelected() {
        @Override
        public void onItemSelected(int position) {
            Intent intent = new Intent(MainActivity.this, ArticleDescriptionActivity.class);
            intent.putExtra(ArticleDescriptionActivity.EXTRA_POSITION, position);
            startActivity(intent);
        }
    };

}
