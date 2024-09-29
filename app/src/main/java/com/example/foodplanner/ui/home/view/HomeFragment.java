package com.example.foodplanner.ui.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.MainActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.database.MealLocalDataSourceImpl;
import com.example.foodplanner.databinding.FragmentHomeBinding;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.RemoteDataSource;
import com.example.foodplanner.ui.Meal.view.MealActivity;
import com.example.foodplanner.ui.home.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements HomeOnClick , HomeView{

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private ArrayList<Meal> meal;
    private HomeAdapter homeAdapter;
    HomePresenter homePresenter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState == null )
        {
            recyclerView=view.findViewById(R.id.recyclerview);
            meal = new ArrayList<Meal>();
            TextView textView = view.findViewById(R.id.textView3);
            textView.setText("Daily Inspiration");

            homeAdapter = new HomeAdapter(getActivity(), meal,this);
            recyclerView.setAdapter(homeAdapter);
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager mgr = new LinearLayoutManager(getActivity());
            mgr.setOrientation(RecyclerView.HORIZONTAL);
            recyclerView.setLayoutManager(mgr);
            homePresenter = new HomePresenter(this, Repository.getInstance(MealLocalDataSourceImpl.getInstance(getActivity()), RemoteDataSource.getInstance()));
            homePresenter.getMeals();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onFavClick(Meal meal) {
        homePresenter.addToFav(meal);
        Toast.makeText(getActivity(),meal.getStrMeal()+"added to Favourites",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItimClick(Meal meal) {
        Intent intent  = new Intent(this.getActivity(), MealActivity.class);
        intent.putExtra(MainActivity.MEAL ,meal);
        startActivity(intent);
    }

    @Override
    public void setMeals(List<Meal> meals) {
        homeAdapter.setValues(meals);
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }
}