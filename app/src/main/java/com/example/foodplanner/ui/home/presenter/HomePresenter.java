package com.example.foodplanner.ui.home.presenter;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.MealNetworkCallBack;
import com.example.foodplanner.ui.home.view.HomeView;

import java.util.List;

public class HomePresenter implements MealNetworkCallBack {

    private HomeView homeView;
    private Repository repository;
    public HomePresenter(HomeView homeView, Repository repository) {
        this.homeView = homeView;
        this.repository = repository;

    }

    public void getMeals (){repository.getRandomMeal(this);}
    @Override
    public void onMealNetworkCallBackSuccessfulResult(List<Meal> response) {
    homeView.setMeals(response);
    }

    @Override
    public void onFailResult(String errStr) {
    homeView.showError(errStr);
    }

    public  void addToFav (Meal meal)
    {
        repository.insertFavouriteMeal(meal);
    }



}
