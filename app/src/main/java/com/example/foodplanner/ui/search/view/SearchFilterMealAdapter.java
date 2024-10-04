package com.example.foodplanner.ui.search.view;

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
import com.example.foodplanner.model.Filter;


import java.util.List;

public class SearchFilterMealAdapter extends RecyclerView.Adapter<SearchFilterMealAdapter.ViewHolder> {
    private final Context context;
    private List<Filter> values;

    private SearchOnClick searchOnClick;

    public SearchFilterMealAdapter(Context context, List<Filter> values, SearchOnClick searchOnClick) {
        this.context = context;
        this.values = values;
        this.searchOnClick = searchOnClick;
    }

    public void setValues(List<Filter> values) {
        this.values = values;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView searchUserImageView;
        TextView searchUserMealtextView;
        CardView searchUserCardView;
        public ViewHolder(@NonNull View v) {
            super(v);
            searchUserImageView = v.findViewById(R.id.searchUserImageView);
            searchUserMealtextView = v.findViewById(R.id.searchUserMealtextView);
            searchUserCardView = v.findViewById(R.id.searchUserCardView);
        }

    }

    @Override
    public SearchFilterMealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recycler, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recycler.getContext());
        View v = inflater.inflate(R.layout.usersearchmeallayouy,recycler,false);
        SearchFilterMealAdapter.ViewHolder vh = new SearchFilterMealAdapter.ViewHolder(v);

        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull SearchFilterMealAdapter.ViewHolder holder, int position) {


        holder.searchUserCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchOnClick.onUserSearchFilterClick(values.get(position).getIdMeal());
            }
        });

        Glide.with(context).load(values.get(position).getStrMealThumb()).into(holder.searchUserImageView);
        holder.searchUserMealtextView.setText(values.get(position).getStrMeal());


    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}