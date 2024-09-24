package com.example.foodplanner.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AreaResponse {
    @SerializedName("meals")
    private ArrayList<Area> areas;

    public AreaResponse(ArrayList<Area> areas) {
        this.areas = areas;
    }

    public ArrayList<Area> getAreas() {
        return areas;
    }

    public void setAreas(ArrayList<Area> areas) {
        this.areas = areas;
    }
}
