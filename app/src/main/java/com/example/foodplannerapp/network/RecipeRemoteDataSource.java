package com.example.foodplannerapp.network;

import com.example.foodplannerapp.area.model.AreaResponse;
import com.example.foodplannerapp.model.RecipeResponse;

import io.reactivex.rxjava3.core.Observable;

public interface RecipeRemoteDataSource {

    void makeNetworkCall(NetworkCallBack networkCallBack);

    Observable<RecipeResponse> searchByMeal();

    Observable<AreaResponse> searchByArea();
}
