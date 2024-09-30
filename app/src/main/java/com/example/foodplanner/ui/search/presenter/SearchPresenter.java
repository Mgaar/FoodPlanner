package com.example.foodplanner.ui.search.presenter;

import android.util.Log;

import com.example.foodplanner.model.Area;
import com.example.foodplanner.model.Category;
import com.example.foodplanner.model.Ingredient;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.AreaNetworkCallBack;
import com.example.foodplanner.network.CategoryNetworkCallBack;
import com.example.foodplanner.network.IngredientNetworkCallBack;
import com.example.foodplanner.network.MealNetworkCallBack;
import com.example.foodplanner.ui.search.view.SearchViewer;

import java.util.List;

public class SearchPresenter implements CategoryNetworkCallBack , IngredientNetworkCallBack, AreaNetworkCallBack , MealNetworkCallBack {
    private SearchViewer searchViewer;
    private Repository repository;
    private static final String TAG = "SearchPresenter";
    public SearchPresenter(SearchViewer searchViewer, Repository repository) {
        this.searchViewer = searchViewer;
        this.repository = repository;
    }
    public void getCategoryList ()
    {
        repository.getCategoryList(this);
    }
    public void getIngredientList ()
    {
        repository.getIngredientList(this);
    }
    public void getAreaList ()
    {
        repository.getAreaList(this);
    }
    public void getMealSearch (String str)
    {
        repository.getMealByName(this,str);
    }
    @Override
    public void onCategoryNetworkCallBackSuccessfulResult(List<Category> response) {
        Log.i(TAG, "onCategoryNetworkCallBackSuccessfulResult: "+response.get(0));
        searchViewer.setCategories(response);
    }

    @Override
    public void onIngredientNetworkCallBackSuccessfulResult(List<Ingredient> response) {
        searchViewer.setIngredients(response);
    }

    @Override
    public void onAreaNetworkCallBackSuccessfulResult(List<Area> response) {
        searchViewer.setAreas(response);
    }

    @Override
    public void onMealNetworkCallBackSuccessfulResult(List<Meal> response) {
        searchViewer.setMeals(response);
    }

    @Override
    public void onFailResult(String errStr) {
searchViewer.showErr(errStr);
    }
}
