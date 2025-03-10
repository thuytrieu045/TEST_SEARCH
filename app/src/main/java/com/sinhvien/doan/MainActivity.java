package com.sinhvien.doan;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Xử lý sự kiện nhấn nút "Sweet Desert"
        Button button = findViewById(R.id.btnSweetDesert);
        Intent intent = new Intent(this, Category1Activity.class);
        button.setOnClickListener(v -> v.getContext().startActivity(intent));

        // Xử lý sự kiện BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.navMenu);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.mnHome) {
                    return true; // Đang ở trang Home, không cần chuyển
                } else if (itemId == R.id.mnSearch) {
                    startActivity(new Intent(MainActivity.this, SearchActivity.class)); // Chuyển đến SearchActivity
                    return true;
                } else if (itemId == R.id.mnShoppingCart) {
                    startActivity(new Intent(MainActivity.this, CartActivity.class));
                    return true;
                } else if (itemId == R.id.mnAccount) {
                    startActivity(new Intent(MainActivity.this, AccountActivity.class));
                    return true;
                }

                return false;
            }

        });
        // Load DangKyFragment vào FragmentContainerView
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new dangky());
        fragmentTransaction.replace(R.id.fragment_container, new dangnhap());
        fragmentTransaction.commit();
        // Xử lý tự động căn lề phù hợp với hệ thống
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
