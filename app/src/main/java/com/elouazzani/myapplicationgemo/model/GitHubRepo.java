package com.elouazzani.myapplicationgemo.model;

public class GitHubRepo {
    public  int id;
    public  String name;
    public  String description;
    public  String avatar_url;
    public  String login;
    public  int stargazersCount;

    public GitHubRepo(int id, String name, String description, String avatar_url, String login, int stargazersCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.avatar_url = avatar_url;
        this.login = login;
        this.stargazersCount = stargazersCount;
    }
}
