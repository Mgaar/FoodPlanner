package com.example.foodplanner.network;


import com.example.foodplanner.model.Area;

import java.util.List;

public interface AreaNetworkCallBack {
    public void onAreaNetworkCallBackSuccessfulResult (List<Area> response);


    public void onFailResult (String errStr);
}
