package com.sinhvien.doan;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Category1Activity extends AppCompatActivity implements ProductAdapter.ProductCallback {
    private RecyclerView rvListC;
    private ProductAdapter productAdapter;
    private RecipeAdapter recipeAdapter;
    private ArrayList<Product> lstProduct;
    FloatingActionButton fbAdd;
    private ArrayList<Recipe> lstRecipe;
    private MyDataBase myDatabase;
    private int imageResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category1);
        rvListC = findViewById(R.id.rvList);
        fbAdd = findViewById(R.id.fbAdd);
        fbAdd.setOnClickListener(view -> addProductDiaglog());

        // Nút quay về
        Button button = findViewById(R.id.btnback);
        button.setOnClickListener(v -> finish());

        // Khởi tạo

        lstProduct = new ArrayList<>();
        lstRecipe = new ArrayList<>();
        myDatabase = new MyDataBase(this); // Khởi tạo database

        // Load dữ liệu sản phẩm
        //loadProductData();
        boolean hasRecipes = loadRecipeData(); // Kiểm tra nếu có bài đã được đăng

        //Lấy dữ liệu
        lstProduct = ProductDataQuery.getAll(this);
        productAdapter = new ProductAdapter(lstProduct);
        productAdapter.setCallback(this);
//Gắn adapter
        LinearLayoutManager linearLayoutManager = new
                LinearLayoutManager(this);
        rvListC.setAdapter(productAdapter);
        rvListC.setLayoutManager(linearLayoutManager);
    }

    void addProductDiaglog() {
//Khởi tạo dialog để thêm thông tin mới
        AlertDialog.Builder alertDialog = new
                AlertDialog.Builder(Category1Activity.this);
        alertDialog.setTitle("Thêm mới");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_product, null);
        alertDialog.setView(dialogView);
        EditText edName = (EditText) dialogView.findViewById(R.id.edName);
        EditText eddescription = (EditText) dialogView.findViewById(R.id.eddescription);
        EditText edAvatar = (EditText)
                dialogView.findViewById(R.id.edAvatar);
        alertDialog.setPositiveButton("Đồng ý", (dialog, which) -> {
            String name = edName.getText().toString();
            String description = eddescription.getText().toString();
            String avatar = edAvatar.getText().toString();
            if (name.isEmpty()) {
                Toast.makeText(Category1Activity.this, "Nhập dữ liệu không hợp lệ", Toast.LENGTH_LONG).show();
            }
            else {
                Product product = new Product(0, name, description, avatar, imageResource);
                long id = ProductDataQuery.Insert(Category1Activity.this, product);
                if(id > 0) {
                    Toast.makeText(Category1Activity.this, "Thêm thành công",
                            Toast.LENGTH_LONG).show();
                    resetData();
                    dialog.dismiss();
                }
            }
        });
        alertDialog.setNegativeButton("Hủy", (dialog, which)-> {
            dialog.dismiss();
        });
        alertDialog.create();
        alertDialog.show();
    }
    void updateProductDialog(Product product) {
//Khởi tạo dialog để thêm thông tin mới
        AlertDialog.Builder alertDialog = new
                AlertDialog.Builder(Category1Activity.this);
        alertDialog.setTitle("Cập nhật");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_product, null);
        alertDialog.setView(dialogView);
        EditText edName = (EditText) dialogView.findViewById(R.id.edName);
        EditText eddescription = (EditText)dialogView.findViewById(R.id.eddescription);
        EditText edAvatar = (EditText) dialogView.findViewById(R.id.edAvatar);
//gán dữ liệu
        edName.setText(product.getName());
        eddescription.setText(product.getDescription());
        edAvatar.setText(product.getAvatar());
        alertDialog.setPositiveButton("Đồng ý", (dialog, which) -> {
            product.setName(edName.getText().toString());
            product.setDescription(eddescription.getText().toString());
            product.setAvatar(edAvatar.getText().toString());
            if (product.getName().isEmpty()) {
                Toast.makeText(Category1Activity.this, "Nhập dữ liệu không hợp lệ", Toast.LENGTH_LONG).show();
            }
            else {
                int id = ProductDataQuery.update(Category1Activity.this, product);
                if(id > 0) {
                    Toast.makeText(Category1Activity.this, "Cập nhật thành công",
                            Toast.LENGTH_LONG).show();
                    resetData();
                    dialog.dismiss();
                }
            }
        });
        alertDialog.setNegativeButton("Hủy", (dialog, which)-> {
            dialog.dismiss();
        });
        alertDialog.create();

        alertDialog.show();
    }

    @Override
    public void onItemClick(int id) {
// Tìm Product theo ID
        Product selectedProduct = null;
        for (Product product : lstProduct) {
            if (product.getId() == id) {
                selectedProduct = product;
                break;
            }
        }
        if (selectedProduct != null) {
            Intent i = new Intent(this, ProductDetailActivity.class);
            i.putExtra("productId", String.valueOf(id));
            i.putExtra("productName", selectedProduct.getName());
            i.putExtra("productDescription", selectedProduct.getDescription());
            i.putExtra("productAvatar", selectedProduct.getAvatar()); // Truyền đường dẫn ảnh
            startActivity(i);
        }
    }

    void resetData() {
        lstProduct.clear();
        lstProduct.addAll(ProductDataQuery.getAll(Category1Activity.this));
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemDeleteClicked(Product product, int position) {
        boolean result = ProductDataQuery.delete(Category1Activity.this, product.getId());
        if(result) {
            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_LONG).show();
            resetData();
        }
        else
            Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemEditClicked(Product product, int position) {
        updateProductDialog(product);
    }

    // Load dữ liệu Product
   /* private void loadProductData() {
        lstProduct = new ArrayList<>();
        lstProduct.add(new Product(1, "Choco", "Bánh Choco là bánh quy phủ sô cô la", "choco.jpg", R.drawable.choco));
        lstProduct.add(new Product(2, "Flan", "Bánh caramel mềm mịn", "flan.jpg", R.drawable.flan));
        lstProduct.add(new Product(3, "Rocher", "Ferrero Rocher Chocolate", "rocher.jpg", R.drawable.rocher));
        lstProduct.add(new Product(4, "Tiramisu", "Món tráng miệng Ý", "tiraminsu.jpg", R.drawable.tiraminsu));
    }*/

    // Load dữ liệu Recipe từ Category2
    private boolean loadRecipeData() {
        Cursor cursor = myDatabase.getRecipeByCategory(1); // Load category 1 (Sweet Dessert)
        if (cursor != null && cursor.moveToFirst()) {
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
                lstRecipe.add(recipe);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return !lstRecipe.isEmpty(); // Trả về true nếu có công thức trong DB
    }
}
