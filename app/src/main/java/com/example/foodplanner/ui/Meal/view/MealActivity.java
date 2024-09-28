package com.example.foodplanner.ui.Meal.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.MainActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealIngredients;
import com.example.foodplanner.ui.fav.Presenter.FavPresenter;
import com.example.foodplanner.ui.fav.view.FavAdapter;

import java.util.ArrayList;

public class MealActivity extends AppCompatActivity {
    private static final String TAG = "MealActivity";
    Meal meal;
    ImageView mealActImageView;
    TextView mealActMealTxtView;
    TextView mealActMealCategoryTxtView;
    TextView mealActMealAreaTextView;
    TextView mealActMealInstructionsTxtView;
    private String[] ingredients;
    private String[] measures;
    private RecyclerView recyclerView;
    private ArrayList<MealIngredients> mealIngredients;
    private MealIngredientsAdapter mealIngredientsAdapter;
    WebView webView;
    ArrayList<MealIngredients> ingredientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meal);
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            meal = (Meal) intent.getSerializableExtra(MainActivity.MEAL);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.item_background_color));
        }
        mealActImageView = findViewById(R.id.mealActImageView);
        mealActMealTxtView = findViewById(R.id.mealActMealTxtView);
        mealActMealAreaTextView = findViewById(R.id.mealActMealAreaTextView);
        mealActMealCategoryTxtView = findViewById(R.id.mealActMealCategoryTxtView);
        mealActMealInstructionsTxtView = findViewById(R.id.mealActMealInstructionsTxtView);
        mealActMealInstructionsTxtView.setText(meal.getStrInstructions());
        mealActMealTxtView.setText(meal.getStrMeal());
        Glide.with(this)
                .load(meal.getStrMealThumb()) // load image from URL or path
                .into(mealActImageView);
        mealActMealCategoryTxtView.setText(meal.getStrCategory());
        mealActMealAreaTextView.setText(meal.getStrArea());
        /*presenter code */
        ingredientList = new ArrayList<>();

        ingredients = new String[]{
                meal.getStrIngredient1(), meal.getStrIngredient2(), meal.getStrIngredient3(),
                meal.getStrIngredient4(), meal.getStrIngredient5(), meal.getStrIngredient6(),
                meal.getStrIngredient7(), meal.getStrIngredient8(), meal.getStrIngredient9(),
                meal.getStrIngredient10(), meal.getStrIngredient11(), meal.getStrIngredient12(),
                meal.getStrIngredient13(), meal.getStrIngredient14(), meal.getStrIngredient15(),
                meal.getStrIngredient16(), meal.getStrIngredient17(), meal.getStrIngredient18(),
                meal.getStrIngredient19(), meal.getStrIngredient20()
        };

        measures = new String[]{
                meal.getStrMeasure1(), meal.getStrMeasure2(), meal.getStrMeasure3(),
                meal.getStrMeasure4(), meal.getStrMeasure5(), meal.getStrMeasure6(),
                meal.getStrMeasure7(), meal.getStrMeasure8(), meal.getStrMeasure9(),
                meal.getStrMeasure10(), meal.getStrMeasure11(), meal.getStrMeasure12(),
                meal.getStrMeasure13(), meal.getStrMeasure14(), meal.getStrMeasure15(),
                meal.getStrMeasure16(), meal.getStrMeasure17(), meal.getStrMeasure18(),
                meal.getStrMeasure19(), meal.getStrMeasure20()
        };
        /*end of presenter code */
        createIngredientList();
        recyclerView = findViewById(R.id.mealActRecycler);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mgr = new LinearLayoutManager(this);
        mgr.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(mgr);
        mealIngredientsAdapter = new MealIngredientsAdapter(this, ingredientList);
        recyclerView.setAdapter(mealIngredientsAdapter);

         webView = findViewById(R.id.mealActWeb);

        webView.loadUrl(meal.getStrYoutube());

        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());



    }

    public void createIngredientList() {
        // Create an ArrayList to hold Ingredient objects

        String url = "https://www.themealdb.com/images/ingredients/";
        // Loop through the ingredients and measures
        for (int i = 0; i < ingredients.length; i++) {
            String ingredient = ingredients[i];
            String measure = measures[i];

            // Check if ingredient is not empty before adding to the list
            if (ingredient != null && !ingredient.isEmpty()) {
                ingredientList.add(new MealIngredients(ingredient, measure, url + ingredient + "-Small.png"));
            }
        }

    }
}