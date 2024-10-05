package com.example.foodplanner.ui.list.view;

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

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private final Context context;
    private List<Filter> values;

    private ListOnClick listOnClick;

    public ListAdapter(Context context, List<Filter> values, ListOnClick listOnClick) {
        this.context = context;
        this.values = values;
        this.listOnClick = listOnClick;
    }

    public void setValues(List<Filter> values) {
        this.values = values;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView listFragImageView;
        TextView ListFragTxtView;
        CardView listFragCardView;
        public ViewHolder(@NonNull View v) {
            super(v);
            listFragImageView = v.findViewById(R.id.listFragImageView);
            ListFragTxtView = v.findViewById(R.id.ListFragTxtView);
            listFragCardView = v.findViewById(R.id.listFragCardView);
        }

    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recycler, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recycler.getContext());
        View v = inflater.inflate(R.layout.listmeallayout,recycler,false);
        ListAdapter.ViewHolder vh = new ListAdapter.ViewHolder(v);

        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {


        holder.listFragCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listOnClick.onItemClick(values.get(position).getIdMeal());
            }
        });

        Glide.with(context).load(values.get(position).getStrMealThumb()).into(holder.listFragImageView);
        holder.ListFragTxtView.setText(values.get(position).getStrMeal());


    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
