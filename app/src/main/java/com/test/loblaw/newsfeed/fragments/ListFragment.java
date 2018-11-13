package com.test.loblaw.newsfeed.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.loblaw.newsfeed.NavigationHelper;
import com.test.loblaw.newsfeed.R;
import com.test.loblaw.newsfeed.databinding.ViewHeadlineItemBinding;
import com.test.loblaw.newsfeed.models.Article;
import com.test.loblaw.newsfeed.viewmodels.ListFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    public static final String TAG = "ListFragment";

    private ListFragmentViewModel viewModel;
    private HeadlinesAdapter adapter;

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(ListFragmentViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        setupUi(view);

        return view;
    }

    private void setupUi(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new HeadlinesAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setItemClickListener(article ->
                NavigationHelper.goToDetailsFragment(getFragmentManager(), article.description));

        viewModel.getArticles(articles -> adapter.update(articles));
    }

    private class HeadlinesAdapter extends RecyclerView.Adapter<ViewHolder> {

        List<Article> items = new ArrayList<>();
        private ItemClickListener itemClickListener;

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

            ViewHeadlineItemBinding itemBinding =
                    ViewHeadlineItemBinding.inflate(layoutInflater, viewGroup, false);
            return new ViewHolder(itemBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
            Article item = items.get(position);
            viewHolder.bind(item, itemClickListener);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        void update(List<Article> articles) {
            items.clear();
            items.addAll(articles);
            notifyDataSetChanged();
        }

        void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final ObservableField<String> title = new ObservableField<>();
        private final ViewHeadlineItemBinding binding;

        ViewHolder(@NonNull ViewHeadlineItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(final Article article, final ItemClickListener itemClickListener) {
            binding.setModel(this);
            binding.setHandler(view -> itemClickListener.onItemClicked(article));
            binding.executePendingBindings();

            title.set(article.title);
        }
    }

    public interface ClickHandler {
        void onClicked(View view);
    }

    private interface ItemClickListener {
        void onItemClicked(Article article);
    }
}
