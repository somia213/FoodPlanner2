package com.example.foodplannerapp.view.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.Recipe;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder>{
    private static final String TAG ="CountryAdapter";
    Context context;
    List<Recipe> countryList;

    public CountryAdapter(Context context, List<Recipe> countryList) {
        this.context = context;
        this.countryList = countryList;
    }

    public void setCountry (List<Recipe> countryList){
        this.countryList=countryList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.area_list,parent,false);
        ViewHolder viewHolder =new ViewHolder(view);
        Log.i(TAG, "onCreateViewHolder: kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.ViewHolder holder, int position) {
        Recipe current = countryList.get(position);
        holder.title.setText(current.getStrArea());
//        Glide.with(context).load(countryList.get(position).getStrCategoryThumb()).into(holder.photo);
        holder.linearCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("countryName",current.getStrArea());
            }
        });

    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView photo;
        TextView title;
        public CardView linearCard;
        public View layout;

        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;
//            photo = v.findViewById(R.id.thumnail_image);
            title = v.findViewById(R.id.mealNameArea);
            linearCard = v.findViewById(R.id.cardView);
        }
    }
}

