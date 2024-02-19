package com.example.foodplannerapp.search.view;

import com.example.foodplannerapp.model.Recipe;

import java.util.List;

public interface SearchView {
       public void ShowData(List<Recipe> recipes);
       public void getFav(Recipe recipe);
}
