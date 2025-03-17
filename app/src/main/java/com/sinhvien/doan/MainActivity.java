package com.sinhvien.doan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //Khúc na của Firebase
        mAuth = FirebaseAuth.getInstance();

        String email = "ThuyTrieu@gmail.com";
        String pass ="Snowwhite@2005";

        mAuth.createUserWithEmailAndPassword(email, pass ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                }else {
                    Log.w("Main", "createUserWithEmail:failure", task.getException());
                    Toast.makeText(MainActivity.this, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Xử lý sự kiện nhấn nút "Sweet Desert"
        Button button = findViewById(R.id.btnSweetDesert);
        Intent intent = new Intent(this, Category1Activity.class);
        button.setOnClickListener(v -> v.getContext().startActivity(intent));

        // Xử lý sự kiện nhấn nút "Breakfast"
        Button btnBreakfast = findViewById(R.id.btnBreakfast);
        btnBreakfast.setOnClickListener(v -> {
            Intent intent2 = new Intent(MainActivity.this, Category2Activity.class);
            startActivity(intent2);
        });

        // Xử lý sự kiện nhấn nút "Birthday Cake"
        Button btnbirthdaycake = findViewById(R.id.btnBirthdayCake);
        btnbirthdaycake.setOnClickListener(v -> {
            Intent intent3 = new Intent(MainActivity.this, Category3Activity.class);
            startActivity(intent3);
        });
        // Xử lý sự kiện nhấn nút "View Information"
        Button btnViewInfo = findViewById(R.id.btnViewInformation);
        btnViewInfo.setOnClickListener(v -> {
            Intent intent4 = new Intent(MainActivity.this, SpecialCakeActivity.class);
            startActivity(intent4);
        });


        // Xử lý sự kiện BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.navMenu);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.mnHome) {
                    return true; // Đang ở trang Home, không cần chuyển
                } else if (itemId == R.id.mnPost) {
                    startActivity(new Intent(MainActivity.this, AddRecipeActivity.class)); // Chuyển đến SearchActivity
                    return true;
                }else if (itemId == R.id.mnSearch) {
                    startActivity(new Intent(MainActivity.this, SearchActivity.class)); // Chuyển đến SearchActivity
                    return true;
                } else if (itemId == R.id.mnAccount) {
                    startActivity(new Intent(MainActivity.this, AccountActivity.class));
                    return true;
                }

                return false;
            }

        });

        // Xử lý tự động căn lề phù hợp với hệ thống
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
