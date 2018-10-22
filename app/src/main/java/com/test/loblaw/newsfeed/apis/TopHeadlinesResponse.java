package com.test.loblaw.newsfeed.apis;

import com.test.loblaw.newsfeed.models.Article;

import java.util.List;

public class TopHeadlinesResponse {
    public String status;
    public int totalResults;
    public List<Article> articles;
}
