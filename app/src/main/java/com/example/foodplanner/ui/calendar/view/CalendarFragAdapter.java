package com.example.foodplanner.ui.calendar.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.model.PlannedMeal;

import java.util.List;

public class CalendarFragAdapter extends RecyclerView.Adapter<CalendarFragAdapter.ViewHolder> {
    private final Context context;
    private List<PlannedMeal> values;
    CalendarFragOnClick calenderFragOnClick;

    public CalendarFragAdapter(Context context, List<PlannedMeal> values, CalendarFragOnClick calenderFragOnClick) {
        this.context = context;
        this.values = values;
        this.calenderFragOnClick = calenderFragOnClick;
    }

    public void setValues(List<PlannedMeal> values) {
        this.values = values;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView plannedMealsImageView;
        TextView plannedMealsTxtView;
        CardView plannedMealsCardView;
        public ViewHolder(@NonNull View v) {
            super(v);
            plannedMealsImageView = v.findViewById(R.id.plannedMealsImageView);
            plannedMealsTxtView = v.findViewById(R.id.plannedMealsTxtView);
            plannedMealsCardView = v.findViewById(R.id.plannedMealsCardView);
        }

    }


    @Override
    public CalendarFragAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recycler, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recycler.getContext());
        View v = inflater.inflate(R.layout.plannedmealslayout,recycler,false);
        CalendarFragAdapter.ViewHolder vh = new CalendarFragAdapter.ViewHolder(v);

        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull CalendarFragAdapter.ViewHolder holder, int position) {


        holder.plannedMealsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calenderFragOnClick.onItemClick(values.get(position));
            }
        });

        Glide.with(context).load(values.get(position).getStrMealThumb()).into(holder.plannedMealsImageView);
        holder.plannedMealsTxtView.setText(values.get(position).getStrMeal());


    }

    @Override
    public int getItemCount() {
        return values.size();
    }


}
