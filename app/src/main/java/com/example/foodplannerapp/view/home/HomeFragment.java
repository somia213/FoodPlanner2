package com.example.foodplannerapp.view.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.db.RecipeLocalDataSourceImp;
import com.example.foodplannerapp.model.Category;
import com.example.foodplannerapp.model.RecipeReposatoryImp;
import com.example.foodplannerapp.startSreen.StartActivity;
import com.example.foodplannerapp.model.Recipe;
import com.example.foodplannerapp.network.RecipeRemoteDataSourceImp;
import com.example.foodplannerapp.presenter.RecipePresenter;
import com.example.foodplannerapp.presenter.RecipePresenterImp;
import com.example.foodplannerapp.view.presenter.AllRecipeView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements AllRecipeView {
 RecyclerView homeRecycleView;
 RecyclerView recyclerView2;
 LinearLayoutManager layoutManager;
 MyAdapter myAdapter;
 CountryAdapter countryAdapter;

    View view;
    Button logOut;
    ImageView imageViewSingleMeal;
    TextView foodSingleName;
    TextView countryName;
    RecipePresenter recipePresenter;
    Recipe meal;
 // localData
    List<Recipe> recipe;
private Context context;
    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment ( Comment bet
        view = inflater.inflate(R.layout.fragment_home, container, false);

         // First Recycle View
         homeRecycleView =view.findViewById(R.id.homeRecycleView);
         imageViewSingleMeal = view.findViewById(R.id.image_thum);

         foodSingleName = view.findViewById(R.id.food_name);
         recipePresenter = new RecipePresenterImp(this , RecipeReposatoryImp.getInstance(RecipeRemoteDataSourceImp.getInstance(), RecipeLocalDataSourceImp.getInstance(view.getContext())));

        myAdapter = new MyAdapter(view.getContext(),new ArrayList<>());
        homeRecycleView.setAdapter(myAdapter);
        layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        homeRecycleView.setLayoutManager(layoutManager);
        recipePresenter.getCategories();

        //Second Recycle view

        recyclerView2 =view.findViewById(R.id.homeRecycleView2);
//        imageViewSingleMeal = view.findViewById(R.id.image_thum);

        countryName = view.findViewById(R.id.mealNameArea);
        recipePresenter = new RecipePresenterImp(this , RecipeReposatoryImp.getInstance(RecipeRemoteDataSourceImp.getInstance(), RecipeLocalDataSourceImp.getInstance(view.getContext())));

        countryAdapter = new CountryAdapter(view.getContext(),new ArrayList<>());
        recyclerView2.setAdapter(countryAdapter);
        recyclerView2 = view.findViewById(R.id.homeRecycleView2);

        layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView2.setLayoutManager(layoutManager);
        recipePresenter.getCountry();

        // LogOut Button
        logOut = view.findViewById(R.id.logOutBtn);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                SharedPreferences preferences = getContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isLogin", false);
                editor.apply();

                // Redirect the user to the login activity
                Intent intent = new Intent(getContext(), StartActivity.class);
                startActivity(intent);
                requireActivity().finish(); // Optional: Close the current activity if needed

            }
        });
        recipePresenter.getRecipe();
        return view;
    }

    @Override
    public void showData(Recipe recipes) {
        if(recipes != null)
        {  Glide.with(imageViewSingleMeal.getContext()).load(recipes.getStrMealThumb()).into(imageViewSingleMeal);
        foodSingleName.setText(recipes.getStrMeal());
        meal= recipes;
        }}

    @Override
    public void showCategories(List<Category> categoryList) {
        myAdapter.setCategory(categoryList);
    }

    @Override
    public void showCountry(List<Recipe> countryList) {
         countryAdapter.setCountry(countryList);
        Log.i("Tag", "showCountry: lllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll");
    }


    @Override
    public void showErrMSG(String error) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An Error Occured");
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    public void getMealData(Recipe recipes) {

    }
}