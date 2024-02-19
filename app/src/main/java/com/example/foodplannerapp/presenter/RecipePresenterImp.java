package com.example.foodplannerapp.presenter;

import android.content.Context;

import com.example.foodplannerapp.model.Recipe;
import com.example.foodplannerapp.model.RecipeReposatory;
import com.example.foodplannerapp.network.NetworkCallBack;
import com.example.foodplannerapp.network.RecipeRemoteDataSourceImp;
import com.example.foodplannerapp.view.home.AllRecipeView;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class RecipePresenterImp implements RecipePresenter , NetworkCallBack {
    private AllRecipeView _view;
    private RecipeReposatory _repo;
    RecipeRemoteDataSourceImp recipeRemoteDataSourceImp;
    Context context;

    public RecipePresenterImp(AllRecipeView _view, RecipeRemoteDataSourceImp _repository) {
        this._view = _view;
        this.recipeRemoteDataSourceImp = _repository;

    }
    @Override
    public void getRecipe() {
        recipeRemoteDataSourceImp.makeNetworkCall(this);
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
