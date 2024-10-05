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
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;

import com.example.foodplanner.model.Meal;

import java.util.List;

public class SearchMealAdapter extends RecyclerView.Adapter<SearchMealAdapter.ViewHolder> {
private final Context context;
private List<Meal> values;

private SearchOnClick searchOnClick;


public SearchMealAdapter(Context context, List<Meal> values, SearchOnClick searchOnClick) {
    this.context = context;
    this.values = values;
    this.searchOnClick = searchOnClick;
}
public void setValues(List<Meal> values) {
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
    public SearchMealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recycler, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recycler.getContext());
        View v = inflater.inflate(R.layout.usersearchmeallayouy,recycler,false);
        SearchMealAdapter.ViewHolder vh = new SearchMealAdapter.ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchMealAdapter.ViewHolder holder, int position) {


        holder.searchUserCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchOnClick.onUserSearchClick(values.get(position));
            }
        });
        Glide.with(context).load(values.get(position).getStrMealThumb()).apply(new RequestOptions().override(70,70)
                )
                .into(holder.searchUserImageView);
        holder.searchUserMealtextView.setText(values.get(position).getStrMeal());


    }

    @Override
    public int getItemCount() {
        return values.size();
    }


}



