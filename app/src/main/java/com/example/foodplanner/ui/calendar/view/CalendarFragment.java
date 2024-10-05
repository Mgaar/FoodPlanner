package com.example.foodplanner.ui.calendar.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.MainActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.database.MealLocalDataSourceImpl;
import com.example.foodplanner.databinding.FragmentCalenderBinding;
import com.example.foodplanner.model.PlannedMeal;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.RemoteDataSource;
import com.example.foodplanner.ui.Meal.view.MealActivity;
import com.example.foodplanner.ui.calendar.presenter.CalendarFragPresenter;

import java.util.ArrayList;
import java.util.List;


public class CalendarFragment extends Fragment implements CalendarFragView, CalendarFragOnClick {

    private FragmentCalenderBinding binding;
    private TextView calenderFragTxtView;
    private ImageButton calenderFragBackImageButton;
    private ImageButton calenderFragForwrdImageButton;

    private RecyclerView calendarFragSundayRecyclervVew;
    private RecyclerView calendarFragMondayRecyclervVew;
    private RecyclerView calendarFragTuesdayRecyclervVew;
    private RecyclerView calendarFragWednesdayRecyclervVew;
    private RecyclerView calendarFragThursdayRecyclervVew;
    private RecyclerView calendarFragFridayRecyclervVew;
    private RecyclerView calendarFragSaturdayRecyclervVew;

    List<PlannedMeal> sundayMeals;
    List<PlannedMeal> mondayMeals;
    List<PlannedMeal> tuesdayMeals;
    List<PlannedMeal> wednesdayMeals;
    List<PlannedMeal> thursdayMeals;
    List<PlannedMeal> fridayMeals;
    List<PlannedMeal> saturdayMeals;

    CalendarFragAdapter sundayCalendarFragAdapter;
    CalendarFragAdapter mondayCalendarFragAdapter;
    CalendarFragAdapter tuesdayCalendarFragAdapter;
    CalendarFragAdapter wednesdayCalendarFragAdapter;
    CalendarFragAdapter thursdayCalendarFragAdapter;
    CalendarFragAdapter fridayCalendarFragAdapter;
    CalendarFragAdapter saturdayCalendarFragAdapter;

    CalendarFragPresenter calendarFragPresenter;
    private AlertDialog.Builder builder;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCalenderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        calenderFragTxtView = view.findViewById(R.id.calenderFragTxtView);
        calenderFragTxtView.setText("This Week");
        calenderFragForwrdImageButton = view.findViewById(R.id.calenderFragForwrdImageButton);
         calenderFragBackImageButton = view.findViewById(R.id.calenderFragBackImageButton);
         calendarFragPresenter = new CalendarFragPresenter(this, Repository.getInstance(MealLocalDataSourceImpl.getInstance(getActivity()), RemoteDataSource.getInstance()));

         calenderFragBackImageButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 calendarFragPresenter.onBackClick();
             }
         });
         calenderFragForwrdImageButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
               calendarFragPresenter.onNextClick();
             }
         });
        sundayMeals = new ArrayList<PlannedMeal>();
        mondayMeals = new ArrayList<PlannedMeal>();
       tuesdayMeals = new ArrayList<PlannedMeal>();
        wednesdayMeals = new ArrayList<PlannedMeal>();
         thursdayMeals = new ArrayList<PlannedMeal>();
       fridayMeals = new ArrayList<PlannedMeal>();
         saturdayMeals = new ArrayList<PlannedMeal>();



            calendarFragSundayRecyclervVew = view.findViewById(R.id.calenderFragSundayRecyclervVew);
            calendarFragMondayRecyclervVew = view.findViewById(R.id.calenderFragMondayRecyclervVew);
            calendarFragTuesdayRecyclervVew = view.findViewById(R.id.calenderFragTuesdayRecyclervVew);
            calendarFragWednesdayRecyclervVew = view.findViewById(R.id.calenderFragWednesdayRecyclervVew);
            calendarFragThursdayRecyclervVew = view.findViewById(R.id.calenderFragThursdayRecyclervVew);
            calendarFragFridayRecyclervVew = view.findViewById(R.id.calenderFragFridayRecyclervVew);
            calendarFragSaturdayRecyclervVew = view.findViewById(R.id.calenderFragSaturdayRecyclervVew);


            sundayCalendarFragAdapter = new CalendarFragAdapter(getActivity(), sundayMeals, this);
            mondayCalendarFragAdapter = new CalendarFragAdapter(getActivity(), mondayMeals, this);
            tuesdayCalendarFragAdapter = new CalendarFragAdapter(getActivity(), tuesdayMeals, this);
            wednesdayCalendarFragAdapter = new CalendarFragAdapter(getActivity(), wednesdayMeals, this);
            thursdayCalendarFragAdapter = new CalendarFragAdapter(getActivity(), thursdayMeals, this);
            fridayCalendarFragAdapter = new CalendarFragAdapter(getActivity(), fridayMeals, this);
            saturdayCalendarFragAdapter = new CalendarFragAdapter(getActivity(), saturdayMeals, this);

            calendarFragSundayRecyclervVew.setAdapter(sundayCalendarFragAdapter);
            calendarFragMondayRecyclervVew.setAdapter(mondayCalendarFragAdapter);
            calendarFragTuesdayRecyclervVew.setAdapter(tuesdayCalendarFragAdapter);
            calendarFragWednesdayRecyclervVew.setAdapter(wednesdayCalendarFragAdapter);
            calendarFragThursdayRecyclervVew.setAdapter(thursdayCalendarFragAdapter);
            calendarFragFridayRecyclervVew.setAdapter(fridayCalendarFragAdapter);
            calendarFragSaturdayRecyclervVew.setAdapter(saturdayCalendarFragAdapter);

            calendarFragSundayRecyclervVew.setHasFixedSize(true);
            calendarFragMondayRecyclervVew.setHasFixedSize(true);
            calendarFragTuesdayRecyclervVew.setHasFixedSize(true);
            calendarFragWednesdayRecyclervVew.setHasFixedSize(true);
            calendarFragThursdayRecyclervVew.setHasFixedSize(true);
            calendarFragFridayRecyclervVew.setHasFixedSize(true);
            calendarFragSaturdayRecyclervVew.setHasFixedSize(true);

            LinearLayoutManager mgrSunday = new LinearLayoutManager(getActivity());
            mgrSunday.setOrientation(RecyclerView.HORIZONTAL);

            LinearLayoutManager mgrMonday = new LinearLayoutManager(getActivity());
            mgrMonday.setOrientation(RecyclerView.HORIZONTAL);

            LinearLayoutManager mgrTuesday = new LinearLayoutManager(getActivity());
            mgrTuesday.setOrientation(RecyclerView.HORIZONTAL);

            LinearLayoutManager mgrWednesday = new LinearLayoutManager(getActivity());
            mgrWednesday.setOrientation(RecyclerView.HORIZONTAL);

            LinearLayoutManager mgrThursday = new LinearLayoutManager(getActivity());
            mgrThursday.setOrientation(RecyclerView.HORIZONTAL);

            LinearLayoutManager mgrFriday = new LinearLayoutManager(getActivity());
            mgrFriday.setOrientation(RecyclerView.HORIZONTAL);

            LinearLayoutManager mgrSaturday = new LinearLayoutManager(getActivity());
            mgrSaturday.setOrientation(RecyclerView.HORIZONTAL);

            calendarFragSundayRecyclervVew.setLayoutManager(mgrSunday);
            calendarFragMondayRecyclervVew.setLayoutManager(mgrMonday);
            calendarFragTuesdayRecyclervVew.setLayoutManager(mgrTuesday);
            calendarFragWednesdayRecyclervVew.setLayoutManager(mgrWednesday);
            calendarFragThursdayRecyclervVew.setLayoutManager(mgrThursday);
            calendarFragFridayRecyclervVew.setLayoutManager(mgrFriday);
            calendarFragSaturdayRecyclervVew.setLayoutManager(mgrSaturday);


        builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Alert !");
        builder.setCancelable(false);


        calendarFragPresenter.getThisWeekPlannedMeals(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void updateWeek(String week) {
        calenderFragTxtView.setText(week);
    }

    @Override
    public void updateSunday(List<PlannedMeal> meals) {
    sundayCalendarFragAdapter.setValues(meals);
    sundayCalendarFragAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateMonday(List<PlannedMeal> meals) {
        mondayCalendarFragAdapter.setValues(meals);
        mondayCalendarFragAdapter.notifyDataSetChanged();


    }

    @Override
    public void updateTuesday(List<PlannedMeal> meals) {
        tuesdayCalendarFragAdapter.setValues(meals);
        tuesdayCalendarFragAdapter.notifyDataSetChanged();


    }

    @Override
    public void updateWednesday(List<PlannedMeal> meals) {
        wednesdayCalendarFragAdapter.setValues(meals);
        wednesdayCalendarFragAdapter.notifyDataSetChanged();


    }

    @Override
    public void updateThursday(List<PlannedMeal> meals) {
        thursdayCalendarFragAdapter.setValues(meals);
        thursdayCalendarFragAdapter.notifyDataSetChanged();


    }

    @Override
    public void updateFriday(List<PlannedMeal> meals) {
        fridayCalendarFragAdapter.setValues(meals);
        fridayCalendarFragAdapter.notifyDataSetChanged();


    }

    @Override
    public void updateSaturday(List<PlannedMeal> meals) {
        saturdayCalendarFragAdapter.setValues(meals);
        saturdayCalendarFragAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(PlannedMeal meal) {
        Intent intent  = new Intent(this.getActivity(), MealActivity.class);
        intent.putExtra(MainActivity.MEAL ,meal.getMeal());
        startActivity(intent);

    }

    @Override
    public void onRemoveClick(PlannedMeal meal) {



        builder.setMessage("if u proceed u will remove " + meal.getStrMeal() );
        builder.setPositiveButton("Proceed", (DialogInterface.OnClickListener) (dialog, which) -> {
            calendarFragPresenter.removePlannedMeal(meal);
            Toast.makeText(getActivity(),meal.getStrMeal()+" removed ",Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("Cancel", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }
}