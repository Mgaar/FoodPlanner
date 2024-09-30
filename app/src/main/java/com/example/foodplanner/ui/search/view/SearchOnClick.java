package com.example.foodplanner.ui.search.view;

import com.example.foodplanner.model.Meal;

public interface SearchOnClick {
    void onFilterClick (String string);
    void onFilterIngredientClick (String string);
    void onFilterAreaClick (String string);
    void onUserSearchClick (Meal meal);
}
