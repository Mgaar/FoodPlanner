package com.example.foodplanner.ui.fav.view;

import com.example.foodplanner.model.Meal;

import java.util.List;

public interface FavView {
    void getMeals (List<Meal> meals);
    void  showError (String str);

}
