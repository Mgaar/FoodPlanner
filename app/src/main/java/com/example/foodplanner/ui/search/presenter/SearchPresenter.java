package com.example.foodplanner.ui.search.presenter;

import android.util.Log;

import com.example.foodplanner.model.Area;
import com.example.foodplanner.model.Category;
import com.example.foodplanner.model.Ingredient;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.AreaNetworkCallBack;
import com.example.foodplanner.network.CategoryNetworkCallBack;
import com.example.foodplanner.network.IngredientNetworkCallBack;
import com.example.foodplanner.ui.search.view.SearchView;

import java.util.List;

public class SearchPresenter implements CategoryNetworkCallBack , IngredientNetworkCallBack, AreaNetworkCallBack {
    private SearchView searchView;
    private Repository repository;
    private static final String TAG = "SearchPresenter";
    public SearchPresenter(SearchView searchView, Repository repository) {
        this.searchView = searchView;
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
    @Override
    public void onCategoryNetworkCallBackSuccessfulResult(List<Category> response) {
        Log.i(TAG, "onCategoryNetworkCallBackSuccessfulResult: "+response.get(0));
        searchView.setCategories(response);
    }

    @Override
    public void onIngredientNetworkCallBackSuccessfulResult(List<Ingredient> response) {
        searchView.setIngredients(response);
    }

    @Override
    public void onAreaNetworkCallBackSuccessfulResult(List<Area> response) {
        searchView.setAreas(response);
    }

    @Override
    public void onFailResult(String errStr) {
searchView.showErr(errStr);
    }
}
