package com.test.loblaw.newsfeed.apis;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface NewsService {

    String API_URL = "https://newsapi.org/v2/";
    String API_KEY = "15ac8a0f7f4e4596b149a7fafd528b33";
    String COUNTRY_US = "us";

    @GET("sources")
    Call<SourcesResponse> getNews(@Query("apiKey") String apiKey);

    @GET("top-headlines")
    Call<TopHeadlinesResponse> getTopHeadlines(@Query("apiKey") String apiKey,
                                          @Query("country") String country);

    class Creator {
        public static NewsService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit.create(NewsService.class);
        }
    }
}
