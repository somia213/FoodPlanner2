package com.example.foodplannerapp.model;

import com.example.foodplannerapp.area.model.AreaResponse;
import com.example.foodplannerapp.network.NetworkCallBack;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public interface RecipeReposatory {

    public void getAllRecipes(NetworkCallBack networkCallBack);

    Observable<List<Category>> getCategories();

    Observable<List<Recipe>> getAllCategoryMeals(String categoryName);

    Observable<List<Recipe>> getCountry();

    Observable<RecipeResponse> getSearchRecipes();
    Observable<AreaResponse> getAreaRecipes();
    Completable insertFav(Recipe recipe);
    Completable deleteFromFav(Recipe recipe);

    Flowable<List<Recipe>> getFavourites();

}
