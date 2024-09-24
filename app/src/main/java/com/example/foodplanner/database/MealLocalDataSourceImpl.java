package com.example.foodplanner.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.Meal;

import java.util.List;

public class MealLocalDataSourceImpl implements MealLocalDataSource {

    private MealDAO mealDAO ;
    private static MealLocalDataSourceImpl mealLocalDataSource= null ;
    private LiveData<List<Meal>> storedMeals;

    private MealLocalDataSourceImpl(Context context) {
        AppDataBase appDataBase = AppDataBase.getInstance(context.getApplicationContext());
        mealDAO = appDataBase.mealDAO();
        storedMeals = mealDAO.getAllMeals();
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
}
