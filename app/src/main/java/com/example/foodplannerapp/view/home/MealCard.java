package com.example.foodplannerapp.view.home;

import androidx.appcompat.app.AppCompatActivity;

//package com.example.foodplannerapp.view.home;
//
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.ImageView;
//import android.widget.TextView;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import com.bumptech.glide.Glide;
//import com.example.foodplannerapp.R;
//import com.example.foodplannerapp.model.Recipe;
//
public class MealCard {}
//    //    String videoUrl = "https://www.example.com/your_video.mp4";
//    public static final String FAV_OBJECT = "FavObject";
//    public static final String PLANNED_MEAL = "planned_meal";
//
//    String TAG = "TAG";
//    private ImageView mealImage;
//    private TextView mealName;
//    private TextView country;
//    private TextView allSteps;
//    private WebView webView;
//    RecyclerView ingS_recyclerView;
//    LinearLayoutManager linearLayoutManager;
//    Intent intent;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.meal);
//
//        webView = findViewById(R.id.web_view);
//        mealImage = findViewById(R.id.img_meal);
//        mealName = findViewById(R.id.tv_mealName);
//        country = findViewById(R.id.tv_country);
//        allSteps = findViewById(R.id.steps);
//
//        intent = getIntent();
//
//        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        Recipe inspMeal = (Recipe) intent.getSerializableExtra("object");
//        if (inspMeal != null) {
//            setThisMealAtCard(inspMeal);
//        }
//    }
//
//    public void setThisMealAtCard(Recipe mealCard) {
//        Recipe  currentMeal = mealCard;
//        mealName.setText(mealCard.getStrMeal());
//        country.setText(mealCard.getStrArea());
//        allSteps.setText(mealCard.getStrInstructions());
//        Glide.with(MealCard.this).load(mealCard.getStrMealThumb()).into(mealImage);
////
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setWebViewClient(new WebViewClient());
//        webView.loadUrl(mealCard.getStrYoutube());
//    }
//
//}