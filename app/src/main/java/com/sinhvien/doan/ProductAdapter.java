package com.sinhvien.doan;

import android.content.Context;
import android.content.Intent;
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
    ArrayList<Product> lstProduct;
    Context context;

    public ProductAdapter(ArrayList<Product> lstProduct) {
        this.lstProduct = lstProduct;
    }

    public ProductAdapter(SearchActivity searchActivity, List<Product> productList) {
    }

    // Gắn layout item vào Adapter
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Nạp layout cho View biểu diễn phần tử product
        View productView = inflater.inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(productView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product item = lstProduct.get(position);

        // Kiểm tra xem có vào hàm này không
        System.out.println("ProductAdapter - Binding ViewHolder for position: " + position);

        // Gán dữ liệu vào View
        holder.imAvatar.setImageBitmap(Utils.convertToBitmapFromAssets(context, item.getAvatar()));
        holder.tvName.setText(item.getName());
        holder.tvDescription.setText(item.getDescription());

        // Kiểm tra ID của sản phẩm khi click
        holder.itemView.setOnClickListener(v -> {
            System.out.println("ProductAdapter - Clicked Product ID: " + item.getId());
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("product_id", item.getId()); // Truyền ID sản phẩm
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return lstProduct.size();
    }

    public void filter(String string) {
    }

    public Object getItem(int position) {
        return lstProduct.get(position);
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
