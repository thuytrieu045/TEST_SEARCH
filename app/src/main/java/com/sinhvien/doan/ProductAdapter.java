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
    ProductCallback productCallback;

    public ProductAdapter( List<Product> lstProduct) {

        this.lstProduct = lstProduct; // Copy danh sách ban đầu
        this.lstProductFull = new ArrayList<>(lstProduct); // Lưu danh sách đầy đủ để phục hồi khi xóa bộ lọc

    }

    public ProductAdapter(List<Product> productList, SearchActivity searchActivity) {
    }

    //Hàm khởi tạo tương tác với item
    public void setCallback(ProductCallback callback) {
        this.productCallback = callback;
    }
//Day là hàm gắn productitem vao adapter
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
       //nap layout cho item bieu dien phan tu product
        View productView = inflater.inflate(R.layout.item_product, parent, false);
        ProductViewHolder viewHolder = new ProductViewHolder(productView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product item = lstProduct.get(position);
        holder.tvName.setText(item.getName());
        holder.tvDescription.setText(item.getDescription());
        holder.imAvatar.setImageBitmap(Utils.convertToBitmapFromAssets(context, item.getAvatar()));

        // Khi nhấn vào ảnh, mở ProductDetailActivity và truyền product_id
        holder.itemView.setOnClickListener(view -> productCallback.onItemClick(item.getId()));
        holder.ivDelete.setOnClickListener(view ->
                productCallback.onItemDeleteClicked(item, position));
        holder.ivEdit.setOnClickListener(view ->
                productCallback.onItemEditClicked(item, position));
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
                if (product.getName().toLowerCase().contains(lowerCaseQuery)) {
                    lstProduct.add(product);
                }
            }
        }
        notifyDataSetChanged(); // Cập nhật RecyclerView sau khi lọc
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imAvatar;
        TextView tvName, tvDescription;
        ImageView ivEdit;
        ImageView ivDelete;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imAvatar = itemView.findViewById(R.id.ivAvatar);
            tvName = itemView.findViewById(R.id.tvRecipeName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }
    public interface ProductCallback {
        void onItemClick(int id);
        void onItemDeleteClicked(Product product, int position);
        void onItemEditClicked(Product product, int position);
    }

}
