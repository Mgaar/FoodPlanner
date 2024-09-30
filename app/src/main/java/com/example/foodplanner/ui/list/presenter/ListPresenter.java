package com.example.foodplanner.ui.list.presenter;

import com.example.foodplanner.model.Filter;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.FilterNetworkCallBack;
import com.example.foodplanner.network.MealNetworkCallBack;
import com.example.foodplanner.ui.list.view.ListView;

import java.util.List;

public class ListPresenter implements FilterNetworkCallBack ,MealNetworkCallBack{

    private ListView listView;
    private Repository repository;

    public ListPresenter(ListView listView, Repository repository) {
        this.listView = listView;
        this.repository = repository;
    }

    public void getMealListByCategory (String category)
    {
        repository.getMealListByCategory(this ,category);
    }
    public void getMealListByArea (String area)
    {
        repository.getMealListByArea(this ,area);
    }
    public void getMealListByIngredient (String ingredient)
    {
        repository.getMealListByIngredient(this ,ingredient);
    }
public  void getMealById (String id)
{
    repository.getMealById(this,id);
}
    @Override
    public void onFilterNetworkCallBackSuccessfulResult(List<Filter> response) {
        listView.setList(response);
    }

    @Override
    public void onMealNetworkCallBackSuccessfulResult(List<Meal> response) {
        listView.setMeal(response.get(0));
    }

    @Override
    public void onFailResult(String errStr) {
listView.showErr(errStr);
    }
}
