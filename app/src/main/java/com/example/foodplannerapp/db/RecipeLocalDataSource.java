package com.example.foodplannerapp.db;

import androidx.lifecycle.LiveData;

import com.example.foodplannerapp.model.Recipe;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface RecipeLocalDataSource {
    Completable insertRecipe(Recipe recipe);
    Completable deleteRecipe(Recipe recipe);
    Flowable<List<Recipe>> getAllStoredRecipes();
}
