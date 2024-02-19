package com.example.foodplannerapp.db;

import android.content.Context;

import com.example.foodplannerapp.model.Recipe;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class RecipeLocalDataSourceImp implements RecipeLocalDataSource{
    Completable insertFav;
    private Context context;
    private RecipeDAO  recipeDAO;
    private Flowable<List<Recipe>> storedRecipe;
    private static RecipeLocalDataSourceImp repo = null;

    public RecipeLocalDataSourceImp(Context context) {
        this.context = context;
       RecipeDataBase dataBase = RecipeDataBase.getInstance(context.getApplicationContext());
        recipeDAO = dataBase.getRecipeDAO();
       storedRecipe = recipeDAO.getAllRecipes();
    }

    public static  synchronized RecipeLocalDataSourceImp getInstance(Context _context){
        if(repo == null){
            repo = new RecipeLocalDataSourceImp(_context);
        }
        return repo;
    }

    @Override
    public Completable insertRecipe(Recipe recipe) {
        return recipeDAO.insertRecipe(recipe);
    }

    @Override
    public Completable deleteRecipe(Recipe recipe) {
        return recipeDAO.deleteRecipe(recipe);
    }

    @Override
    public Flowable<List<Recipe>> getAllStoredRecipes() {
        return recipeDAO.getAllRecipes();
    }
}
