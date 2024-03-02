package com.example.foodplannerapp.category;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.Category;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private static final String TAG = "CategoryAdapter";
    Context context;
    List<Category> categories;

    public MyAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }
    public void setCategory(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recipe_list, parent, false);
        MyAdapter.ViewHolder viewHolder = new MyAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: ");
        Category current = categories.get(position);
        holder.categoryName.setText(current.getStrCategory());

        Glide.with(context).load(categories.get(position).getStrCategoryThumb()).into(holder.categoryPhoto);

        holder.cardCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the category clicked
                // Create an Intent to start the activity to show meals in this category
                Intent intent = new Intent(context, CategoryDetails.class);
                intent.putExtra("categoryName", current.getStrCategory());
                intent.putExtra("categoryId", current.getIdCategory());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount: ");
        return categories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryPhoto;
        TextView categoryName;
        public CardView cardCategory;
        public View layout;

        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;
            categoryPhoto = v.findViewById(R.id.thumnail_image);
            categoryName = v.findViewById(R.id.tv_title);
            cardCategory = v.findViewById(R.id.recipe_card);
        }
    }
}
