package com.example.foodplannerapp.model;

import com.example.foodplannerapp.area.model.AreaResponse;
import com.example.foodplannerapp.db.RecipeLocalDataSource;
import com.example.foodplannerapp.network.NetworkCallBack;
import com.example.foodplannerapp.network.RecipeRemoteDataSource;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public class RecipeReposatoryImp implements RecipeReposatory{

    RecipeRemoteDataSource recipeRemoteDataSource;
    RecipeLocalDataSource recipeLocalDataSource;
    private static RecipeReposatoryImp repo =null;

    public RecipeReposatoryImp(RecipeRemoteDataSource recipeRemoteDataSource ,RecipeLocalDataSource localDataSource) {
        this.recipeRemoteDataSource = recipeRemoteDataSource;
        this.recipeLocalDataSource = localDataSource;
    }

    public static RecipeReposatoryImp getInstance(RecipeRemoteDataSource remoteSource ,RecipeLocalDataSource localDataSource){
        if(repo == null){
            repo = new RecipeReposatoryImp(remoteSource , localDataSource);

        }
        return repo;
    }

    @Override
    public void getAllRecipes(NetworkCallBack networkCallBack) {
        recipeRemoteDataSource.makeNetworkCall(networkCallBack);
    }

    @Override
    public Observable<List<Category>> getCategories() {
        return recipeRemoteDataSource.getCategories();
    }

    @Override
    public Observable<List<Recipe>> getCountry() {
        return recipeRemoteDataSource.getCountry();
    }

    @Override
    public Observable<RecipeResponse> getSearchRecipes() {
        return recipeRemoteDataSource.searchByMeal();
            }

    @Override
    public Observable<AreaResponse> getAreaRecipes() {
        return recipeRemoteDataSource.searchByArea();
    }

    @Override
    public Completable insertFav(Recipe recipe) {
        return recipeLocalDataSource.insertRecipe(recipe);
    }

    @Override
    public Completable deleteFromFav(Recipe recipe) {
        return recipeLocalDataSource.deleteRecipe(recipe);
    }

    @Override
    public Flowable<List<Recipe>> getFavourites() {
        return recipeLocalDataSource.getAllStoredRecipes();
    }

}
