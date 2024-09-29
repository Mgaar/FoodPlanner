package com.example.foodplanner.ui.search.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.database.MealLocalDataSourceImpl;
import com.example.foodplanner.databinding.FragmentSearchBinding;
import com.example.foodplanner.model.Area;
import com.example.foodplanner.model.Category;
import com.example.foodplanner.model.Ingredient;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.RemoteDataSource;
import com.example.foodplanner.ui.search.presenter.SearchPresenter;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment implements SearchOnClick,SearchView{
    private RecyclerView recyclerView;
    private RecyclerView searchIngredientRecycler;
    private RecyclerView searchRegionRecycler;

    private List<Category> categories;
    private List<Ingredient> ingredients;
    private List<Area> areas;

    private SearchCategoryAdapter searchCategoryAdapter;
    private SearchIngredientAdapter searchIngredientAdapter;
    private SearchAreaAdapter searchAreaAdapter;

    private SearchPresenter searchPresenter;
    private FragmentSearchBinding binding;
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

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onFilterClick(String string) {

    }

    @Override
    public void onFilterIngredientClick(String string) {

    }

    @Override
    public void onFilterAreaClick(String string) {

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
    public void showErr(String errString) {
        Toast.makeText(getActivity(),errString,Toast.LENGTH_SHORT).show();
    }
}