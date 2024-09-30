package com.example.foodplanner.ui.search.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.MainActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.database.MealLocalDataSourceImpl;
import com.example.foodplanner.databinding.FragmentSearchBinding;
import com.example.foodplanner.model.Area;
import com.example.foodplanner.model.Category;
import com.example.foodplanner.model.Ingredient;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.RemoteDataSource;
import com.example.foodplanner.ui.Meal.view.MealActivity;
import com.example.foodplanner.ui.search.presenter.SearchPresenter;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment implements SearchOnClick, SearchViewer {
    public static final String CAT = "category";
    public static final String TYPE = "filtertype";
    public static final String LIST = "listof";
    public static final String ING = "ingredient";
    public static final String AREA = "area";

    private RecyclerView recyclerView;
    private RecyclerView searchIngredientRecycler;
    private RecyclerView searchRegionRecycler;
    private SearchView searchViewer;
    private RecyclerView searchUserSearchFragRecycler;

    private List<Category> categories;
    private List<Ingredient> ingredients;
    private List<Area> areas;
    private List<Meal> meals;

    private SearchCategoryAdapter searchCategoryAdapter;
    private SearchIngredientAdapter searchIngredientAdapter;
    private SearchAreaAdapter searchAreaAdapter;
    private SearchMealAdapter searchMealAdapter;
    private SearchPresenter searchPresenter;
    private FragmentSearchBinding binding;

    TextView searchUserNoResultTxtView ;
    private static final String TAG = "SearchFragment";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchUserNoResultTxtView = view.findViewById(R.id.searchUserNoResultTxtView);
        searchUserNoResultTxtView.setVisibility(View.GONE);

        recyclerView=view.findViewById(R.id.searchCategoryRecycler);
        searchIngredientRecycler =view.findViewById(R.id.searchIngredientRecycler);
        searchRegionRecycler=view.findViewById(R.id.searchRegionRecycler);

        categories = new ArrayList<Category>();
        ingredients = new ArrayList<Ingredient>();
        areas = new ArrayList<Area>();

        searchPresenter = new SearchPresenter(this, Repository.getInstance(MealLocalDataSourceImpl.getInstance(getActivity()), RemoteDataSource.getInstance()));
        searchPresenter.getCategoryList();
        searchPresenter.getIngredientList();
        searchPresenter.getAreaList();

        searchCategoryAdapter = new SearchCategoryAdapter(getActivity(),categories,this);
        searchIngredientAdapter = new SearchIngredientAdapter(getActivity(),ingredients,this);
        searchAreaAdapter = new SearchAreaAdapter(getActivity(),areas,this);

        recyclerView.setAdapter(searchCategoryAdapter);
        searchIngredientRecycler.setAdapter(searchIngredientAdapter);
        searchRegionRecycler.setAdapter(searchAreaAdapter);

        recyclerView.setHasFixedSize(true);
        searchIngredientRecycler.setHasFixedSize(true);
        searchRegionRecycler.setHasFixedSize(true);

        LinearLayoutManager mgr = new LinearLayoutManager(getActivity());
        mgr.setOrientation(RecyclerView.HORIZONTAL);

        LinearLayoutManager ingMgr = new LinearLayoutManager(getActivity());
        ingMgr.setOrientation(RecyclerView.HORIZONTAL);

        LinearLayoutManager areaMgr = new LinearLayoutManager(getActivity());
        areaMgr.setOrientation(RecyclerView.HORIZONTAL);

        recyclerView.setLayoutManager(mgr);
        searchIngredientRecycler.setLayoutManager(ingMgr);
        searchRegionRecycler.setLayoutManager(areaMgr);

        searchViewer = view.findViewById(R.id.searchView);
        searchUserSearchFragRecycler = view.findViewById(R.id.searchUserSearchFrag);
        searchUserSearchFragRecycler.setVisibility(View.GONE);
        meals = new ArrayList<Meal>();
        searchMealAdapter = new SearchMealAdapter(getActivity(),meals,this);
        searchUserSearchFragRecycler.setAdapter(searchMealAdapter);
        searchUserSearchFragRecycler.setHasFixedSize(true);
        LinearLayoutManager userSearchMgr = new LinearLayoutManager(getActivity());
        userSearchMgr.setOrientation(RecyclerView.VERTICAL);
        searchUserSearchFragRecycler.setLayoutManager(userSearchMgr);
        searchViewer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
searchViewer.setIconified(false);
            }
        });
        searchViewer.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchPresenter.getMealSearch(query);
                Log.i(TAG, "onQueryTextSubmit: "+query);
                searchUserSearchFragRecycler.setVisibility(View.VISIBLE);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i(TAG, "onQueryTextChange: "+newText);
                if(newText.isEmpty() )
                {
                    searchUserSearchFragRecycler.setVisibility(View.GONE);
                    searchUserNoResultTxtView.setVisibility(View.GONE);

                }
                else
                {
                    searchPresenter.getMealSearch(newText);
                    searchUserSearchFragRecycler.setVisibility(View.VISIBLE);


                }
                return false;
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onFilterClick(String string) {

        NavController navController = NavHostFragment.findNavController(this);
        Bundle args = new Bundle();
        args.putString(this.TYPE, this.CAT);
        args.putString(this.LIST, string);  // pass any data you need
        navController.navigate(R.id.action_searchFragment_to_listFragment, args);
    }

    @Override
    public void onFilterIngredientClick(String string) {
        NavController navController = NavHostFragment.findNavController(this);
        Bundle args = new Bundle();
        args.putString(this.TYPE, this.ING);
        args.putString(this.LIST, string);  // pass any data you need
        navController.navigate(R.id.action_searchFragment_to_listFragment, args);
    }

    @Override
    public void onFilterAreaClick(String string) {
        NavController navController = NavHostFragment.findNavController(this);
        Bundle args = new Bundle();
        args.putString(this.TYPE, this.AREA);
        args.putString(this.LIST, string);  // pass any data you need
        navController.navigate(R.id.action_searchFragment_to_listFragment, args);
    }

    @Override
    public void onUserSearchClick(Meal meal) {
        Intent intent  = new Intent(this.getActivity(), MealActivity.class);
        intent.putExtra(MainActivity.MEAL ,meal);
        startActivity(intent);
    }

    @Override
    public void setCategories(List<Category> categories) {

        searchCategoryAdapter.setValues(categories);
        searchCategoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void setIngredients(List<Ingredient> ingredients) {
        searchIngredientAdapter.setValues(ingredients);
        searchIngredientAdapter.notifyDataSetChanged();
    }

    @Override
    public void setAreas(List<Area> areas) {
        searchAreaAdapter.setValues(areas);
        searchAreaAdapter.notifyDataSetChanged();
    }

    @Override
    public void setMeals(List<Meal> meals) {
        if (meals == null )
        {
            searchUserNoResultTxtView.setVisibility(View.VISIBLE);
            searchUserSearchFragRecycler.setVisibility(View.GONE);


        }
        else
        {
            searchUserNoResultTxtView.setVisibility(View.GONE);
            searchMealAdapter.setValues(meals);
            searchMealAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void showErr(String errString) {
        Toast.makeText(getActivity(),errString,Toast.LENGTH_SHORT).show();
    }
}