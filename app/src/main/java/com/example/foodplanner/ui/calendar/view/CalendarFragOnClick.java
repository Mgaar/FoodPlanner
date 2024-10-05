package com.example.foodplanner.ui.calendar.view;

import com.example.foodplanner.model.PlannedMeal;

public interface CalendarFragOnClick {
   void  onItemClick (PlannedMeal meal);
   void onRemoveClick(PlannedMeal meal);

}
