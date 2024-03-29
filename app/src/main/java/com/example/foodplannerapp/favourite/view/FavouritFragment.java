package com.example.foodplannerapp.favourite.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.db.RecipeLocalDataSource;
import com.example.foodplannerapp.db.RecipeLocalDataSourceImp;
import com.example.foodplannerapp.favourite.presenter.FavouritePresenter;
import com.example.foodplannerapp.favourite.presenter.FavouritePresenterImp;
import com.example.foodplannerapp.model.Recipe;
import com.example.foodplannerapp.model.RecipeReposatoryImp;
import com.example.foodplannerapp.network.RecipeRemoteDataSourceImp;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class FavouritFragment extends Fragment implements FavouriteView{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    FavouritePresenter presenter;
    LinearLayoutManager layoutManager;
    FavouriteAdapter favouriteAdapter;

    public FavouritFragment() {
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
        View v=inflater.inflate(R.layout.fragment_favourit, container, false);
        recyclerView=v.findViewById(R.id.rv_list_favorite);
        layoutManager=new LinearLayoutManager(v.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        favouriteAdapter=new FavouriteAdapter(v.getContext(),new ArrayList<>(),this);
        presenter= new FavouritePresenterImp(this, RecipeReposatoryImp.getInstance(RecipeRemoteDataSourceImp.getInstance(), RecipeLocalDataSourceImp.getInstance(getContext())));
        recyclerView.setAdapter(favouriteAdapter);
        presenter.getFavRecipe();
        showData();
        return v;
    }



















    @Override
    public void showData() {

        SharedPreferences preferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        boolean isLogin = preferences.getBoolean("isLogin", false);

        if(isLogin){
//        favouriteAdapter.setRecipe(recipeList);
//        favouriteAdapter.notifyDataSetChanged();
        Flowable<List<Recipe>> Fav = presenter.getFavRecipe();
        Log.d("TAG", "showData: ssssssssssssssssssssssssssssssssssssssssss");
        Fav.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> {
                    if (!meals.isEmpty()) {
//                        Log.d("keep", "after mini" + meals.get(0).getName() + "    " + meals.size());
                        favouriteAdapter.setRecipe(meals);
                    } else {
//                        Log.d("keep", "meals list is empty");
                    }
                });
    }else {
            Toast.makeText(getContext(), "Please Login to see your favourite", Toast.LENGTH_SHORT).show();
        }
    }








}