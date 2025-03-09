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
    ArrayList<Product> lstProduct;
    Context context;
    public ProductAdapter(ArrayList<Product> lstProduct)
    {
        this.lstProduct = lstProduct;
    }

    public ProductAdapter(SearchActivity searchActivity, List<Product> productList) {
    }

    //Đây là hàm giúp gắn layoutitem vào Adapter
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
//Nạp layout cho View biểu diễn phần tử product
        View ProductView = inflater.inflate(R.layout.item_product, parent, false);
//
        ProductViewHolder viewHolder = new ProductViewHolder(ProductView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            //Lấy từng item của dữ liệu
        Product item = lstProduct.get(position);

        //Gán vào item của View
        holder.imAvatar.setImageBitmap(Utils.convertToBitmapFromAssets(context,item.getAvatar()));
        holder.tvName.setText(item.getName());
        holder.tvDescription.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return lstProduct.size();
    }

    public void filter(String string) {
    }

    public Object getItem(int position) {
        return null;
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
