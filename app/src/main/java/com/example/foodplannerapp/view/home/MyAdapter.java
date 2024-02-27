
package com.example.foodplannerapp.view.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.Category;
import com.example.foodplannerapp.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private static final String TAG ="MyAdapter";
    Context context;
    List<Category> categoryList;

    public MyAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    public void setCategory (List<Category> categoryList){
        this.categoryList=categoryList;
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
        Category current = categoryList.get(position);
        holder.title.setText(current.getStrCategory());
        Glide.with(context).load(categoryList.get(position).getStrCategoryThumb()).into(holder.photo);
        holder.linearCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Bundle bundle = new Bundle();
               bundle.putString("categoryName",current.getStrCategory());
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView title;
        public LinearLayout linearCard;
        public View layout;

        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;
            photo = v.findViewById(R.id.thumnail_image);
            title = v.findViewById(R.id.tv_title);
            linearCard = v.findViewById(R.id.linearCard);
        }
    }
}
