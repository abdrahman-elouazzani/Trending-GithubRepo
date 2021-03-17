package com.elouazzani.myapplicationgemo.service;

import com.elouazzani.myapplicationgemo.model.GitHubRepo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GitHubService {
    @GET("search/repositories?q=created:>2017-10-22&sort=stars&order=desc")
    Observable<GitHubRepo> getTrendingRepositories();

}
