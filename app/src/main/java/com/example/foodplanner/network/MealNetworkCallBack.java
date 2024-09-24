package com.example.foodplanner.network;

import com.example.foodplanner.model.Meal;

import java.util.List;

public interface MealNetworkCallBack {
    public void onMealNetworkCallBackSuccessfulResult (List<Meal> response);


    public void onFailResult (String errStr);
}
