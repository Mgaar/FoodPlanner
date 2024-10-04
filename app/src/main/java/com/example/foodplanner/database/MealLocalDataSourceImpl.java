package com.example.foodplanner.database;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.PlannedMeal;

import java.util.List;

public class MealLocalDataSourceImpl implements MealLocalDataSource {

    private MealDAO mealDAO ;
    private PlannedMealDAO plannedMealDAO;
    private static MealLocalDataSourceImpl mealLocalDataSource= null ;
    private LiveData<List<Meal>> storedMeals;
    private LiveData<List<PlannedMeal>> storedPlannedMeals;
    private MealLocalDataSourceImpl(Context context) {
        AppDataBase appDataBase = AppDataBase.getInstance(context.getApplicationContext());
        mealDAO = appDataBase.mealDAO();
        storedMeals = mealDAO.getAllMeals();
        plannedMealDAO =appDataBase.plannedMealDAO();

    }
    public static MealLocalDataSourceImpl getInstance(Context context)
    {
        if (mealLocalDataSource == null )
        {
            mealLocalDataSource = new MealLocalDataSourceImpl(context);
        }
        return mealLocalDataSource;
    }


    @Override
    public void insertMeal(Meal meal) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                mealDAO.insertMeal(meal);
            }
        }.start();
    }

    @Override
    public void removeMeal(Meal meal) {
new Thread(){
    @Override
    public void run() {
        super.run();
        mealDAO.deleteMeal(meal);
    }
}.start();
    }

    @Override
    public LiveData<List<Meal>> getAllMeals() {
        return storedMeals;
    }

    @Override
    public void insertPlannedMeal(PlannedMeal meal) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                plannedMealDAO.insertPlannedMeal(meal);
            }
        }.start();
    }

    @Override
    public void deletePlannedMeal(PlannedMeal meal) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                plannedMealDAO.deletePlannedMeal(meal);
            }
        }.start();
    }

    @Override
    public LiveData<List<PlannedMeal>> getDayPlannedMeals(int day,int month,int year) {
        Log.i(TAG, "getDayPlannedMeals: "+ day + month + year);
        storedPlannedMeals=plannedMealDAO.getDayPlannedMeals(day,month,year);
        if (storedPlannedMeals == null )
            Log.i(TAG, "getDayPlannedMeals: nulll");
        return storedPlannedMeals;
    }




}
