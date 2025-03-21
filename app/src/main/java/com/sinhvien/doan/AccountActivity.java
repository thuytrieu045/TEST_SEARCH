package com.sinhvien.doan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    Button btnSignout;
    TextView txtName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.account_profile);

        btnSignout = findViewById(R.id.btnSingout);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        txtName = findViewById(R.id.profileName);

        if(user == null){
            Intent in = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(in);
            finish();
        }
        else{
            txtName.setText(user.getEmail());
        }

        // Xử lý nút "Back"
        Button button = findViewById(R.id.btnBack);
        button.setOnClickListener(v -> finish()); // Quay lại màn hình trước đó

        //Xử lý nút đăng xuất
        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutMessage();
            }
        });
    }

    private void showLogoutMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout");
        builder.setMessage("Bạn có chắc muốn đăng xuất?");

        builder.setPositiveButton("Logout", (dialog, which) -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.dismiss();
        });

        builder.show();
    }
}