package com.elouazzani.myapplicationgemo.model;

import android.content.ClipData;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class GitHubRepo {
    @SerializedName("items")
    private List<Item> items;

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }
}
