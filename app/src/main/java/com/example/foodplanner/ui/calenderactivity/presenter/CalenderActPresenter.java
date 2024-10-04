package com.example.foodplanner.ui.calenderactivity.presenter;

import com.example.foodplanner.model.PlannedMeal;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.ui.calenderactivity.view.CalenderActivityView;


public class CalenderActPresenter {
    private CalenderActivityView calenderActivityView;
    private Repository repository;

    public CalenderActPresenter(CalenderActivityView calenderActivityView, Repository repository) {
        this.calenderActivityView = calenderActivityView;
        this.repository = repository;
    }
   public void addPlannedMeal (PlannedMeal plannedMeal)
    {

        repository.insertPlannedMeal(plannedMeal);

    }
}
