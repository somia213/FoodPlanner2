package com.example.foodplannerapp.network;

import com.example.foodplannerapp.area.model.AreaResponse;
import com.example.foodplannerapp.model.Category;
import com.example.foodplannerapp.model.RecipeResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// step3 call Api
// api -> RecipeResponse
public interface RecipeService {
    @GET("random.php")
    Call<RecipeResponse> getRecipe();

    @GET("search.php?s")
    Observable<RecipeResponse> getSearchMeals();

    @GET("list.php?a=list")
    Observable<AreaResponse> getAreaMeals();

    @GET("categories.php")
    Observable<RecipeResponse> getCategories();
    // second way
    @GET("list.php?a=list")
    Observable<RecipeResponse> getCountry();

    @GET("filter.php")
    Observable<RecipeResponse> getCategoryMeals(@Query("c") String categoryName);



}
