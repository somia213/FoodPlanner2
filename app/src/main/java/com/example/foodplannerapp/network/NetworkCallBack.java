package com.example.foodplannerapp.network;

import com.example.foodplannerapp.model.Recipe;

import java.util.List;

public interface NetworkCallBack {

    public void onSucessfull( List<Recipe> recipe);
    public void onFileur(String errMsg);
}
