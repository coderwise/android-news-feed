package com.test.loblaw.newsfeed.viewmodels;

import android.arch.lifecycle.ViewModel;

import com.test.loblaw.newsfeed.Repository;

public class ListFragmentViewModel extends ViewModel {

    public void getArticles(Repository.ArticlesHandler handler) {
        Repository repository = Repository.getInstance();
        repository.getArticles(handler);
    }
}
