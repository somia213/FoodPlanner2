package com.example.foodplannerapp.search.presenter;

import com.example.foodplannerapp.model.Recipe;

import io.reactivex.rxjava3.core.Completable;

public interface SearchPresenter {
    public void getSearchRecipe();
    public void insertFav(Recipe recipe);
}
