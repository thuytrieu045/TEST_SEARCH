package com.sinhvien.doan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AddRecipeActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        Button btnBackToHome = findViewById(R.id.btnBackToHome);

        // Nút quay lại trang Home
        btnBackToHome.setOnClickListener(v -> {
            Intent intent = new Intent(AddRecipeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
