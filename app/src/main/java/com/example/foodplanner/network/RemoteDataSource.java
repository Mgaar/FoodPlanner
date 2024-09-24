package com.example.foodplanner.network;

import com.example.foodplanner.model.AreaResponse;
import com.example.foodplanner.model.CategoryResponse;
import com.example.foodplanner.model.FilterResponse;
import com.example.foodplanner.model.IngredientResponse;
import com.example.foodplanner.model.MealResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource {
    private static final String BASE_URL =new String("https://www.themealdb.com/api/json/v1/1/");
    private ApiClientInterface apiClientInterface;
    private static RemoteDataSource remoteDataSource =null;

    private RemoteDataSource ()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build();
        apiClientInterface = retrofit.create(ApiClientInterface.class);
    }

    public static RemoteDataSource getInstance ()
    {
        if (remoteDataSource == null)
        {
            remoteDataSource = new RemoteDataSource();
        }
        return remoteDataSource;
    }

    public void makeRandomMealNetworkCallBack (MealNetworkCallBack mealNetworkCallBack)
    {
        Call<MealResponse> call = apiClientInterface.getRandomMeal();
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
            mealNetworkCallBack.onMealNetworkCallBackSuccessfulResult(response.body().meals);
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                mealNetworkCallBack.onFailResult("error fetching Random meal");
            }
        });
    }
    public void makeMealByIdNetworkCallBack (MealNetworkCallBack mealNetworkCallBack , String str)
    {
        Call<MealResponse> call = apiClientInterface.getMealById(str);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                mealNetworkCallBack.onMealNetworkCallBackSuccessfulResult(response.body().meals);
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                mealNetworkCallBack.onFailResult("error fetching Random meal");
            }
        });
    }
    public void makeCategoryListNetworkCallBack (CategoryNetworkCallBack categoryNetworkCallBack)
    {
        Call<CategoryResponse> call = apiClientInterface.getCategories();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                categoryNetworkCallBack.onCategoryNetworkCallBackSuccessfulResult(response.body().getCategories());
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable throwable) {
                categoryNetworkCallBack.onFailResult("Failed to get Categories");
            }
        });
    }
    public void makeAreaListNetworkCallBack (AreaNetworkCallBack areaNetworkCallBack)
    {
        Call<AreaResponse> call = apiClientInterface.getAreas();
        call.enqueue(new Callback<AreaResponse>() {
            @Override
            public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
                areaNetworkCallBack.onAreaNetworkCallBackSuccessfulResult(response.body().getAreas());
            }

            @Override
            public void onFailure(Call<AreaResponse> call, Throwable throwable) {
                areaNetworkCallBack.onFailResult("Failed to get Categories");
            }
        });
    }
    public void makeIngredientListNetworkCallBack (IngredientNetworkCallBack ingredientNetworkCallBack)
    {
        Call<IngredientResponse> call = apiClientInterface.getIngredients();
        call.enqueue(new Callback<IngredientResponse>() {
            @Override
            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {
                ingredientNetworkCallBack.onIngredientNetworkCallBackSuccessfulResult(response.body().getIngredients());
            }

            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable throwable) {
                ingredientNetworkCallBack.onFailResult("Failed to get Categories");
            }
        });
    }
    public void makeFilterIngredientListNetworkCallBack (FilterNetworkCallBack filterNetworkCallBack,String str)
    {
        Call<FilterResponse> call = apiClientInterface.getFilterIngredients(str);
        call.enqueue(new Callback<FilterResponse>() {
            @Override
            public void onResponse(Call<FilterResponse> call, Response<FilterResponse> response) {
                filterNetworkCallBack.onFilterNetworkCallBackSuccessfulResult(response.body().getFilters());
            }

            @Override
            public void onFailure(Call<FilterResponse> call, Throwable throwable) {
                filterNetworkCallBack.onFailResult("Failed to get Categories");
            }
        });
    }
    public void makeFilterAreaListNetworkCallBack (FilterNetworkCallBack filterNetworkCallBack,String str)
    {
        Call<FilterResponse> call = apiClientInterface.getFilterAreas(str);
        call.enqueue(new Callback<FilterResponse>() {
            @Override
            public void onResponse(Call<FilterResponse> call, Response<FilterResponse> response) {
                filterNetworkCallBack.onFilterNetworkCallBackSuccessfulResult(response.body().getFilters());
            }

            @Override
            public void onFailure(Call<FilterResponse> call, Throwable throwable) {
                filterNetworkCallBack.onFailResult("Failed to get Categories");
            }
        });
    }
    public void makeFilterCategoryListNetworkCallBack (FilterNetworkCallBack filterNetworkCallBack,String str)
    {
        Call<FilterResponse> call = apiClientInterface.getFilterCategories(str);
        call.enqueue(new Callback<FilterResponse>() {
            @Override
            public void onResponse(Call<FilterResponse> call, Response<FilterResponse> response) {
                filterNetworkCallBack.onFilterNetworkCallBackSuccessfulResult(response.body().getFilters());
            }

            @Override
            public void onFailure(Call<FilterResponse> call, Throwable throwable) {
                filterNetworkCallBack.onFailResult("Failed to get Categories");
            }
        });
    }
}
