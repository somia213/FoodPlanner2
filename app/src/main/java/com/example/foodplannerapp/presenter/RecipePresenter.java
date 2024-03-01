package com.example.foodplannerapp.presenter;

import com.example.foodplannerapp.model.Recipe;

import io.reactivex.rxjava3.core.Completable;

public interface RecipePresenter {
    public void getRecipe();

    void getCategories();

    void getCountry();
//    public Completable insertFav(Recipe recipe);

}
