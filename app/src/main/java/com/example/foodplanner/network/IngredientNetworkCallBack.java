package com.example.foodplanner.network;

import com.example.foodplanner.model.Category;
import com.example.foodplanner.model.Ingredient;

import java.util.List;

public interface IngredientNetworkCallBack {
    public void onIngredientNetworkCallBackSuccessfulResult (List<Ingredient> response);


    public void onFailResult (String errStr);
}
