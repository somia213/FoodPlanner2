
package com.example.foodplannerapp.view.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private static final String TAG ="MyAdapter";
    Context context;
    List<Recipe> recipeList;

    public MyAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
        recipeList = new ArrayList<>();
    }

    public void setRecipe (List<Recipe> recipes){
        this.recipeList=recipes;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recipe_list,parent,false);
        ViewHolder viewHolder =new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        Recipe current = recipeList.get(position);
        holder.title.setText(current.getStrMeal());
        Glide.with(context).load(recipeList.get(position).getStrMealThumb()).into(holder.photo);

    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView title;
        public ConstraintLayout constraintLayout;
        public View layout;

        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;
            photo = v.findViewById(R.id.thumnail_image);
            title = v.findViewById(R.id.tv_title);
            constraintLayout = v.findViewById(R.id.recipe_card);
        }
    }
}
