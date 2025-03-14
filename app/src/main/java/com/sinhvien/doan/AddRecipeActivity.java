package com.sinhvien.doan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AddRecipeActivity extends AppCompatActivity {
    private EditText edtTitle, edtDifficulty,edtTime,edtIngredients,edtSteps,edtImage;
    private Spinner spinnerCategory;
    private String selectedCategory;
    private List<Product> listProduct;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        edtTitle = findViewById(R.id.edtTitle);
        edtDifficulty = findViewById(R.id.edtDifficulty);
        edtTime = findViewById(R.id.edtTime);
        edtIngredients = findViewById(R.id.edtIngredients);
        edtSteps = findViewById(R.id.edtSteps);
        edtImage = findViewById(R.id.edtImageURL);
        Button btnBackToHome = findViewById(R.id.btnBackToHome);
        Button btnSubmit = findViewById(R.id.btnSave);
        spinnerCategory = findViewById(R.id.spinnerCategory);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.recipe_categories, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinnerCategory.setAdapter(adapter);
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedCategory = "";
            }
        });

        // Nút quay lại trang Home
        btnBackToHome.setOnClickListener(v -> finish());

        btnSubmit.setOnClickListener(v -> sendRecipeData());
    }


    private void sendRecipeData()
    {
        String title = edtTitle.getText().toString().trim();
        String difficulty = edtDifficulty.getText().toString().trim();
        String time = edtTime.getText().toString().trim();
        String ingredients = edtIngredients.getText().toString().trim();
        String steps = edtSteps.getText().toString().trim();
        String urlimage;

        if(edtImage == null) {
            urlimage = "";
        }
        else{
            urlimage = edtImage.getText().toString();
        }

        if(title.isEmpty() || difficulty.isEmpty() || time.isEmpty() || ingredients.isEmpty() || steps.isEmpty()
        || selectedCategory.isEmpty())
        {
            Toast.makeText(this, "Bạn phải điền hết ô trống còn lại!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(AddRecipeActivity.this, RecipePostActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("difficulty", difficulty);
        intent.putExtra("ingredients", ingredients);
        intent.putExtra("steps", steps);
        intent.putExtra("time", time);
        intent.putExtra("imageUrl", urlimage);
        startActivity(intent);
    }
}
