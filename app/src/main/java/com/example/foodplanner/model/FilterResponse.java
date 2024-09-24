package com.example.foodplanner.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FilterResponse {
    @SerializedName("meals")
    private ArrayList<Filter> filters;

    public FilterResponse(ArrayList<Filter> filters) {
        this.filters = filters;
    }

    public ArrayList<Filter> getFilters() {
        return filters;
    }

    public void setFilters(ArrayList<Filter> filters) {
        this.filters = filters;
    }
}
