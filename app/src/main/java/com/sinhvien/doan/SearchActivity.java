package com.sinhvien.doan;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class SearchActivity extends AppCompatActivity implements ProductAdapter.ProductCallback {
    private Button btnFilter;
    private ProductAdapter adapter;
    private RecipeAdapter recipeAdapter;
    private List<Product> productList;
    private List<Recipe> recipesList;
    private RecyclerView recyclerView;
    private MyDataBase myDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        EditText searchBar = findViewById(R.id.search_bar);
        recyclerView = findViewById(R.id.recycler_view);
        Button btnBackToHome = findViewById(R.id.btnBackToHome);

        // Khởi tạo danh sách sản phẩm
        productList = new ArrayList<Product>();
        recipesList = new ArrayList<Recipe>();
       // loadProducts(); // Gọi hàm để load dữ liệu mẫu
        myDataBase = new MyDataBase(this);
        btnFilter = findViewById(R.id.btnFilter);

        boolean hasRecipePosts = loadRecipes(); // Hàm kiểm tra tồn tại bài đăng công thức

        // Cấu hình RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductAdapter(productList, this);
        if(hasRecipePosts) {
            recipeAdapter = new RecipeAdapter(this, recipesList);
            recyclerView.setAdapter(new ConcatAdapter(adapter, recipeAdapter));
        }
        else {
            recyclerView.setAdapter(adapter);
        }

        // Xử lý tìm kiếm
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString()); // Gọi hàm lọc từ ProductAdapter
                recipeAdapter.filter(s.toString()); // Gọi hàm lọc từ RecipeAdapter
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Nút quay lại trang Home
        btnBackToHome.setOnClickListener(v -> finish());

        boolean[] isAll = {true};
        // Nút lọc bài đăng
        btnFilter.setOnClickListener(v -> {
            isAll[0] = !isAll[0];

            if(isAll[0]) {
                btnFilter.setText("All");
                showRecipes(isAll[0]);
            }
            else {
                btnFilter.setText("My posts");
                showRecipes(isAll[0]);
            }
        });
    }

    /*private void loadProducts() {
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
            productList.add(new Product(Integer.parseInt(product[0]), product[1], product[2], product[3], Integer.parseInt(product[4])));
        }
    }*/

    // Sử dụng loadRecipes() để tải lên các bài viết đã đăng
    private boolean loadRecipes() {
        Cursor cursor = myDataBase.layTatCaDuLieu();
        if(cursor != null && cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe(
                        cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COT_RECIPE_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COT_TEN_RECIPE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COT_INGREDIENTS)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COT_STEPS)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COT_USER_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COT_IMG_URL)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COT_CATEGORY)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COT_TIME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COT_DOKHO))
                );
                recipesList.add(recipe);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return !recipesList.isEmpty();
    }

    private void showRecipes(boolean isALl) {
        if(isALl) {
            recyclerView.setAdapter(new ConcatAdapter(adapter, recipeAdapter));
        }
        else {
            recyclerView.setAdapter(recipeAdapter);
        }
    }
}