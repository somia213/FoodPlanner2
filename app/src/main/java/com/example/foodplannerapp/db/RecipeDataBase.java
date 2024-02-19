package com.example.foodplannerapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplannerapp.model.Recipe;

@Database(entities = {Recipe.class},version = 1)
public abstract class RecipeDataBase extends RoomDatabase {
    private static RecipeDataBase instance =null;
    public abstract RecipeDAO getRecipeDAO();
    public static synchronized RecipeDataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),RecipeDataBase.class,"RecipesDB").build();
        }
        return instance;
    }
}
