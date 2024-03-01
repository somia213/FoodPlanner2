package com.example.foodplannerapp.presenter;

import android.content.Context;
import android.util.Log;

import com.example.foodplannerapp.model.Category;
import com.example.foodplannerapp.model.Recipe;
import com.example.foodplannerapp.model.RecipeReposatoryImp;
import com.example.foodplannerapp.network.NetworkCallBack;
import com.example.foodplannerapp.network.RecipeRemoteDataSourceImp;
import com.example.foodplannerapp.view.presenter.AllRecipeView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RecipePresenterImp implements RecipePresenter , NetworkCallBack {
    private AllRecipeView _view;
     RecipeReposatoryImp _repo;
    RecipeRemoteDataSourceImp recipeRemoteDataSourceImp;
    Context context;

    public RecipePresenterImp(AllRecipeView _view, RecipeReposatoryImp _repository) {
        this._view = _view;
        this._repo = _repository;

    }
    @Override
    public void getRecipe() {
       _repo.getAllRecipes(this);
    }

    @Override
    public void getCategories() {
        Observable<List<Category>> observabe = _repo.getCategories();
        observabe.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categories -> {
                    _view.showCategories(categories);
                });
    }

    @Override
    public void getCountry() {
        Observable<List<Recipe>> observabe = _repo.getCountry();
        observabe.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(country -> {
                    _view.showCountry(country);
                });
        if(observabe == null){
            Log.i("Tag", "getCountry: emptyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy ");
        }else{
            Log.i("Tag", "getCountry: Dataaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa ");
        }
    }

//    @Override
//    public Completable insertFav(Recipe recipe) {
//        return _repo.insertFav(recipe);
//    }

    @Override
    public void onSucessfull(List<Recipe> recipe) {
     if (recipe.get(0) != null)
     {  _view.showData(recipe.get(0));

    }}

    @Override
    public void onFileur(String errMsg) {
          _view.showErrMSG(errMsg);
    }


}
