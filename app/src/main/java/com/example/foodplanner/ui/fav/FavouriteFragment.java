package com.example.foodplanner.ui.fav;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.databinding.FragmentFavouriteBinding;

public class FavouriteFragment extends Fragment {
    private FragmentFavouriteBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      //  CalenderPresenter calenderPresenter =
          //      new ViewModelProvider(this).get(CalenderPresenter.class);

        binding = FragmentFavouriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}