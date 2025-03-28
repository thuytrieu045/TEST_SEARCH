package com.sinhvien.doan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        // Lấy data từ Intent
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("title");
            String difficulty = intent.getStringExtra("difficulty");
            int time = intent.getIntExtra("time", -1);
            String ingredients = intent.getStringExtra("ingredients");
            String steps = intent.getStringExtra("steps");
            String imageUrl = intent.getStringExtra("imageUrl");

        // Hiển thị data vào trong bài đăng

            txtRecipeName.setText(name);
            String existingText = txtDifficulty.getText().toString();
            txtDifficulty.setText(existingText + " " + difficulty);
            existingText = txtTime.getText().toString();
            if(time > 60) {
                txtTime.setText(existingText + " " + time / 60 + " giờ " + time % 60 + " phút");
            }
            else {
                txtTime.setText(existingText + " " + time + " phút");
            }
            txtIngredients.setText(ingredients);
            txtSteps.setText(steps);


            if (imageUrl != null && !imageUrl.isEmpty()) {
                Glide.with(this).load(imageUrl).into(imgRecipe);
            } else {
                imgRecipe.setImageResource(R.drawable.dessert);
            }
        }
        btnBack.setOnClickListener(v -> finish());
    }
}
