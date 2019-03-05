package com.example.test_work_for_cleveroad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.test_work_for_cleveroad.adpter.NewsRecycleAdapter;
import com.example.test_work_for_cleveroad.model.Article;
import com.example.test_work_for_cleveroad.model.ListArticles;
import com.example.test_work_for_cleveroad.network.ArticlesListAPI;
import com.example.test_work_for_cleveroad.network.ArticlesListRetrofit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Article> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new NewsRecycleAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);

        ArticlesListAPI listAPI = ArticlesListRetrofit.getApiService();
        Call<ListArticles> call = listAPI.getArticles(ArticlesListRetrofit.API_KEY, "android");
        call.enqueue(new Callback<ListArticles>() {
            @Override
            public void onResponse(Call<ListArticles> call, Response<ListArticles> response) {
                mList.clear();
                mList.addAll(response.body().getArticles());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ListArticles> call, Throwable t) {
                int a = 10;
            }
        });
    }
}
