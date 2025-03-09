package com.sinhvien.doan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseAdapter;
import java.util.List;

public class CartAdapter extends BaseAdapter {
    private Context context;
    private List<Product> cartList;
    private Runnable updateTotalCallback;

    public CartAdapter(Context context, List<Product> cartList, Runnable updateTotalCallback) {
        this.context = context;
        this.cartList = cartList;
        this.updateTotalCallback = updateTotalCallback;
    }

    @Override
    public int getCount() {
        return cartList.size();
    }

    @Override
    public Object getItem(int position) {
        return cartList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        }

        ImageView imgProduct = convertView.findViewById(R.id.imgProduct);
        TextView tvProductName = convertView.findViewById(R.id.tvProductName);
        TextView tvProductPrice = convertView.findViewById(R.id.tvProductPrice);
        Button btnRemove = convertView.findViewById(R.id.btnRemove);

        Product product = cartList.get(position);
        imgProduct.setImageResource(product.getImageResource());
        tvProductName.setText(product.getName());
        tvProductPrice.setText(String.format("$%.2f", product.getPrice()));

        btnRemove.setOnClickListener(v -> {
            cartList.remove(position);
            notifyDataSetChanged();
            updateTotalCallback.run();
        });

        return convertView;
    }
}
