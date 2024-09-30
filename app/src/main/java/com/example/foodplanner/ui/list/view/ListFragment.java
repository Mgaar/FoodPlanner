package com.example.foodplanner.ui.list.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.MainActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.database.MealLocalDataSourceImpl;
import com.example.foodplanner.model.Category;
import com.example.foodplanner.model.Filter;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.RemoteDataSource;
import com.example.foodplanner.ui.Meal.view.MealActivity;
import com.example.foodplanner.ui.list.presenter.ListPresenter;
import com.example.foodplanner.ui.search.view.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class ListFragment extends Fragment implements ListView , ListOnClick{

private String type;
private String filter;
private TextView listFragTxtView;
private ListPresenter listPresenter;
    private List<Filter> filters;
    private ListAdapter listAdapter;
    RecyclerView listFragRecyclerView;
    private BottomNavigationView bottomNavigationView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Get a reference to the BottomNavigationView

        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listPresenter = new ListPresenter(this, Repository.getInstance(MealLocalDataSourceImpl.getInstance(getActivity()), RemoteDataSource.getInstance()));

        type = getArguments().getString(SearchFragment.TYPE);
        filter = getArguments().getString(SearchFragment.LIST);
        listFragTxtView = view.findViewById(R.id.listFragTxtView);
        listFragTxtView.setText(filter + " Meals");
        switch (type){
            case SearchFragment.CAT: listPresenter.getMealListByCategory(filter);break;
            case SearchFragment.AREA: listPresenter.getMealListByArea(filter);break;
            case SearchFragment.ING: listPresenter.getMealListByIngredient(filter);break;
            default:Toast.makeText(getActivity(),"error not valid filter",Toast.LENGTH_SHORT).show();
        }
        listFragRecyclerView = view.findViewById(R.id.listFragRecyclerView);
        filters = new ArrayList<Filter>();
        listAdapter = new ListAdapter(getActivity(),filters,this);

listFragRecyclerView.setAdapter(listAdapter);
        listFragRecyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),2);
        listFragRecyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void setList(List<Filter> meals) {
listAdapter.setValues(meals);
listAdapter.notifyDataSetChanged();
    }

    @Override
    public void setMeal(Meal meal) {
        Intent intent  = new Intent(this.getActivity(), MealActivity.class);
        intent.putExtra(MainActivity.MEAL ,meal);
        startActivity(intent);
    }

    @Override
    public void showErr(String errString) {
        Toast.makeText(getActivity(),errString,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(String mealId) {
    listPresenter.getMealById(mealId);
    }

    @Override
    public void onResume() {
        super.onResume();
        bottomNavigationView = requireActivity().findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.navigation_search);
    }
}