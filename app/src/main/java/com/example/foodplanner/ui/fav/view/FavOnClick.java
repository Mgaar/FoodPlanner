package com.example.foodplanner.ui.fav.view;

import com.example.foodplanner.model.Meal;

public interface FavOnClick {
    void onRemoveClick (Meal meal);
    void onAddToPlanClick (Meal meal);
    void onItemClick (Meal meal);
}
