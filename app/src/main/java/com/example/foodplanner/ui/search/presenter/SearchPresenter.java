package com.example.foodplanner.ui.search.presenter;

import android.util.Log;

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
import com.example.foodplanner.ui.search.view.SearchFragment;
import com.example.foodplanner.ui.search.view.SearchViewer;


import java.util.List;

public class SearchPresenter implements CategoryNetworkCallBack , IngredientNetworkCallBack, AreaNetworkCallBack , MealNetworkCallBack, FilterNetworkCallBack {
    private SearchViewer searchViewer;
    private Repository repository;
    private String filter;

    private List<Category> categories;
    private List<Ingredient> ingredients;
    private List<Area> areas;

    private static final String TAG = "SearchPresenter";
    public SearchPresenter(SearchViewer searchViewer, Repository repository) {
        this.searchViewer = searchViewer;
        this.repository = repository;
        this.filter = SearchFragment.NAME;
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
        switch (filter)
        {
            case SearchFragment.NAME:
                repository.getMealByName(this,str); break;
            case SearchFragment.AREA:
                for (Area i : areas)
                {
if (i.getStrArea().equalsIgnoreCase(str))
{
    repository.getMealListByArea(this ,i.getStrArea());
    break;
}

                }
                break;
            case SearchFragment.CAT:
                for (Category i : categories)
                {
                    if (i.getStrCategory().equalsIgnoreCase(str))
                    {
                        repository.getMealListByCategory(this ,i.getStrCategory());
                        break;
                    }
                }
                break;
            case SearchFragment.ING:
                for (Ingredient i : ingredients)
                {
                    if (i.getStrIngredient().equalsIgnoreCase(str))
                    {
                        repository.getMealListByIngredient(this ,i.getStrIngredient());

                    }

                }
                break;
        }

    }
    @Override
    public void onCategoryNetworkCallBackSuccessfulResult(List<Category> response) {
        categories = response;
        searchViewer.setCategories(response);
    }

    @Override
    public void onIngredientNetworkCallBackSuccessfulResult(List<Ingredient> response) {
        ingredients = response;
        searchViewer.setIngredients(response);
    }

    @Override
    public void onAreaNetworkCallBackSuccessfulResult(List<Area> response) {
        areas = response;
        searchViewer.setAreas(response);
    }

    @Override
    public void onMealNetworkCallBackSuccessfulResult(List<Meal> response) {
        searchViewer.setMeals(response);
    }

    @Override
    public void onFilterNetworkCallBackSuccessfulResult(List<Filter> response) {

        searchViewer.setFilterMeals(response);
    }

    @Override
    public void onFailResult(String errStr) {
searchViewer.showErr(errStr);
    }
    public void filterChange(String str){
        filter = str;
    }
}
