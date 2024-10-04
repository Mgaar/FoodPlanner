package com.example.foodplanner.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.PlannedMeal;

@Database(entities = {Meal.class, PlannedMeal.class},version = 4
)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance=null;

    public AppDataBase() {
    }
    public abstract MealDAO mealDAO();
    public abstract PlannedMealDAO plannedMealDAO();
    public static synchronized AppDataBase getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,"mealsdb").build();

        }
        return instance;
    }
}

