package com.example.foodplanner.ui.home.view;

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
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.model.Meal;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private final Context context;
    private List<Meal> values;

    private HomeOnClick homeOnClick;

    public HomeAdapter(Context context, List<Meal> values, HomeOnClick homeOnClick) {
        this.context = context;
        this.values = values;
        this.homeOnClick = homeOnClick;
    }

    public void setValues(List<Meal> values) {
        this.values = values;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView homeMealImageView;
        Button homeFavbutton;
        TextView homeMealTextView;
        public ViewHolder(@NonNull View v) {
            super(v);
            homeMealImageView = v.findViewById(R.id.homeMealImageView);
            homeFavbutton = v.findViewById(R.id.homeFavbutton);
            homeMealTextView = v.findViewById(R.id.homeMealTextView);
        }

    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recycler, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recycler.getContext());
        View v = inflater.inflate(R.layout.homemeallayout,recycler,false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.homeMealTextView.setText(values.get(position).getStrMeal());
        holder.homeFavbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeOnClick.onFavClick(values.get(position));
            }
        });
        Glide.with(context).load(values.get(position).getStrMealThumb()).apply(new RequestOptions().override(150,150)
                )
                .into(holder.homeMealImageView);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
