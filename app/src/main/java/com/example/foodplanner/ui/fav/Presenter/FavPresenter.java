package com.example.foodplanner.ui.fav.Presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Repository;

import com.example.foodplanner.ui.fav.view.FavView;

import java.util.List;

public class FavPresenter  {
    private FavView favView;
    private Repository repository;

    public FavPresenter(FavView favView, Repository repository) {
        this.favView = favView;
        this.repository = repository;
    }
    public void getFavMeals(LifecycleOwner lifecycleOwner){
        Observer<List<Meal>> observer = new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                favView.getMeals(meals);
            }
        };
        LiveData<List<Meal>> liveData =repository.getStoredMeals();
        liveData.observe(lifecycleOwner,observer);
    }
    public void removeMeal(Meal meal)
    {
        repository.deleteMeal(meal);
    }

}
