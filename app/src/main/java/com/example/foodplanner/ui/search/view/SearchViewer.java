package com.example.foodplanner.ui.search.view;

import com.example.foodplanner.model.Area;
import com.example.foodplanner.model.Category;
import com.example.foodplanner.model.Filter;
import com.example.foodplanner.model.Ingredient;
import com.example.foodplanner.model.Meal;

import java.util.List;

public interface SearchViewer {
    void setCategories(List<Category> categories);
    void setIngredients(List<Ingredient> ingredients);
    void setAreas(List<Area> areas);
    void setMeals(List<Meal> meals);
    void setFilterMeals (List<Filter> meals);
    void showErr (String errString);
    void setMealNav (Meal meal);
}
