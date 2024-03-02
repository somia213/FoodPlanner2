package com.example.foodplannerapp.view.presenter;

import com.example.foodplannerapp.model.Category;
import com.example.foodplannerapp.model.Recipe;

import java.util.List;

public interface AllRecipeView {

    public void showData(Recipe recipes);
    public void showCategories(List<Category> categoryList);
    public void showMealsDetails(List<Recipe>recipeDetal);

    public void showCountry(List<Recipe> countryList);

    public void showErrMSG(String error);
    public void getMealData(Recipe recipes);
}
