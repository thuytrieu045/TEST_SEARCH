package com.sinhvien.doan;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private ProductAdapter adapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        EditText searchBar = findViewById(R.id.search_bar);
        ListView listView = findViewById(R.id.list_view);

        // Danh sách sản phẩm
        productList = new ArrayList<>();
        productList.add(new Product(1, "Bánh Chocolate", "Bánh sô-cô-la mềm mịn", 5.99, R.drawable.chocolate_cake));
        productList.add(new Product(2, "Croissant", "Bánh sừng bò giòn tan", 5.99, R.drawable.croissant));
        productList.add(new Product(3, "Cheesecake", "Bánh phô mai thơm ngon", 5.99, R.drawable.cheesecake));
        productList.add(new Product(4, "Tiramisu", "Bánh cà phê Ý ngon tuyệt", 5.99, R.drawable.tiramisu));
        productList.add(new Product(5, "Bánh Mousse", "Bánh kem mềm mịn", 5.99, R.drawable.mousse_cake));

        adapter = new ProductAdapter(this, productList);
        listView.setAdapter((ListAdapter) adapter);

        // Xử lý tìm kiếm theo từ khóa
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString()); // Gọi phương thức filter() từ adapter
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}
