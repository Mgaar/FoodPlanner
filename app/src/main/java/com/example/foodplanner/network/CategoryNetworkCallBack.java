package com.example.foodplanner.network;

import com.example.foodplanner.model.Category;
import com.example.foodplanner.model.Meal;

import java.util.List;

public interface CategoryNetworkCallBack {
    public void onCategoryNetworkCallBackSuccessfulResult (List<Category> response);


    public void onFailResult (String errStr);
}
