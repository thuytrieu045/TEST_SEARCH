package com.sinhvien.doan;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private ListView listViewCart;
    private TextView tvTotalPrice;
    private Button btnCheckout;
    private CartAdapter cartAdapter;
    private List<Product> cartList;
    private double totalPrice = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        listViewCart = findViewById(R.id.listViewCart);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        btnCheckout = findViewById(R.id.btnCheckout);

        // Dữ liệu giả lập giỏ hàng
        cartList = new ArrayList<>();
        cartList.add(new Product(1, "Bánh Chocolate", "Bánh sô-cô-la mềm mịn", 5.99, R.drawable.chocolate_cake));
        cartList.add(new Product(2, "Croissant", "Bánh sừng bò giòn tan", 3.49, R.drawable.croissant));

        cartAdapter = new CartAdapter(this, cartList, this::updateTotalPrice);
        listViewCart.setAdapter(cartAdapter);

        updateTotalPrice();

        btnCheckout.setOnClickListener(v -> {
            // Xử lý thanh toán
        });
    }

    // Cập nhật tổng tiền
    private void updateTotalPrice() {
        totalPrice = 0;
        for (Product product : cartList) {
            totalPrice += (double) product.getPrice();
        }
        tvTotalPrice.setText(String.format("Total: $%.2f", totalPrice));
    }
}
