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
import com.example.foodplanner.model.Category;


import java.util.List;

public class SearchCategoryAdapter extends RecyclerView.Adapter<SearchCategoryAdapter.ViewHolder> {

    private final Context context;
    private List<Category> values;

    private SearchOnClick searchOnClick;

    public SearchCategoryAdapter(Context context, List<Category> values, SearchOnClick searchOnClick) {
        this.context = context;
        this.values = values;
        this.searchOnClick = searchOnClick;
    }

    public void setValues(List<Category> values) {
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
    public SearchCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recycler, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recycler.getContext());
        View v = inflater.inflate(R.layout.categorieslayout,recycler,false);
        SearchCategoryAdapter.ViewHolder vh = new SearchCategoryAdapter.ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchCategoryAdapter.ViewHolder holder, int position) {


        holder.searchCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchOnClick.onFilterClick(values.get(position).getStrCategory());
            }
        });
        Glide.with(context).load(values.get(position).getStrCategoryThumb())./*apply(new RequestOptions().override(130,100)
                )
                .*/into(holder.searchCategoryImageView);
        holder.searchCategoryTxtView.setText(values.get(position).getStrCategory());


    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
