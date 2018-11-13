package com.test.loblaw.newsfeed;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.test.loblaw.newsfeed.fragments.DetailsFragment;
import com.test.loblaw.newsfeed.fragments.ListFragment;

public class NavigationHelper {
    public static void goToListFragment(FragmentManager fragmentManager) {
        Fragment fragment = fragmentManager.findFragmentByTag(ListFragment.TAG);

        if (fragment == null) {
            fragment = ListFragment.newInstance();
        }
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_placeholder, fragment, ListFragment.TAG)
                .commit();
    }

    public static void goToDetailsFragment(FragmentManager fragmentManager, String content) {
        Fragment fragment = fragmentManager.findFragmentByTag(DetailsFragment.TAG);

        if (fragment == null) {
            fragment = DetailsFragment.newInstance(content);
        }
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_placeholder, fragment, DetailsFragment.TAG)
                .addToBackStack(DetailsFragment.TAG)
                .commit();
    }
}
