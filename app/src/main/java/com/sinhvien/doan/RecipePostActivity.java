package com.sinhvien.doan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class RecipePostActivity extends AppCompatActivity {
    private ImageView imgRecipe;
    private TextView txtRecipeName, txtDifficulty, txtTime, txtIngredients, txtSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_product_details);

        imgRecipe = findViewById(R.id.imgRecipe);
        txtRecipeName = findViewById(R.id.txtRecipeTitle);
        txtDifficulty = findViewById(R.id.txtRecipeDifficulty);
        txtTime = findViewById(R.id.txtRecipeTime);
        txtIngredients = findViewById(R.id.txtIngredients);
        txtSteps = findViewById(R.id.txtSteps);
        Button btnBack = findViewById(R.id.btnBack);

        // Get data from Intent
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("title");
            String difficulty = intent.getStringExtra("difficulty");
            String time = intent.getStringExtra("time");
            String ingredients = intent.getStringExtra("ingredients");
            String steps = intent.getStringExtra("steps");
            String imageUrl = intent.getStringExtra("imageUrl");

            txtRecipeName.setText(name);
            txtDifficulty.setText(difficulty);
            txtTime.setText(time);
            txtIngredients.setText(ingredients);
            txtSteps.setText(imageUrl);


            if (imageUrl != null && !imageUrl.isEmpty()) {
                Glide.with(this).load(imageUrl).into(imgRecipe);
            } else {
                imgRecipe.setImageResource(R.drawable.dessert);
            }
        }

        btnBack.setOnClickListener(v -> finish());
    }
}
