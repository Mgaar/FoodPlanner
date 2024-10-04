package com.example.foodplanner.database;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.PlannedMeal;

import java.util.List;

public interface MealLocalDataSource {
    void insertMeal (Meal meal);
    void removeMeal (Meal meal);
    LiveData<List<Meal>> getAllMeals ();
    void insertPlannedMeal (PlannedMeal meal);
    void deletePlannedMeal (PlannedMeal meal);
    LiveData<List<PlannedMeal>> getDayPlannedMeals(int day,int month,int year);


}
