package com.sinhvien.doan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> lstProduct;
    private List<Product> lstProductFull; // Danh sách đầy đủ (không lọc)
    private Context context;

    public ProductAdapter(Context context, List<Product> lstProduct) {
        this.context = context;
        this.lstProduct = new ArrayList<>(lstProduct); // Copy danh sách ban đầu
        this.lstProductFull = new ArrayList<>(lstProduct); // Lưu danh sách đầy đủ để phục hồi khi xóa bộ lọc
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View productView = inflater.inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(productView);
    }


    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product item = lstProduct.get(position);
        holder.tvName.setText(item.getName());
        holder.tvDescription.setText(item.getDescription());
        holder.imAvatar.setImageResource(item.getImageResource());

        // 🔥 Khi nhấn vào ảnh, mở ProductDetailActivity và truyền product_id
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("product_id", item.getId()); // Truyền ID sản phẩm
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return lstProduct.size();
    }

    // 🔥 Thêm phương thức filter() để tìm kiếm sản phẩm
    public void filter(String query) {
        lstProduct.clear();
        if (query.isEmpty()) {
            lstProduct.addAll(lstProductFull); // Nếu chuỗi tìm kiếm rỗng, khôi phục danh sách ban đầu
        } else {
            String lowerCaseQuery = query.toLowerCase();
            for (Product product : lstProductFull) {
                if (product.getName().toLowerCase().contains(lowerCaseQuery) ||
                        product.getDescription().toLowerCase().contains(lowerCaseQuery)) {
                    lstProduct.add(product);
                }
            }
        }
        notifyDataSetChanged(); // Cập nhật RecyclerView sau khi lọc
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
