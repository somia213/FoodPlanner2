package com.example.foodplannerapp.area.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.area.model.AreaModel;
import com.example.foodplannerapp.area.presenter.AreaInterfaceImp;
import com.example.foodplannerapp.area.presenter.AreaPresenter;
import com.example.foodplannerapp.db.RecipeLocalDataSource;
import com.example.foodplannerapp.db.RecipeLocalDataSourceImp;
import com.example.foodplannerapp.model.Recipe;
import com.example.foodplannerapp.model.RecipeReposatoryImp;
import com.example.foodplannerapp.network.RecipeRemoteDataSourceImp;
import com.example.foodplannerapp.search.presenter.SearchPresenter;
import com.example.foodplannerapp.search.presenter.SearchPresenterImp;
import com.example.foodplannerapp.search.view.SearchAdapter;
import com.example.foodplannerapp.search.view.SearchView;

import java.util.ArrayList;
import java.util.List;

public class AreaFragment extends Fragment implements AreaInterface {
    AreaAdapter myAdapter;
    AreaPresenter myPresenter;
    RecyclerView recyclerView;
    LinearLayoutManager layOutManager;
    EditText seachForMeal;

    public AreaFragment() {
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
        View view = inflater.inflate(R.layout.fragment_area, container, false);
        seachForMeal = view.findViewById(R.id.searchEdit);
        recyclerView = view.findViewById(R.id.mealSearch);
        layOutManager = new LinearLayoutManager(view.getContext());
        layOutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layOutManager);
        myAdapter = new AreaAdapter(view.getContext(), new ArrayList<>(), this);

        myPresenter = new AreaInterfaceImp(this, RecipeReposatoryImp.getInstance(RecipeRemoteDataSourceImp.getInstance(), RecipeLocalDataSourceImp.getInstance(getContext())));

        recyclerView.setAdapter(myAdapter);
        myPresenter.getAreaRecipe();
        seachForMeal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //  Log.i(TAG, "beforeTextChanged: ");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myAdapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {
                //  Log.i(TAG, "afterTextChanged: ");

            }
        });
        return view;
    }

    @Override
    public void ShowData(List<AreaModel> recipes) {
        myAdapter.setRecipe(recipes);
        myAdapter.notifyDataSetChanged();
    }
}