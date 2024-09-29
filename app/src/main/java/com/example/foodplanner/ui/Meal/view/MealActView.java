package com.example.foodplanner.ui.Meal.view;

import com.example.foodplanner.model.MealIngredients;

import java.util.ArrayList;

public interface MealActView {
    void setMealIngredients(ArrayList<MealIngredients> mealIngredients);
    void loadWebView(String url);

}
