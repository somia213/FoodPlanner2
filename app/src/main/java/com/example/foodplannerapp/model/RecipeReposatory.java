package com.example.foodplannerapp.model;

import com.example.foodplannerapp.area.model.AreaModel;
import com.example.foodplannerapp.area.model.AreaResponse;
import com.example.foodplannerapp.network.NetworkCallBack;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public interface RecipeReposatory {

    public void getAllRecipes(NetworkCallBack networkCallBack);
    Observable<RecipeResponse> getSearchRecipes();
    Observable<AreaResponse> getAreaRecipes();
    Completable insertFav(Recipe recipe);
    Flowable<List<Recipe>> getFavourites();

}
