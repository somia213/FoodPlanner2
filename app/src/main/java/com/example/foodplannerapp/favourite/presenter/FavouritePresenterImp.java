package com.example.foodplannerapp.favourite.presenter;

import com.example.foodplannerapp.favourite.view.FavouriteView;
import com.example.foodplannerapp.model.Recipe;
import com.example.foodplannerapp.model.RecipeReposatory;
import com.example.foodplannerapp.model.RecipeReposatoryImp;
import com.example.foodplannerapp.model.RecipeResponse;
import com.example.foodplannerapp.network.RecipeRemoteDataSourceImp;
import com.example.foodplannerapp.search.presenter.SearchPresenter;
import com.example.foodplannerapp.search.view.SearchView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class FavouritePresenterImp implements FavouritePresenter {
    private FavouriteView _view;
    private RecipeReposatory _repo;
    RecipeRemoteDataSourceImp recipeRemoteDataSourceImp;

    public FavouritePresenterImp(FavouriteView _view, RecipeReposatoryImp _repository) {
        this._view = _view;
        this._repo = _repository;
    }

    @Override
    public Flowable<List<Recipe>> getFavRecipe() {
        return  _repo.getFavourites();


    }


}