package com.example.foodplanner.model;

public class MealIngredients {
    private String strIngredient;
    private String strMeasure;
    private String thumbUrl;

    public MealIngredients(String strIngredient, String strMeasure, String thumbUrl) {
        this.strIngredient = strIngredient;
        this.strMeasure = strMeasure;
        this.thumbUrl = thumbUrl;
    }

    public String getStrIngredient() {
        return strIngredient;
    }

    public String getStrMeasure() {
        return strMeasure;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }
}
