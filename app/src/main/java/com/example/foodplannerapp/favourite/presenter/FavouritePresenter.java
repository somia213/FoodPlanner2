package com.example.foodplannerapp.favourite.presenter;

import com.example.foodplannerapp.model.Recipe;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface FavouritePresenter {

    public Flowable<List<Recipe>> getFavRecipe();
    public void remove( Recipe recipe);
    }


