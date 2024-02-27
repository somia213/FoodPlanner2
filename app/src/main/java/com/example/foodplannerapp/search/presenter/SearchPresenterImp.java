package com.example.foodplannerapp.search.presenter;

import android.util.Log;

import com.example.foodplannerapp.model.Recipe;
import com.example.foodplannerapp.model.RecipeReposatory;
import com.example.foodplannerapp.model.RecipeReposatoryImp;
import com.example.foodplannerapp.model.RecipeResponse;
import com.example.foodplannerapp.network.RecipeRemoteDataSourceImp;
import com.example.foodplannerapp.search.view.SearchView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenterImp implements SearchPresenter {
    private SearchView _view;
    private RecipeReposatory _repo;
    RecipeRemoteDataSourceImp recipeRemoteDataSourceImp;

    public SearchPresenterImp(SearchView _view, RecipeReposatoryImp _repository) {
        this._view = _view;
        this._repo = _repository;
    }

    @Override
    public void getSearchRecipe() {
        Observable<RecipeResponse> observable= _repo.getSearchRecipes();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RecipeResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RecipeResponse mealResponse) {
                        _view.ShowData(mealResponse.meals);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
//                        _view.showErrMsg(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void insertFav(Recipe recipe) {
       // return _repo.insertFav(recipe);
        _repo.insertFav(recipe)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                            Log.i("TAG", "insertFav: Successfllyyyyyyyyyyyyyyyyy ");
                        },
                        error -> {
                            Log.i("TAG", "insertFav: Failrdddddddddddddddddddddddd ");
                        }
                );
    }


}
