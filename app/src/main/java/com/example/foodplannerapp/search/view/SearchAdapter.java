package com.example.foodplannerapp.search.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.area.presenter.AreaInterfaceImp;
import com.example.foodplannerapp.db.RecipeLocalDataSourceImp;
import com.example.foodplannerapp.model.Recipe;
import com.example.foodplannerapp.model.RecipeReposatoryImp;
import com.example.foodplannerapp.network.RecipeRemoteDataSourceImp;
import com.example.foodplannerapp.presenter.RecipePresenter;
import com.example.foodplannerapp.presenter.RecipePresenterImp;
import com.example.foodplannerapp.search.presenter.SearchPresenter;
import com.example.foodplannerapp.view.home.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private static final String TAG = "MyAdapter";
    Context context;
    SearchView searchView;
    List<Recipe> recipeList;
    List<Recipe> recipes;
    RecipePresenter recipePresenter;
    SearchPresenter presenter;

    public SearchAdapter(Context context, List<Recipe> recipeList , SearchView searchView) {
        this.context = context;
        this.recipeList = recipeList;
        recipes = new ArrayList<>();
        this.searchView = searchView;
        this.recipeList = new ArrayList<>();

    }

//    public void setRecipe(List<Recipe> recipes) {
//        this.recipeList = recipes;
//        recipes.addAll(recipes);
//   //     notifyDataSetChanged();
//    }
    public void setRecipe(List<Recipe> recipes) {
        this.recipeList = recipes;
        this.recipes = new ArrayList<>(recipes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.iteam_meal_search_result, parent, false);
        SearchAdapter.ViewHolder viewHolder = new SearchAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        Recipe current = recipeList.get(position);
        holder.title.setText(current.getStrMeal());
        Glide.with(context).load(recipeList.get(position).getStrMealThumb()).into(holder.photo);
        holder.Fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Added to favourite", Toast.LENGTH_SHORT).show();
                searchView.getFav(current);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView title;
        public CardView cardView;
        public View layout;
        ImageView Fav;

        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;
            photo = v.findViewById(R.id.imgMeal);
            title = v.findViewById(R.id.mealName);
            cardView = v.findViewById(R.id.cardView);
            Fav = v.findViewById(R.id.GoToFavImg);
        }
    }
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Recipe> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(recipes);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Recipe meal : recipes) {
                    if (meal.getStrMeal().toLowerCase().contains(filterPattern)) {
                        filteredList.add(meal);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            recipeList.clear();
            recipeList.addAll((List<Recipe>) results.values);
            notifyDataSetChanged();
        }
    };
}


