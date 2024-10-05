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
import com.example.foodplanner.model.Area;

import java.util.List;

public class SearchAreaAdapter extends RecyclerView.Adapter<SearchAreaAdapter.ViewHolder> {

    private final Context context;
    private List<Area> values;

    private SearchOnClick searchOnClick;

    public SearchAreaAdapter(Context context, List<Area> values, SearchOnClick searchOnClick) {
        this.context = context;
        this.values = values;
        this.searchOnClick = searchOnClick;
    }

    public void setValues(List<Area> values) {
        this.values = values;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView searchImageView;
        TextView searchTxtView;
        CardView searchCardView;
        public ViewHolder(@NonNull View v) {
            super(v);
            searchImageView = v.findViewById(R.id.searchCategoryImageView);
            searchTxtView = v.findViewById(R.id.searchCategoryTxtView);
            searchCardView = v.findViewById(R.id.searchCardView);
        }

    }
    @Override
    public SearchAreaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recycler, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recycler.getContext());
        View v = inflater.inflate(R.layout.categorieslayout,recycler,false);
        SearchAreaAdapter.ViewHolder vh = new SearchAreaAdapter.ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAreaAdapter.ViewHolder holder, int position) {


        holder.searchCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchOnClick.onFilterAreaClick(values.get(position).getStrArea());
            }
        });
        String[] countryCodes = {
                "us", // American
                "gb", // British
                "ca", // Canadian
                "cn", // Chinese
                "hr", // Croatian
                "nl", // Dutch
                "eg", // Egyptian
                "ph", // Filipino
                "fr", // French
                "gr", // Greek
                "in", // Indian
                "ie", // Irish
                "it", // Italian
                "jm", // Jamaican
                "jp", // Japanese
                "ke", // Kenyan
                "my", // Malaysian
                "mx", // Mexican
                "ma", // Moroccan
                "pl", // Polish
                "pt", // Portuguese
                "ru", // Russian
                "es", // Spanish
                "th", // Thai
                "tn", // Tunisian
                "tr", // Turkish
                "ua",// Ukrainian
                "",
                "vn"  // Vietnamese
        };
        String url = "https://flagcdn.com/160x120/"+countryCodes[position]+".png";

        Glide.with(context).load(url).into(holder.searchImageView);
        holder.searchTxtView.setText(values.get(position).getStrArea());


    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
