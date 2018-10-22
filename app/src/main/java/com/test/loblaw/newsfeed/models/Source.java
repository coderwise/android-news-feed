package com.test.loblaw.newsfeed.models;

import android.support.annotation.NonNull;

public class Source {
    public String id;
    public String name;
    public String description;
    public String url;
    public String category;
    public String language;
    public String country;

    @NonNull
    @Override
    public String toString() {
        return name + " : " + description;
    }
}
