package com.elouazzani.myapplicationgemo.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

    private GitHubRepoAdapter repositoryAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private List<Item> repList;
    private Subscription subscription;
    private static final String TAG = MainActivity.class.getSimpleName();

    // pagination
    private int page_number=1;
    private int firstVisibleItem;
    private int totalItemCount;
    private int visibleItemCount;
    private int previousTotal;
    private boolean load=true;

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
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_trending, container, false);
        layoutManager=new LinearLayoutManager(getContext());
        repList =new ArrayList<>();
        recyclerView=view.findViewById(R.id.trendingRecyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        repositoryAdapter=new GitHubRepoAdapter(getContext());
        recyclerView.setAdapter(repositoryAdapter);
        pagination();
        getTrendingRepo();

        return view;

    }
    private void getTrendingRepo() {
        /* getTrendingRepo return Observable<List<GitHubRepo>> return stream of data
         * subscription is a observer of steam
         */
        subscription= GitHubClient.getInstance().getTrendingRepos(page_number)
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
                        Log.d(TAG, "In onNext()"+" page="+page_number+" data="+repList.toString());
                        repositoryAdapter.setGitHubRepos(repList);
                        Log.d(TAG, "rep list size="+repositoryAdapter.getItemCount());
                    }
                });


    }
    // recycler view pagination
    private void pagination() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstVisibleItem=layoutManager.findFirstVisibleItemPosition();
                totalItemCount=layoutManager.getChildCount();
                visibleItemCount=layoutManager.getItemCount();

                if (load) {
                    if(totalItemCount>previousTotal) {
                        previousTotal=totalItemCount;
                        page_number++;
                        load=false;
                    }
                }

                if(!load && (visibleItemCount+firstVisibleItem)>=totalItemCount) {
                    getNext();
                    load=true;
                    Log.v("tag","Page Number:"+page_number);
                }
            }
        });
    }

    // load data from next page
    private void getNext() {
        getTrendingRepo();
    }

    @Override
    public void onStop() {
        Toast.makeText(getContext(),"On stop",Toast.LENGTH_SHORT).show();
        page_number=1;
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroyView();
    }

}