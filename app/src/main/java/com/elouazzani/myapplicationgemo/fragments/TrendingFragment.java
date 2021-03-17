package com.elouazzani.myapplicationgemo.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elouazzani.myapplicationgemo.MainActivity;
import com.elouazzani.myapplicationgemo.R;
import com.elouazzani.myapplicationgemo.adapter.GitHubRepoAdapter;
import com.elouazzani.myapplicationgemo.client.GitHubClient;
import com.elouazzani.myapplicationgemo.model.GitHubRepo;
import com.elouazzani.myapplicationgemo.model.Item;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class TrendingFragment extends Fragment {
    // TODO: Rename and change types of para
    private GitHubRepoAdapter repositoryAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private List<Item> repList;
    private Subscription subscription;
    private static final String TAG = MainActivity.class.getSimpleName();


    public TrendingFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_trending, container, false);
        layoutManager=new LinearLayoutManager(getContext());
        repList =new ArrayList<>();
        recyclerView=view.findViewById(R.id.trendingRecyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        repositoryAdapter=new GitHubRepoAdapter(getContext());
        recyclerView.setAdapter(repositoryAdapter);
        getTrendingRepo();
        // Inflate the layout for this fragment
        return view;

    }
    private void getTrendingRepo() {
        /* getTrendingRepo return Observable<List<GitHubRepo>> return stream of data
         * subscription is a observer of steam
         * the operator is the retrofit interface
         */
        subscription= GitHubClient.getInstance().getTrendingRepos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GitHubRepo>() {
                    @Override public void onCompleted() {
                        Log.d(TAG, "In onCompleted()");
                    }

                    @Override public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d(TAG, "In onError()");
                    }

                    @Override public void onNext(GitHubRepo gitHubRepos) {
                        repList=gitHubRepos.getItems();
                        Log.d(TAG, "In onNext()"+repList.toString());
                        repositoryAdapter.setGitHubRepos(repList);
                    }
                });


    }

    @Override
    public void onDestroyView() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroyView();
    }

}