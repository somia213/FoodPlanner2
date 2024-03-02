package com.example.foodplannerapp.network;

import android.util.Log;

import com.example.foodplannerapp.area.model.AreaResponse;
import com.example.foodplannerapp.model.Category;
import com.example.foodplannerapp.model.Recipe;
import com.example.foodplannerapp.model.RecipeResponse;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


// Step1 in download api
public class RecipeRemoteDataSourceImp implements RecipeRemoteDataSource{

    public static final String TAG = "Network";
    private static final String URL = "https://www.themealdb.com/api/json/v1/1/";
    private static RecipeRemoteDataSourceImp client = null; // single ton
     RecipeService recipeService;

    public RecipeRemoteDataSourceImp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build();
                  recipeService = retrofit.create(RecipeService.class);
    }

    public static RecipeRemoteDataSourceImp getInstance() {
        if (client == null) {
            client = new RecipeRemoteDataSourceImp();
        }
        return client;
    }


    @Override
    public void makeNetworkCall(NetworkCallBack networkCallBack) {
        Call<RecipeResponse> call = recipeService.getRecipe();
        call.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {

                if (response.isSuccessful()) {
                    networkCallBack.onSucessfull(response.body().meals);
                    Log.i(TAG, "onResponse: " + response.body() + " recipe successfully downloaded");
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                networkCallBack.onFileur(t.getMessage());
                Log.i(TAG, "onResponse: " + " data Field to downloaded");

            }
        });
    }

    @Override
    public Observable<RecipeResponse> searchByMeal() {
        Observable<RecipeResponse> observable = recipeService.getSearchMeals();
        return observable.subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<AreaResponse> searchByArea() {
        Observable<AreaResponse> observable = recipeService.getAreaMeals();
        return observable.subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<Category>> getCategories() {
        Observable<RecipeResponse> observable = recipeService.getCategories();
        return observable.flatMap(categoryResponse->Observable.just(categoryResponse.categories));
    }


    @Override
    public Observable<List<Recipe>> networkMethodForCategoryMeals(String categoryName) {
        Observable<RecipeResponse> observable = recipeService.getCategoryMeals(categoryName);
        return observable.flatMap(recipeResponse->Observable.just(recipeResponse.meals));
    }

    @Override
    public Observable<List<Recipe>> getCountry() {
        Observable<RecipeResponse> observable = recipeService.getCountry();
        if (observable != null) {
            return observable.flatMap(countryResponse -> {
                if (countryResponse != null) {
                    Log.i("Tag", "Country Response: " + countryResponse.toString());
                    if (countryResponse.meals != null) {
                        Log.i("Tag", "Country List Size: " + countryResponse.meals.size());
                        return Observable.just(countryResponse.meals);
                    } else {
                        Log.e("Tag", "Country List is null");
                        return Observable.error(new NullPointerException("Country list is null"));
                    }
                } else {
                    Log.e("Tag", "Country Response is null");
                    return Observable.error(new NullPointerException("Country response is null"));
                }
            });
        } else {
            Log.e("Tag", "Observable is null");
            return Observable.error(new NullPointerException("Observable is null"));
        }
    }

}
