package com.test.loblaw.newsfeed;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.test.loblaw.newsfeed.apis.NewsService;
import com.test.loblaw.newsfeed.apis.TopHeadlinesResponse;
import com.test.loblaw.newsfeed.models.Article;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private NewsService mNewsService = NewsService.Creator.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadNews();
    }

    private void loadNews() {
        mNewsService.getTopHeadlines(NewsService.API_KEY, NewsService.COUNTRY_US)
                .enqueue(new Callback<TopHeadlinesResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<TopHeadlinesResponse> call,
                                           @NonNull Response<TopHeadlinesResponse> response) {
                        Log.i(TAG, "onResponse");
                        Log.i(TAG, response.body().status);
                        for (Article article : response.body().articles) {
                            Log.i(TAG, article.toString());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<TopHeadlinesResponse> call,
                                          @NonNull Throwable t) {
                        Log.e(TAG, "onFailure");
                    }
                });
    }
}
