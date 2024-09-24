package com.example.foodplanner.network;

import com.example.foodplanner.model.Filter;

import java.util.List;

public interface FilterNetworkCallBack {
    public void onFilterNetworkCallBackSuccessfulResult (List<Filter> response);


    public void onFailResult (String errStr);
}
