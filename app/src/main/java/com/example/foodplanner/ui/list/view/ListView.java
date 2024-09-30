package com.example.foodplanner.ui.list.view;

import com.example.foodplanner.model.Filter;
import com.example.foodplanner.model.Meal;

import java.util.List;

public interface ListView {
    void setList (List<Filter> meals);
    void setMeal(Meal meal);
    void showErr (String errString);

}
