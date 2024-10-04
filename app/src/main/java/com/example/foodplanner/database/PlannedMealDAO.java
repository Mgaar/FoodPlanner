package com.example.foodplanner.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.model.PlannedMeal;

import java.util.List;

@Dao
public interface
PlannedMealDAO {
    @Query("Select * FROM calender_table WHERE day = :day AND month = :month AND year = :year")
    LiveData<List<PlannedMeal>> getDayPlannedMeals ( int day,int month,int year );

    @Query("SELECT * FROM calender_table WHERE idMeal = :idMeal ")
    PlannedMeal getPlannedMealById(String idMeal);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPlannedMeal (PlannedMeal meal);

    @Delete
    void deletePlannedMeal(PlannedMeal meal);
}
