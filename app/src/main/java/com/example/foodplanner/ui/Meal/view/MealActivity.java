package com.example.foodplanner.ui.Meal.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.foodplanner.database.MealLocalDataSourceImpl;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealIngredients;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.RemoteDataSource;
import com.example.foodplanner.ui.Meal.presenter.MealActPresenter;
import com.example.foodplanner.ui.calenderactivity.view.CalendarActivity;
import com.example.foodplanner.ui.fav.Presenter.FavPresenter;
import com.example.foodplanner.ui.fav.view.FavAdapter;

import java.util.ArrayList;

public class MealActivity extends AppCompatActivity implements MealActView{
    private static final String TAG = "MealActivity";
    private Meal meal;
    private ImageView mealActImageView;
    private TextView mealActMealTxtView;
    private  TextView mealActMealCategoryTxtView;
    private TextView mealActMealAreaTextView;
    private TextView mealActMealInstructionsTxtView;
    private RecyclerView recyclerView;
    private MealIngredientsAdapter mealIngredientsAdapter;
    private WebView webView;
    private ArrayList<MealIngredients> ingredientList;
    private MealActPresenter mealActPresenter;
    private Button mealActAddToFavBtn;
    private Button mealActAddToPlanBtn;
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

        mealActAddToFavBtn = findViewById(R.id.mealActAddToFavBtn);

        mealActPresenter = new MealActPresenter(this , Repository.getInstance(MealLocalDataSourceImpl.getInstance(this), RemoteDataSource.getInstance()));
        mealActPresenter.getMealIngredients(meal);
        mealActAddToFavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            mealActPresenter.addMeal(meal);
                Toast.makeText(MealActivity.this,meal.getStrMeal()+"added to Favourites",Toast.LENGTH_SHORT).show();

            }
        });

        mealActAddToPlanBtn = findViewById(R.id.mealActAddToPlanBtn);
        mealActAddToPlanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(MealActivity.this, CalendarActivity.class);
                intent.putExtra(MainActivity.MEAL ,meal);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.mealActRecycler);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mgr = new LinearLayoutManager(this);
        mgr.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(mgr);
        mealIngredientsAdapter = new MealIngredientsAdapter(this, ingredientList);
        recyclerView.setAdapter(mealIngredientsAdapter);

         webView = findViewById(R.id.mealActWeb);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        mealActPresenter.loadYoutubeVideo(meal.getStrYoutube());

    }


    @Override
    public void setMealIngredients(ArrayList<MealIngredients> mealIngredients) {
        ingredientList = mealIngredients;
    }

    @Override
    public void loadWebView(String url) {
        webView.loadUrl(url);

    }

}