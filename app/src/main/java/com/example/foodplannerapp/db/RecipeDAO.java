package com.example.foodplannerapp.db;//package com.example.foodplannerapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.foodplannerapp.model.Recipe;
import java.util.List;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface RecipeDAO {
    @Query("SELECT * FROM  Far_table")
    Flowable<List<Recipe>> getAllRecipes();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertRecipe(Recipe recipe);
    @Delete
    Completable deleteRecipe(Recipe recipe);

}
