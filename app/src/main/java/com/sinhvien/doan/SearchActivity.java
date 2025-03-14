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
        btnBackToHome.setOnClickListener(v -> finish());
    }

    private void loadProducts() {
        String[][] products = {
                {"1", "Choco", "Bánh Choco là bánh quy phủ sô cô la, thường có nhân kem hoặc mềm bên trong.", "banhchoco.jpg", String.valueOf(R.drawable.banhchoco)},
                {"2", "Flan", "Bánh caramel mềm mịn, làm từ trứng, sữa và đường.", "flan.jpg", String.valueOf(R.drawable.flan)},
                {"3", "Rocher", "Ferrero Rocher Chocolate", "rocher.jpg", String.valueOf(R.drawable.rocher)},
                {"4", "Tiramisu", "Bánh cà phê Ý ngon tuyệt", "tiramisu.jpg", String.valueOf(R.drawable.tiramisu)},
                {"5", "Bánh Mì bơ nghiền và trứng", "Bánh kem mềm mịn", "mousse_cake.jpg", String.valueOf(R.drawable.trungbo)},
                {"6", "Bánh mì cá hồi", "Bánh bagel kẹp cá hồi xông khói, bơ lát, phô mai.", "cahoi.jpg", String.valueOf(R.drawable.bothit)},
                {"7", "Bánh mì xông khói", "Bánh mì kẹp kem phô mai, cá hồi xông khói, hành tím.", "xongkhoi.jpg", String.valueOf(R.drawable.xongkhoi)},
                {"8", "Bánh mì tôm hùm", "Bánh mì nướng phủ sốt đỏ đậm đà, tôm hùm tươi.", "tomhum.jpg", String.valueOf(R.drawable.tom)},
                {"9", "Bánh kem trà xanh", "Bánh làm từ bột trà xanh Nhật Bản.", "matcha.jpg", String.valueOf(R.drawable.matcha)},
                {"10", "Bánh kem xoài", "Bánh kem xoài mềm mịn, xen kẽ kem tươi và xoài.", "mango.jpg", String.valueOf(R.drawable.mango)},
                {"11", "Bánh kem trái cây", "Trang trí với dâu, kiwi, xoài, việt quất.", "traicay.jpg", String.valueOf(R.drawable.traicay)},
                {"12", "Bánh kem chanh", "Bánh có vị chua thanh mát, sốt chanh dịu nhẹ.", "lemoncake.jpg", String.valueOf(R.drawable.lemoncake)}
        };

        for (String[] product : products) {
            productList.add(new Product(product[0], product[1], product[2], product[3], Integer.parseInt(product[4])));
        }
    }

}