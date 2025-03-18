package com.sinhvien.doan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ViewPostActivity extends AppCompatActivity {
    private ImageView imgRecipe;
    private TextView txtRecipeName, txtDifficulty, txtTime, txtIngredients, txtSteps;

    String name, diff, time, ingre, steps, imgUrl;

    Button btnBack;

    public ViewPostActivity(String title, String diff, String time, String Ingre, String Steps, String imgURL)
    {
        this.name = name;
        this.diff = diff;
        this.ingre = ingre;
        this.time = time;
        this.steps = steps;
        this.imgUrl = imgURL;
    }

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
        btnBack = findViewById(R.id.btnBack);

        txtRecipeName.setText(name);
        String existingText = txtDifficulty.getText().toString();
        txtDifficulty.setText(existingText + " " + diff);
        existingText = txtTime.getText().toString();
        txtTime.setText(existingText + " " + time);
        txtIngredients.setText(ingre);
        txtSteps.setText(steps);


        if (imgUrl != null && !imgUrl.isEmpty()) {
            Glide.with(this).load(imgUrl).into(imgRecipe);
        } else {
            imgRecipe.setImageResource(R.drawable.dessert);
        }

        btnBack.setOnClickListener(v -> {Intent intent = new Intent(ViewPostActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();});
    }
}
