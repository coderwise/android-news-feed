package com.test.loblaw.newsfeed.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.loblaw.newsfeed.R;
import com.test.loblaw.newsfeed.databinding.FragmentDetailsBinding;
import com.test.loblaw.newsfeed.viewmodels.DetailsFragmentViewModel;

public class DetailsFragment extends Fragment {
    public static final String TAG = "DetailsFragment";
    private static final String ARG_CONTENT = "arg_content";

    private String content;
    private DetailsFragmentViewModel viewModel;


    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(String content) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CONTENT, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            content = getArguments().getString(ARG_CONTENT);
        }
        viewModel = ViewModelProviders.of(this).get(DetailsFragmentViewModel.class);

        if (content != null) {
            viewModel.content.set(content);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDetailsBinding binding = DataBindingUtil.inflate(
                getLayoutInflater(), R.layout.fragment_details, container, false);

        binding.setModel(viewModel);
        viewModel.initSubscriptions();

        return binding.getRoot();
    }
}
