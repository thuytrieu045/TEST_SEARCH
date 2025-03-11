package com.sinhvien.doan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context context;
    private List<Product> lstProduct;
    private List<Product> lstProductFull; // Danh sách gốc để lọc tìm kiếm

    // Constructor đúng
    public ProductAdapter(Context context, List<Product> lstProduct) {
        this.context = context;
        this.lstProduct = new ArrayList<>(lstProduct);
        this.lstProductFull = new ArrayList<>(lstProduct);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product item = lstProduct.get(position);
        holder.tvName.setText(item.getName());
        holder.tvDescription.setText(item.getDescription());

        // Kiểm tra hình ảnh hợp lệ
        if (item.getImageResource() != 0) {
            holder.imAvatar.setImageResource(item.getImageResource());
        } else {
            holder.imAvatar.setImageResource(R.drawable.dessert); // Hình mặc định
        }
    }

    @Override
    public int getItemCount() {
        return lstProduct.size();
    }

    // Cập nhật danh sách tìm kiếm
    public void filter(String keyword) {
        lstProduct.clear();
        if (keyword.isEmpty()) {
            lstProduct.addAll(lstProductFull);
        } else {
            for (Product item : lstProductFull) {
                if (item.getName().toLowerCase().contains(keyword.toLowerCase())) {
                    lstProduct.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imAvatar;
        TextView tvName, tvDescription;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imAvatar = itemView.findViewById(R.id.ivAvatar);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }
    }
}