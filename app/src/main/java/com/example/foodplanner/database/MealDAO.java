package com.example.foodplanner.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.model.Meal;

import java.util.List;
@Dao
public interface MealDAO {
    @Query("Select * FROM meals_table")
    LiveData<List<Meal>> getAllMeals ();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal (Meal meal);
    @Delete
    void deleteMeal(Meal meal);
}
