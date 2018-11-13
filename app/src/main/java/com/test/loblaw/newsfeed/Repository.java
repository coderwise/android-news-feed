package com.test.loblaw.newsfeed;

import android.support.annotation.NonNull;

import com.test.loblaw.newsfeed.apis.NewsService;
import com.test.loblaw.newsfeed.apis.TopHeadlinesResponse;
import com.test.loblaw.newsfeed.models.Article;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private static Repository sInstance;
    private NewsService mNewsService = NewsService.Creator.create();

    public static Repository getInstance() {
        if (sInstance == null) {
            sInstance = new Repository();
        }
        return sInstance;
    }

    public void getArticles(final ArticlesHandler handler) {
        mNewsService.getTopHeadlines(NewsService.API_KEY, NewsService.COUNTRY_US)
                .enqueue(new Callback<TopHeadlinesResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<TopHeadlinesResponse> call,
                                           @NonNull Response<TopHeadlinesResponse> response) {
                        if (response.body().articles != null) {
                            handler.onArticles(response.body().articles);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<TopHeadlinesResponse> call,
                                          @NonNull Throwable t) {
                        //Log.e(TAG, "onFailure");
                    }
                });
    }

    public interface ArticlesHandler {
        void onArticles(List<Article> articles);
    }
}
