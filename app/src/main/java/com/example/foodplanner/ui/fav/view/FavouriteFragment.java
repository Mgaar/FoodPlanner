package com.example.foodplanner.ui.fav.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.database.MealLocalDataSourceImpl;
import com.example.foodplanner.databinding.FragmentFavouriteBinding;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.RemoteDataSource;
import com.example.foodplanner.ui.fav.Presenter.FavPresenter;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment implements FavOnClick , FavView{
    private FragmentFavouriteBinding binding;
    private RecyclerView recyclerView;
    private ArrayList<Meal> meal;
    private FavAdapter favAdapter;
    private FavPresenter favPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

            recyclerView = view.findViewById(R.id.favouriteRecyclerView);
            meal = new ArrayList<Meal>();
            favAdapter = new FavAdapter(getActivity(),meal,this);
            recyclerView.setAdapter(favAdapter);
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager mgr = new LinearLayoutManager(getActivity());
            mgr.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(mgr);
            favPresenter = new FavPresenter(this, Repository.getInstance(MealLocalDataSourceImpl.getInstance(getActivity()), RemoteDataSource.getInstance()));
            favPresenter.getFavMeals(this);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onRemoveClick(Meal meal) {
        favPresenter.removeMeal(meal);
    }

    @Override
    public void getMeals(List<Meal> meals) {
        favAdapter.setValues(meals);
        favAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String str) {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}