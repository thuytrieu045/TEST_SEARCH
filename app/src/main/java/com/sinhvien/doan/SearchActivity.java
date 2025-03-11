package com.sinhvien.doan;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private ProductAdapter adapter;
    private List<Product> productList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        EditText searchBar = findViewById(R.id.search_bar);
        recyclerView = findViewById(R.id.recycler_view);
        Button btnBackToHome = findViewById(R.id.btnBackToHome);

        // Khởi tạo danh sách sản phẩm
        productList = new ArrayList<Product>();
        loadProducts(); // Gọi hàm để load dữ liệu mẫu

        // Cấu hình RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(adapter);

        // Xử lý tìm kiếm
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString()); // Gọi hàm lọc từ ProductAdapter
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Nút quay lại trang Home
        btnBackToHome.setOnClickListener(v -> {
            Intent intent = new Intent(SearchActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void loadProducts() {
        productList.add(new Product(1, "Bánh Chocolate", "Bánh sô-cô-la mềm mịn", 5.99, R.drawable.chocolate_cake));
        productList.add(new Product(2, "Croissant", "Bánh sừng bò giòn tan", 5.99, R.drawable.croissant));
        productList.add(new Product(3, "Cheesecake", "Bánh phô mai thơm ngon", 5.99, R.drawable.cheesecake));
        productList.add(new Product(4, "Tiramisu", "Bánh cà phê Ý ngon tuyệt", 5.99, R.drawable.tiramisu));
        productList.add(new Product(5, "Bánh Mousse", "Bánh kem mềm mịn", 5.99, R.drawable.mousse_cake));
    }
}