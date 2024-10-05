package com.example.foodplanner.ui.calendar.presenter;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.foodplanner.model.PlannedMeal;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.ui.calendar.view.CalendarFragView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarFragPresenter {
    private Repository repository;
    private CalendarFragView calendarFragView;
private Date current;
private Calendar calendar;
LifecycleOwner lifecycleOwner;
    private static final String TAG = "CalenderFragPresenter";
    public CalendarFragPresenter(CalendarFragView calendarFragView, Repository repository) {
        this.repository = repository;
        this.calendarFragView = calendarFragView;
        calendar = Calendar.getInstance(Locale.US);

        //set the first day to be today to get
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        current = calendar.getTime();
    }

public void getThisWeekPlannedMeals (LifecycleOwner lifecycleOwner){
        this.lifecycleOwner = lifecycleOwner;
        getWeek(current);
}
private void getWeek(Date weekFirstDay)
{
    Calendar calendarTemp = Calendar.getInstance();
    calendarTemp.setTime(weekFirstDay);
int day;
int month;
int year;
for (int i =0;i<7;i++)
{

     day = calendarTemp.get(Calendar.DAY_OF_MONTH);
     month = calendarTemp.get(Calendar.MONTH) ;
     year = calendarTemp.get(Calendar.YEAR);
    calendarTemp.add(Calendar.DAY_OF_MONTH,1);
    switch (i) {
        case 0: // Sunday
            getPlannedSundayMeals(lifecycleOwner,day, month, year);
            break;
        case 1: // Monday
            getPlannedMondayMeals(lifecycleOwner,day, month, year);
            break;
        case 2: // Tuesday
            getPlannedTuesdayMeals(lifecycleOwner,day, month, year);
            break;
        case 3: // Wednesday
            getPlannedWednesdayMeals(lifecycleOwner,day, month, year);
            break;
        case 4: // Thursday
            getPlannedThursdayMeals(lifecycleOwner,day, month, year);
            break;
        case 5: // Friday
            getPlannedFridayMeals(lifecycleOwner,day, month, year);
            break;
        case 6: // Saturday
            getPlannedSaturdayMeals(lifecycleOwner,day, month, year);
            break;
        default:
            Log.i(TAG, "getWeekInvalid day of the week: " + i);
    }

}



}

    public void onBackClick(){
        String week;
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        Date weekStart = calendar.getTime();
        if (calendar.getTime().equals(current))
        {
            week ="This Week";
            getWeek(current);
        }
        else
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd", Locale.getDefault());
            calendar.add(Calendar.DAY_OF_MONTH, 6);
            week = dateFormat.format(weekStart) +" - " + dateFormat.format(calendar.getTime());
            getWeek(weekStart);
            calendar.add(Calendar.DAY_OF_MONTH, -6);
        }
        calendarFragView.updateWeek(week);
    }

    public void onNextClick(){
        String week;
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date weekStart =calendar.getTime();
        if (calendar.getTime().equals(current))
        {
            week = "This Week";
            getWeek(current);
        }
        else
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd", Locale.getDefault());
            calendar.add(Calendar.DAY_OF_MONTH, 6);
            week = dateFormat.format(weekStart) +" - " + dateFormat.format(calendar.getTime());
            getWeek(weekStart);
            calendar.add(Calendar.DAY_OF_MONTH, -6);
        }
        calendarFragView.updateWeek(week);
    }


    private void getPlannedSundayMeals(LifecycleOwner lifecycleOwner,int day,int month,int year){
        Observer<List<PlannedMeal>> observer = new Observer<List<PlannedMeal>>() {
            @Override
            public void onChanged(List<PlannedMeal> meals) {
                if(meals == null)
                    Log.i(TAG, "onChanged: null");
calendarFragView.updateSunday(meals);
            }
        };
        LiveData<List<PlannedMeal>> liveData =repository.getPlannedStoredMeals(day,month,year);
        liveData.observe(lifecycleOwner,observer);
    }
    private void getPlannedMondayMeals(LifecycleOwner lifecycleOwner,int day,int month,int year){
        Observer<List<PlannedMeal>> observer = new Observer<List<PlannedMeal>>() {
            @Override
            public void onChanged(List<PlannedMeal> meals) {
                calendarFragView.updateMonday(meals);
            }
        };
        LiveData<List<PlannedMeal>> liveData =repository.getPlannedStoredMeals(day,month,year);
        liveData.observe(lifecycleOwner,observer);
    }
    private void getPlannedTuesdayMeals(LifecycleOwner lifecycleOwner,int day,int month,int year){
        Observer<List<PlannedMeal>> observer = new Observer<List<PlannedMeal>>() {
            @Override
            public void onChanged(List<PlannedMeal> meals) {
                calendarFragView.updateTuesday(meals);
            }
        };
        LiveData<List<PlannedMeal>> liveData =repository.getPlannedStoredMeals(day,month,year);
        liveData.observe(lifecycleOwner,observer);
    }
    private void getPlannedWednesdayMeals(LifecycleOwner lifecycleOwner,int day,int month,int year){
        Observer<List<PlannedMeal>> observer = new Observer<List<PlannedMeal>>() {
            @Override
            public void onChanged(List<PlannedMeal> meals) {
                calendarFragView.updateWednesday(meals);
            }
        };
        LiveData<List<PlannedMeal>> liveData =repository.getPlannedStoredMeals(day,month,year);
        liveData.observe(lifecycleOwner,observer);
    }
    private void getPlannedThursdayMeals(LifecycleOwner lifecycleOwner,int day,int month,int year){
        Observer<List<PlannedMeal>> observer = new Observer<List<PlannedMeal>>() {
            @Override
            public void onChanged(List<PlannedMeal> meals) {
                calendarFragView.updateThursday(meals);
            }
        };
        LiveData<List<PlannedMeal>> liveData =repository.getPlannedStoredMeals(day,month,year);
        liveData.observe(lifecycleOwner,observer);
    }
    private void getPlannedFridayMeals(LifecycleOwner lifecycleOwner,int day,int month,int year){
        Observer<List<PlannedMeal>> observer = new Observer<List<PlannedMeal>>() {
            @Override
            public void onChanged(List<PlannedMeal> meals) {
                calendarFragView.updateFriday(meals);
            }
        };
        LiveData<List<PlannedMeal>> liveData =repository.getPlannedStoredMeals(day,month,year);
        liveData.observe(lifecycleOwner,observer);
    }
    private void getPlannedSaturdayMeals(LifecycleOwner lifecycleOwner,int day,int month,int year){
        Observer<List<PlannedMeal>> observer = new Observer<List<PlannedMeal>>() {
            @Override
            public void onChanged(List<PlannedMeal> meals) {
                calendarFragView.updateSaturday(meals);
            }
        };
        LiveData<List<PlannedMeal>> liveData =repository.getPlannedStoredMeals(day,month,year);
        liveData.observe(lifecycleOwner,observer);
    }
public void removePlannedMeal (PlannedMeal plannedMeal){
        repository.deletePlannedMeal(plannedMeal);
}
}
