package com.example.foodplannerapp.favourite.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.favourite.presenter.FavouritePresenterImp;
import com.example.foodplannerapp.model.Recipe;
import com.example.foodplannerapp.model.RecipeReposatoryImp;
import com.example.foodplannerapp.search.view.SearchAdapter;
import com.example.foodplannerapp.search.view.SearchView;

import java.util.ArrayList;
import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {
    private static final String TAG = "MyAdapter";
    Context context;
    FavouriteView favouriteView;
    List<Recipe> recipeList;
    List<Recipe> recipes;

    public FavouriteAdapter(Context context, List<Recipe> recipeList, FavouriteView favouriteView) {
        this.context = context;
        this.recipeList = recipeList;
        recipes = new ArrayList<>();
        this.favouriteView = favouriteView;
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
    public FavouriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.favourite_list, parent, false);

        FavouriteAdapter.ViewHolder viewHolder = new FavouriteAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.ViewHolder holder, int position) {
        Recipe current = recipeList.get(position);
        holder.title.setText(current.getStrMeal());
        Glide.with(context).load(recipeList.get(position).getStrMealThumb()).into(holder.photo);
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              FavouritePresenterImp.removeFrom(current);
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
        ImageView remove;

        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;
            photo = v.findViewById(R.id.mealImageView);
            title = v.findViewById(R.id.nameDetailsTextView);
            cardView = v.findViewById(R.id.fav_crd);
            remove = v.findViewById(R.id.addToFavoriteButton);
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
                filteredList.addAll(recipes); // Add all meals if the search query is empty
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Recipe meal : recipes) {
                    if (meal.getStrMeal().toLowerCase().contains(filterPattern)) {
                        filteredList.add(meal); // Add meal to filtered list if its name contains the search query
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