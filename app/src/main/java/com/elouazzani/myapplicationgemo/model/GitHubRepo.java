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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }
}
