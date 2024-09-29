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
import com.example.foodplanner.model.Category;
import com.example.foodplanner.model.Ingredient;

import java.util.List;

public class SearchIngredientAdapter extends RecyclerView.Adapter<SearchIngredientAdapter.ViewHolder> {
    private final Context context;
    private List<Ingredient> values;

    private SearchOnClick searchOnClick;

    public SearchIngredientAdapter(Context context, List<Ingredient> values, SearchOnClick searchOnClick) {
        this.context = context;
        this.values = values;
        this.searchOnClick = searchOnClick;
    }

    public void setValues(List<Ingredient> values) {
        this.values = values;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView searchCategoryImageView;
        TextView searchCategoryTxtView;
        CardView searchCardView;
        public ViewHolder(@NonNull View v) {
            super(v);
            searchCategoryImageView = v.findViewById(R.id.searchCategoryImageView);
            searchCategoryTxtView = v.findViewById(R.id.searchCategoryTxtView);
            searchCardView = v.findViewById(R.id.searchCardView);
        }

    }
    @Override
    public SearchIngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recycler, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recycler.getContext());
        View v = inflater.inflate(R.layout.categorieslayout,recycler,false);
        SearchIngredientAdapter.ViewHolder vh = new SearchIngredientAdapter.ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchIngredientAdapter.ViewHolder holder, int position) {


        holder.searchCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchOnClick.onFilterIngredientClick(values.get(position).getStrIngredient());
            }
        });
        Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+values.get(position).getStrIngredient()+ "-Small.png")./*apply(new RequestOptions().override(130,100)
                )
                .*/into(holder.searchCategoryImageView);
        holder.searchCategoryTxtView.setText(values.get(position).getStrIngredient());


    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
