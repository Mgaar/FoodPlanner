package com.example.foodplanner.ui.Meal.presenter;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealIngredients;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.ui.Meal.view.MealActView;

import java.util.ArrayList;

public class MealActPresenter {
    private MealActView mealActView;
    private Repository repository;
    public MealActPresenter(MealActView mealActView, Repository repository) {
        this.mealActView = mealActView;
        this.repository = repository;
    }
    public void getMealIngredients (Meal meal)
    {
       String[] ingredients = new String[]{
                meal.getStrIngredient1(), meal.getStrIngredient2(), meal.getStrIngredient3(),
                meal.getStrIngredient4(), meal.getStrIngredient5(), meal.getStrIngredient6(),
                meal.getStrIngredient7(), meal.getStrIngredient8(), meal.getStrIngredient9(),
                meal.getStrIngredient10(), meal.getStrIngredient11(), meal.getStrIngredient12(),
                meal.getStrIngredient13(), meal.getStrIngredient14(), meal.getStrIngredient15(),
                meal.getStrIngredient16(), meal.getStrIngredient17(), meal.getStrIngredient18(),
                meal.getStrIngredient19(), meal.getStrIngredient20()
        };

        String[] measures = new String[]{
                meal.getStrMeasure1(), meal.getStrMeasure2(), meal.getStrMeasure3(),
                meal.getStrMeasure4(), meal.getStrMeasure5(), meal.getStrMeasure6(),
                meal.getStrMeasure7(), meal.getStrMeasure8(), meal.getStrMeasure9(),
                meal.getStrMeasure10(), meal.getStrMeasure11(), meal.getStrMeasure12(),
                meal.getStrMeasure13(), meal.getStrMeasure14(), meal.getStrMeasure15(),
                meal.getStrMeasure16(), meal.getStrMeasure17(), meal.getStrMeasure18(),
                meal.getStrMeasure19(), meal.getStrMeasure20()
        };
      ArrayList<MealIngredients>  ingredientList = new ArrayList<>();
        String url = "https://www.themealdb.com/images/ingredients/";

        for (int i = 0; i < ingredients.length; i++) {
            String ingredient = ingredients[i];
            String measure = measures[i];
            if (ingredient != null && !ingredient.isEmpty()) {
                ingredientList.add(new MealIngredients(ingredient, measure, url + ingredient + "-Small.png"));
            }
        }
        mealActView.setMealIngredients(ingredientList);
    }
    public void addMeal(Meal meal)
    {
        repository.insertFavouriteMeal(meal);
    }
    public void loadYoutubeVideo(String youtubeUrl) {
        String youtubeEmbedUrl = "https://www.youtube.com/embed/" + getYoutubeVideoId(youtubeUrl);
mealActView.loadWebView(youtubeEmbedUrl);
    }

    private String getYoutubeVideoId(String url) {
        String videoId = "";
        if (url != null && url.contains("v=")) {
            String[] parts = url.split("v=");
            videoId = parts[1].split("&")[0];
        }
        return videoId;
    }
}
