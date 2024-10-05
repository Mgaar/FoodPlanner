package com.example.foodplanner.ui.Meal.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.model.MealIngredients;


import java.util.List;

public class MealIngredientsAdapter extends RecyclerView.Adapter<MealIngredientsAdapter.ViewHolder>{
private final Context context;
private List<MealIngredients> values;

    public MealIngredientsAdapter(Context context, List<MealIngredients> values) {
        this.context = context;
        this.values = values;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
    ImageView ingImage;
    TextView mealActIngredientMeasureTxtView;
    TextView mealActIngredientTxtView;
    public ViewHolder(@NonNull View v) {
        super(v);
        ingImage = v.findViewById(R.id.ingImage);
        mealActIngredientMeasureTxtView = v.findViewById(R.id.mealActIngredientMeasureTxtView);
        mealActIngredientTxtView = v.findViewById(R.id.mealActIngredientTxtView);
    }

}

    @Override
    public MealIngredientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recycler, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recycler.getContext());
        View v = inflater.inflate(R.layout.mealactingredientlayout,recycler,false);
        MealIngredientsAdapter.ViewHolder vh = new MealIngredientsAdapter.ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MealIngredientsAdapter.ViewHolder holder, int position) {
        holder.mealActIngredientMeasureTxtView.setText(values.get(position).getStrMeasure());
        holder.mealActIngredientTxtView.setText(values.get(position).getStrIngredient());

        Glide.with(context).load(values.get(position).getThumbUrl())
                .into(holder.ingImage);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

}
