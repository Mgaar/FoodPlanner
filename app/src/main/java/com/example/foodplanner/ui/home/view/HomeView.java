package com.example.foodplanner.ui.home.view;

import com.example.foodplanner.model.Meal;

import java.util.List;

public interface HomeView {
    void setMeals (List<Meal> meals);
    void  showError (String str);
}
