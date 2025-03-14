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
        lstProduct.add(new Product("1", "Choco", "Bánh Choco là bánh quy phủ sô cô la, thường có nhân kem hoặc mềm bên trong", "banhchoco.jpg", R.drawable.choco));
        lstProduct.add(new Product("2", "Flan", "Bánh caramel mềm mịn, làm từ trứng, sữa và đường.", "bread.jpg", R.drawable.flan));
        lstProduct.add(new Product("3", "Rocher", "Ferrero Rocher Chocolate", "rocher.jpg", R.drawable.rocher));
        lstProduct.add(new Product("4", "Tiramisu", "Món tráng miệng Ý, gồm bánh ladyfingers nhúng cà phê, kem mascarpone và cacao.", "tiramisu.jpg", R.drawable.tiraminsu));
    }
}