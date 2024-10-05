package com.example.foodplanner.ui.fav.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.model.Meal;

import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder>{
    private final Context context;
    private List<Meal> values;
    private FavOnClick favOnClick ;

    public FavAdapter(Context context, List<Meal> values, FavOnClick favOnClick) {
        this.context = context;
        this.values = values;
        this.favOnClick = favOnClick;
    }

    public void setValues(List<Meal> values) {
        this.values = values;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView favouriteImageView;
        ImageButton removeButton;
        Button favFragAddToPlanButton;
        TextView favouriteMealtextView;
        TextView favouriteMealCategorytextView;
        CardView cardView;
        public ViewHolder(@NonNull View v) {
            super(v);
            favouriteImageView = v.findViewById(R.id.favouriteImageView);
            removeButton = v.findViewById(R.id.removeButton);
            favouriteMealtextView = v.findViewById(R.id.favouriteMealtextView);
            favouriteMealCategorytextView = v.findViewById(R.id.favouriteMealCategorytextView);
            favFragAddToPlanButton = v.findViewById(R.id.favFragAddToPlanButton);
            cardView=v.findViewById(R.id.favCardView);
        }

    }

    @Override
    public FavAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recycler, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recycler.getContext());
        View v = inflater.inflate(R.layout.favouritemeallayout,recycler,false);
        FavAdapter.ViewHolder vh = new FavAdapter.ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull FavAdapter.ViewHolder holder, int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favOnClick.onItemClick(values.get(position));
            }
        });
        holder.favouriteMealtextView.setText(values.get(position).getStrMeal());
        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favOnClick.onRemoveClick(values.get(position));
            }
        });
        holder.favFragAddToPlanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favOnClick.onAddToPlanClick(values.get(position));
            }
        });

        Glide.with(context).load(values.get(position).getStrMealThumb()).apply(new RequestOptions().override(150,150)
                )
                .into(holder.favouriteImageView);
        holder.favouriteMealCategorytextView.setText(values.get(position).getStrCategory());
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

}
