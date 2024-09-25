package com.example.foodplanner.model;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.database.MealLocalDataSource;
import com.example.foodplanner.network.AreaNetworkCallBack;
import com.example.foodplanner.network.CategoryNetworkCallBack;
import com.example.foodplanner.network.FilterNetworkCallBack;
import com.example.foodplanner.network.IngredientNetworkCallBack;
import com.example.foodplanner.network.MealNetworkCallBack;
import com.example.foodplanner.network.RemoteDataSource;

import java.util.List;

public class Repository {
    private MealLocalDataSource mealLocalDataSource;
    private RemoteDataSource remoteDataSource;
    private static Repository repository = null;

    private Repository(MealLocalDataSource mealLocalDataSource, RemoteDataSource remoteDataSource) {
        this.mealLocalDataSource = mealLocalDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static Repository getInstance(MealLocalDataSource mealLocalDataSource, RemoteDataSource remoteDataSource) {
        if (repository == null) {
            repository = new Repository(mealLocalDataSource, remoteDataSource);
        }
        return repository;
    }
    public void getRandomMeal (MealNetworkCallBack mealNetworkCallBack)
    {
        remoteDataSource.makeRandomMealNetworkCallBack(mealNetworkCallBack);
    }
    public void getMealById (MealNetworkCallBack mealNetworkCallBack,String id)
    {
        remoteDataSource.makeMealByIdNetworkCallBack(mealNetworkCallBack,id);
    }
    public void getMealByName (MealNetworkCallBack mealNetworkCallBack,String name)
    {
        remoteDataSource.makeMealByNameNetworkCallBack(mealNetworkCallBack,name);
    }
    public void getCategoryList (CategoryNetworkCallBack categoryNetworkCallBack)
    {
        remoteDataSource.makeCategoryListNetworkCallBack(categoryNetworkCallBack);
    }
    public void getIngredientList (IngredientNetworkCallBack ingredientNetworkCallBack)
    {
        remoteDataSource.makeIngredientListNetworkCallBack(ingredientNetworkCallBack);
    }
    public void getAreaList (AreaNetworkCallBack areaNetworkCallBack)
    {
        remoteDataSource.makeAreaListNetworkCallBack(areaNetworkCallBack);
    }
    public void getMealListByCategory (FilterNetworkCallBack filterNetworkCallBack,String category)
    {
        remoteDataSource.makeFilterCategoryListNetworkCallBack(filterNetworkCallBack,category);
    }
    public void getMealListByArea (FilterNetworkCallBack filterNetworkCallBack,String area)
    {
        remoteDataSource.makeFilterAreaListNetworkCallBack(filterNetworkCallBack,area);
    }
    public void getMealListByIngredient (FilterNetworkCallBack filterNetworkCallBack,String ingredient)
    {
        remoteDataSource.makeFilterIngredientListNetworkCallBack(filterNetworkCallBack,ingredient);
    }
    public void insertMeal (Meal meal)
    {
        mealLocalDataSource.insertMeal(meal);
    }
    public void deleteMeal (Meal meal)
    {
        mealLocalDataSource.removeMeal(meal);
    }
    public LiveData<List<Meal>> getStoredMeals (){return mealLocalDataSource.getAllMeals();}


}