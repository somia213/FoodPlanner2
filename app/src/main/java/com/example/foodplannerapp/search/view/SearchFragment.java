package com.example.foodplannerapp.search.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.area.view.AreaFragment;
import com.example.foodplannerapp.db.RecipeLocalDataSourceImp;
import com.example.foodplannerapp.model.Recipe;
import com.example.foodplannerapp.model.RecipeReposatoryImp;
import com.example.foodplannerapp.network.RecipeRemoteDataSourceImp;
import com.example.foodplannerapp.presenter.RecipePresenter;
import com.example.foodplannerapp.search.presenter.SearchPresenter;
import com.example.foodplannerapp.search.presenter.SearchPresenterImp;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment implements SearchView{
SearchAdapter myAdapter;
SearchPresenter myPresenter;
RecyclerView recyclerView;
LinearLayoutManager layOutManager;
EditText seachForMeal;
Button searchArea;
RecipePresenter recipePresenter;

    public SearchFragment() {
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        seachForMeal=view.findViewById(R.id.searchEdit);
        recyclerView=view.findViewById(R.id.mealSearch);
        searchArea = view.findViewById(R.id.searchBAreaBtn);
        layOutManager=new LinearLayoutManager(view.getContext());
        layOutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layOutManager);
        myAdapter=new SearchAdapter(view.getContext(),new ArrayList<>(),this);
        searchArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                AreaFragment myfragment = new AreaFragment();

                fragmentTransaction.replace(R.id.frame_layout, myfragment);
                fragmentTransaction.commit();
            }
        });

        myPresenter= new SearchPresenterImp(this, RecipeReposatoryImp.getInstance(RecipeRemoteDataSourceImp.getInstance(), RecipeLocalDataSourceImp.getInstance(getContext())));

        recyclerView.setAdapter(myAdapter);
        myPresenter.getSearchRecipe();
        seachForMeal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                //  Log.i(TAG, "beforeTextChanged: ");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //   Log.i(TAG, "onTextChanged: ");

            }

            @Override
            public void afterTextChanged(Editable s) {
                //  Log.i(TAG, "afterTextChanged: ");
                myAdapter.getFilter().filter(s);

            }
        });
        return view;
    }

    @Override
    public void ShowData(List<Recipe> recipes) {
        myAdapter.setRecipe(recipes);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void getFav(Recipe recipe) {
        if (myPresenter != null) {
            myPresenter.insertFav(recipe);
        }

    }
}