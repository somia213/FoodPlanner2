package com.example.foodplannerapp.view.home;

import com.example.foodplannerapp.model.Recipe;

import java.util.List;

public interface AllRecipeView {

    public void showData(Recipe recipes);
    public void showErrMSG(String error);
}
