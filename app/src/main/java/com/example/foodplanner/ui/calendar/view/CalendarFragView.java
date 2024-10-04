package com.example.foodplanner.ui.calendar.view;

import com.example.foodplanner.model.PlannedMeal;

import java.util.List;

public interface CalendarFragView {
    void updateWeek(String week);
    void updateSunday(List<PlannedMeal> meals);
    void updateMonday(List<PlannedMeal> meals);
    void updateTuesday(List<PlannedMeal> meals);
    void updateWednesday(List<PlannedMeal> meals);
    void updateThursday(List<PlannedMeal> meals);
    void updateFriday(List<PlannedMeal> meals);
    void updateSaturday(List<PlannedMeal> meals);
}
