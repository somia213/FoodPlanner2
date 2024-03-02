package com.example.foodplannerapp.category;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.Category;
import com.example.foodplannerapp.model.Recipe;
import com.example.foodplannerapp.view.presenter.AllRecipeView;

import java.util.ArrayList;
import java.util.List;

public class CategoryDetails extends Fragment implements AllRecipeView{

    RecyclerView recyclerViewMealsByCategory;
    LinearLayoutManager linearLayoutManager;
    MyAdapter categoriesAdapter;
    AllRecipeView allMealsPresenter;
    String categoryName;
    View view;

    public CategoryDetails() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_category_details, container, false);

        recyclerViewMealsByCategory = view.findViewById(R.id.recyclerView_mealsByCategory);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewMealsByCategory.setLayoutManager(linearLayoutManager);

        Bundle extra = getArguments();
        if (extra != null) {
            categoryName = extra.getString("categoryName");
        }

//        //Adapter
//        categoriesAdapter = new MyAdapter(getContext(), new ArrayList<>(), getContext());
//        recyclerViewMealsByCategory.setAdapter(categoriesAdapter);

        return null;
    }

    @Override
    public void showData(Recipe recipes) {

    }

    @Override
    public void showCategories(List<Category> categoryList) {

    }

    @Override
    public void showMealsDetails(List<Recipe> recipeDetal) {

    }

    @Override
    public void showCountry(List<Recipe> countryList) {

    }

    @Override
    public void showErrMSG(String error) {

    }

    @Override
    public void getMealData(Recipe recipes) {

    }
}