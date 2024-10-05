package com.example.foodplanner.ui.calenderactivity.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.foodplanner.MainActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.database.MealLocalDataSourceImpl;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.PlannedMeal;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.RemoteDataSource;
import com.example.foodplanner.ui.calenderactivity.presenter.CalenderActPresenter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CalendarActivity extends AppCompatActivity implements CalenderActivityView{
private CalendarView calendarActCalendarView;
private Meal meal;
private TextView calendarActMealTxtView;
private ImageView calendarActImageView;
private Button calendarActAddBtn;
private int year;
private int month;
private int day;
    private static final String TAG = "CalendarActivity";
private CalenderActPresenter calenderActPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calendar);
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            meal = (Meal) intent.getSerializableExtra(MainActivity.MEAL);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.item_background_color));
        }

        calendarActCalendarView = findViewById(R.id.calendarActCalendarView);
        calendarActMealTxtView = findViewById(R.id.calendarActMealTxtView);
        calendarActImageView = findViewById(R.id.calendarActImageView);
        Glide.with(this)
                .load(meal.getStrMealThumb()) // load image from URL or path
                .into(calendarActImageView);
        calendarActMealTxtView.setText(meal.getStrMeal());


        Calendar calendar = Calendar.getInstance(Locale.US);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        Log.i(TAG, "onCreate: "+day + month + year);
        long today = calendar.getTimeInMillis();
        calendarActCalendarView.setMinDate(today);
        calenderActPresenter = new CalenderActPresenter(this ,Repository.getInstance(MealLocalDataSourceImpl.getInstance(CalendarActivity.this), RemoteDataSource.getInstance()));
        calendarActCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                Log.i(TAG, "onSelectedDayChange: "+i+i1+i2);
                if ((i == i1) &&(0 == i2)&&(i1 == 0))
                {
                     year = calendar.get(Calendar.YEAR);
                     month = calendar.get(Calendar.MONTH);
                     day = calendar.get(Calendar.DAY_OF_MONTH);
                    Log.i(TAG, "onSelectedDayChange: ");
                }
                else
                {
                    year = i;
                    month = i1;
                    day = i2;
                }

            }
        });
        calendarActAddBtn = findViewById(R.id.calendarActAddBtn);
        calendarActAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: "+day+month+year);
                calenderActPresenter.addPlannedMeal(new PlannedMeal(meal,day,month,year));
                Toast.makeText(CalendarActivity.this,meal.getStrMeal()+" added sucessfully",Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }





}