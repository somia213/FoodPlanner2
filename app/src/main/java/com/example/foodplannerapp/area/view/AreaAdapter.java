package com.example.foodplannerapp.area.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.area.model.AreaModel;
import com.example.foodplannerapp.model.Recipe;
import com.example.foodplannerapp.search.view.SearchAdapter;
import com.example.foodplannerapp.search.view.SearchView;

import java.util.ArrayList;
import java.util.List;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.ViewHolder>  {
    private static final String TAG = "MyAdapter";
    Context context;
    AreaFragment areaView;
    List<AreaModel> recipeList;
    List<AreaModel> recipes;

    public AreaAdapter(Context context, List<AreaModel> recipeList , AreaFragment AreaFragment) {
        this.context = context;
        this.recipeList = recipeList;
        recipes = new ArrayList<>();
        this.areaView = AreaFragment;
        this.recipeList = new ArrayList<>();
    }

    //    public void setRecipe(List<Recipe> recipes) {
//        this.recipeList = recipes;
//        recipes.addAll(recipes);
//   //     notifyDataSetChanged();
//    }
    public void setRecipe(List<AreaModel> recipes) {
        this.recipeList = recipes;
        this.recipes = new ArrayList<>(recipes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AreaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.area_list, parent, false);
        AreaAdapter.ViewHolder viewHolder = new AreaAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AreaAdapter.ViewHolder holder, int position) {
        AreaModel current = recipeList.get(position);
        holder.title.setText(current.getStrArea());

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

        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;
            photo = v.findViewById(R.id.imgMeal);
            title = v.findViewById(R.id.mealName);
            cardView = v.findViewById(R.id.cardView);
        }
    }
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<AreaModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(recipes);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (AreaModel meal : recipes) {
                    if (meal.getStrArea().toLowerCase().contains(filterPattern)) {
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
            recipeList.addAll((List<AreaModel>) results.values);
            notifyDataSetChanged();
        }
    };
}

