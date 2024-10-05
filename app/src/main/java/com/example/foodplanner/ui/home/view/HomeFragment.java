package com.example.foodplanner.ui.home.view;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.ui.calenderactivity.view.CalendarActivity;
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
    private HomePresenter homePresenter;
    private ConstraintLayout networkLayout;
    private ConstraintLayout noNetworkLayout;
    private Button noNetworkTryAgainButton;
    private TextView noNetworkViewFavMeals;
    private TextView noNetworkViewMyPlan;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

        noNetworkTryAgainButton = view.findViewById(R.id.noNetworkTryAgainButton);
        noNetworkTryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOnline())
                {
                    homePresenter.getMeals();
                    noNetworkLayout.setVisibility(View.GONE);
                    networkLayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    noNetworkLayout.setVisibility(View.VISIBLE);
                    networkLayout.setVisibility(View.GONE);
                }
            }
        });
        noNetworkViewFavMeals =view.findViewById(R.id.noNetworkViewFavMeals);
        noNetworkViewFavMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = NavHostFragment.findNavController(HomeFragment.this);

                navController.navigate(R.id.navigation_favourite);
            }
        });
        noNetworkViewMyPlan = view.findViewById(R.id.noNetworkViewMyPlan);
        noNetworkViewMyPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = NavHostFragment.findNavController(HomeFragment.this);

                navController.navigate(R.id.navigation_calender);
            }
        });
        noNetworkLayout = view.findViewById(R.id.noNetworkLayout);
        networkLayout = view.findViewById(R.id.networkLayout);



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(isOnline())
        {
            noNetworkLayout.setVisibility(View.GONE);
            networkLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            noNetworkLayout.setVisibility(View.VISIBLE);
            networkLayout.setVisibility(View.GONE);
        }
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
    public void onPlanClick(Meal meal) {
        Intent intent  = new Intent(this.getActivity(), CalendarActivity.class);
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

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}