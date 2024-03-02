package com.example.foodplannerapp.network;

import com.example.foodplannerapp.area.model.AreaResponse;
import com.example.foodplannerapp.model.Category;
import com.example.foodplannerapp.model.Recipe;
import com.example.foodplannerapp.model.RecipeResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface RecipeRemoteDataSource {

    void makeNetworkCall(NetworkCallBack networkCallBack);

    Observable<RecipeResponse> searchByMeal();

    Observable<AreaResponse> searchByArea();

    Observable<List<Category>> getCategories();

    Observable<List<Recipe>> networkMethodForCategoryMeals(String categoryName);

    Observable<List<Recipe>> getCountry();
}
