package com.example.foodplanner.database;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.Meal;

import java.util.List;

public interface MealLocalDataSource {
    void insertMeal (Meal meal);
    void removeMeal (Meal meal);
    LiveData<List<Meal>> getAllMeals ();
}
