package com.example.foodplanner;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodplanner.database.MealLocalDataSourceImpl;
import com.example.foodplanner.model.Area;
import com.example.foodplanner.model.Category;
import com.example.foodplanner.model.Filter;
import com.example.foodplanner.model.Ingredient;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.AreaNetworkCallBack;
import com.example.foodplanner.network.CategoryNetworkCallBack;
import com.example.foodplanner.network.FilterNetworkCallBack;
import com.example.foodplanner.network.IngredientNetworkCallBack;
import com.example.foodplanner.network.MealNetworkCallBack;
import com.example.foodplanner.network.RemoteDataSource;

import java.util.List;




public class MainActivity extends AppCompatActivity implements MealNetworkCallBack, IngredientNetworkCallBack, AreaNetworkCallBack, CategoryNetworkCallBack, FilterNetworkCallBack {
    private static final String TAG = "MainActivity";
    Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


         repository = Repository.getInstance(MealLocalDataSourceImpl.getInstance(getApplicationContext()),RemoteDataSource.getInstance());
        repository.getIngredientList(this);
        repository.getAreaList(this);
        repository.getCategoryList(this);
        repository.getMealListByArea(this,"Canadian");
        repository.getMealByName(this,"Rappie Pie");
        repository.getMealById(this,"52933");
    }


    @Override
    public void onIngredientNetworkCallBackSuccessfulResult(List<Ingredient> response) {
        Log.i(TAG, "onIngredientNetworkCallBackSuccessfulResult: "+response.get(0).getStrIngredient());
    }

    @Override
    public void onMealNetworkCallBackSuccessfulResult(List<Meal> response) {
        Log.i(TAG, "onMealNetworkCallBackSuccessfulResult: " + response.get(0).getStrMeal());
repository.deleteMeal(response.get(0));
    }

    @Override
    public void onAreaNetworkCallBackSuccessfulResult(List<Area> response) {
        Log.i(TAG, "onAreaNetworkCallBackSuccessfulResult: " + response.get(0).getStrArea());
    }

    @Override
    public void onCategoryNetworkCallBackSuccessfulResult(List<Category> response) {
        Log.i(TAG, "onCategoryNetworkCallBackSuccessfulResult: " + response.get(0).getStrCategory() );
    }

    @Override
    public void onFilterNetworkCallBackSuccessfulResult(List<Filter> response) {
        Log.i(TAG, "onFilterNetworkCallBackSuccessfulResult: " + response.get(0).getStrMeal());
    }

    @Override
    public void onFailResult(String errStr) {

    }
}