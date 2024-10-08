package com.example.foodplanner.network;


import com.example.foodplanner.model.AreaResponse;
import com.example.foodplanner.model.CategoryResponse;
import com.example.foodplanner.model.FilterResponse;
import com.example.foodplanner.model.IngredientResponse;
import com.example.foodplanner.model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiClientInterface {
    @GET("random.php")
    Call<MealResponse> getRandomMeal();

    @GET("lookup.php")
    Call<MealResponse> getMealById(@Query("i") String id);

    @GET("search.php")
    Call<MealResponse> getMealByName(@Query("s") String name);

    @GET("categories.php")
    Call<CategoryResponse> getCategories();

    @GET("list.php?a=list")
    Call<AreaResponse> getAreas();

    @GET("list.php?i=list")
    Call<IngredientResponse> getIngredients();

    @GET("filter.php")
    Call<FilterResponse> getFilterIngredients(@Query("i") String ingredient);

    @GET("filter.php")
    Call<FilterResponse> getFilterAreas(@Query("a") String ingredient);

    @GET("filter.php")
    Call<FilterResponse> getFilterCategories(@Query("c") String ingredient);

}
