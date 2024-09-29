package com.example.foodplanner.ui.search.view;

import com.example.foodplanner.model.Area;
import com.example.foodplanner.model.Category;
import com.example.foodplanner.model.Ingredient;

import java.util.List;

public interface SearchView {
    void setCategories(List<Category> categories);
    void setIngredients(List<Ingredient> ingredients);
    void setAreas(List<Area> areas);
    void showErr (String errString);
}
