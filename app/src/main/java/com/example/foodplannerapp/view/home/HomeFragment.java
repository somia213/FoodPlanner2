package com.example.foodplannerapp.view.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.LoginActivity;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.db.RecipeLocalDataSourceImp;
import com.example.foodplannerapp.model.Recipe;
import com.example.foodplannerapp.model.RecipeReposatoryImp;
import com.example.foodplannerapp.network.RecipeRemoteDataSourceImp;
import com.example.foodplannerapp.presenter.RecipePresenter;
import com.example.foodplannerapp.presenter.RecipePresenterImp;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;


public class HomeFragment extends Fragment implements AllRecipeView {
 RecyclerView recyclerView;
 LinearLayoutManager layoutManager;
 MyAdapter myAdapter;
    View view;
    Button logOut;
    ImageView imageViewSingleMeal;
    TextView foodSingleName;
    RecipePresenter recipePresenter;
    Recipe Fav_recipe;
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
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
         imageViewSingleMeal = view.findViewById(R.id.image_thum);
         foodSingleName = view.findViewById(R.id.food_name);
         recipePresenter = new RecipePresenterImp(this , new RecipeRemoteDataSourceImp());
       // recipePresenter = new RecipePresenterImp( this,RecipeReposatoryImp.getInstance(RecipeRemoteDataSourceImp.getInstance(), RecipeLocalDataSourceImp.getInstance(getContext())));
//        logOut = view.findViewById(R.id.logOutBtn);
//        logOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//
//                // Redirect the user to the login activity
//                Intent intent = new Intent(getContext(), LoginActivity.class);
//                startActivity(intent);
//                requireActivity().finish(); // Optional: Close the current activity if needed
//            }
//        });
        recipePresenter.getRecipe();
//            TextView plane_btn = view.findViewById(R.id.plane_btn);
//            ImageButton fav_btn = view.findViewById(R.id.fav_btn);
////            Fav_recipe = recipe.get(0);
//             fav_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (Fav_recipe != null) {
//                        recipePresenter.insertFav(Fav_recipe);
//                    }
//                }
//            });

        return view;
    }

    @Override
    public void showData(Recipe recipes) {
        Glide.with(imageViewSingleMeal.getContext()).load(recipes.getStrMealThumb()).into(imageViewSingleMeal);
        foodSingleName.setText(recipes.getStrMeal());


        }


    @Override
    public void showErrMSG(String error) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An Error Occured");
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}