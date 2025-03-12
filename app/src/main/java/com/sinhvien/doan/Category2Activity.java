package com.sinhvien.doan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class Category2Activity extends AppCompatActivity {
    private RecyclerView rvListC;
    private ProductAdapter productAdapter;
    private ArrayList<Product> lstProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category2);

        // Xử lý nút "Back"
        Button button = findViewById(R.id.btnback);
        button.setOnClickListener(v -> finish()); // Trả về MainActivity

        // Ánh xạ RecyclerView
        rvListC = findViewById(R.id.rvList);
        lstProduct = new ArrayList<>();

        // Load dữ liệu sản phẩm
        LoadData();

        // Thiết lập Adapter và LayoutManager cho RecyclerView
        productAdapter = new ProductAdapter(this,lstProduct);
        rvListC.setAdapter(productAdapter);
        rvListC.setLayoutManager(new LinearLayoutManager(this));

        // Xử lý hiển thị tự động căn lề
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rvList), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void LoadData() {
        lstProduct.add(new Product("5", "Bánh mì nướng phủ bơ nghiền và trứng", "Bánh mì nướng phủ bơ nghiền, trứng luộc lòng đào thái lát, rắc tiêu đen và ớt bột, tạo hương vị béo ngậy, thơm ngon. ", "trungbo.jpg", 5.99, R.drawable.trungbo));
        lstProduct.add(new Product("6", "Bánh mì cá hồi", "Bánh bagel hạt giòn thơm, kẹp kem phô mai, bơ lát và cá hồi xông khói, tạo hương vị béo ngậy, mặn mà. ", "bothit.jpg", 3.49, R.drawable.bothit));
        lstProduct.add(new Product("7", "bánh mì xông khói", "Bagel hạt giòn, kẹp kem phô mai, cá hồi xông khói, hành tím và nụ bạch hoa, tạo hương vị béo, mặn và thanh nhẹ", "xongkhoi.jpg", 6.99, R.drawable.xongkhoi));
        lstProduct.add(new Product("8", "bánh mì tôm hùm", "Bánh mì nướng giòn phủ sốt đỏ đậm đà, tôm hùm tươi ngọt, thịt cua thơm béo và rau xanh tươi mát, tạo hương vị hài hòa và hấp dẫn", "tom.jpg", 6.99, R.drawable.tom));

    }
}
