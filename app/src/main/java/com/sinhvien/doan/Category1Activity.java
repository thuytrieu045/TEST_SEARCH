package com.sinhvien.doan;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Category1Activity extends AppCompatActivity {
    private RecyclerView rvListC;
    private ProductAdapter productAdapter;
    private List<Product> lstProduct; // Sử dụng List thay vì ArrayList để linh hoạt hơn

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category1);

        // Xử lý nút "Back"
        Button button = findViewById(R.id.btnback);
        button.setOnClickListener(v -> finish()); // Quay lại màn hình trước đó

        // Ánh xạ RecyclerView
        rvListC = findViewById(R.id.rvList);
        lstProduct = new ArrayList<>();

        // Tải dữ liệu sản phẩm
        loadData();

        // Thiết lập Adapter và LayoutManager cho RecyclerView
        productAdapter = new ProductAdapter(this, lstProduct); // Đã sửa lỗi truyền context
        rvListC.setAdapter(productAdapter);
        rvListC.setLayoutManager(new LinearLayoutManager(this));

        // Xử lý hiển thị tự động căn lề
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rvList), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void loadData() {
        lstProduct.add(new Product("1", "Choco", "Chocolate Cake", "choco.jpg", 5.99, R.drawable.choco));
        lstProduct.add(new Product("2", "Bread", "Freshly baked bread", "bread.jpg", 3.49, R.drawable.bread));
        lstProduct.add(new Product("3", "Rocher", "Ferrero Rocher Chocolate", "rocher.jpg", 6.99, R.drawable.rocher));
    }
}