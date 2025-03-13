package com.sinhvien.doan;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Nhận product_id từ Intent
        String productId = getIntent().getStringExtra("product_id");

        // Chọn layout tương ứng
        if ("1".equals(productId)) {
            setContentView(R.layout.banh_choco);
        } else if ("2".equals(productId)) {
            setContentView(R.layout.flan);
        } else if ("3".equals(productId)) {
            setContentView(R.layout.rocher);
        } else if ("4".equals(productId)) {
            setContentView(R.layout.tiramisu);
        } else if ("5".equals(productId)) {
            setContentView(R.layout.trungbo);
        } else if ("6".equals(productId)) {
            setContentView(R.layout.cahoi);
        } else if ("7".equals(productId)) {
            setContentView(R.layout.xongkhoi);
        } else if ("8".equals(productId)) {
            setContentView(R.layout.tom);
        } else if ("9".equals(productId)) {
            setContentView(R.layout.banhkem_traxanh);
        } else if ("10".equals(productId)) {
            setContentView(R.layout.banhkem_xoai);
        } else if ("11".equals(productId)) {
            setContentView(R.layout.banhkem_traicay);
        } else if ("12".equals(productId)) {
            setContentView(R.layout.banhkem_chanh);
        } else {
            finish(); // Nếu không có product_id hợp lệ, thoát Activity
        }

        // Xử lý nút "Quay lại"
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
    }
}
