package com.test.loblaw.newsfeed.models;

import android.support.annotation.NonNull;

import java.net.URL;

public class Article {
    public String author;
    public String title;
    public String description;
    public URL url;
    public URL urlToImage;
    public String publishedAt;
    public String content;
    public Source source;

    @NonNull
    @Override
    public String toString() {
        return title + " : " + description;
    }
}
