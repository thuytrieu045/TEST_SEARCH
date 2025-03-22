package com.sinhvien.doan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private List<Recipe> lstRecipe;
    private List<Recipe> lstRecipeFull;
    private Context context;

    public RecipeAdapter(Context context, List<Recipe> lstRecipe) {
        this.context = context;
        this.lstRecipe = new ArrayList<>(lstRecipe);
        this.lstRecipeFull = new ArrayList<>(lstRecipe);
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View recipeView = inflater.inflate(R.layout.activity_recipe_detail, parent, false);
        return new RecipeViewHolder(recipeView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe item = lstRecipe.get(position);
        holder.tvRecipeName.setText(item.getRecipeName());
        holder.tvIngredients.setText(item.getIngredients());

        // Load image using Glide
        Glide.with(context)
                .load(item.getImgSource()) // Upload ảnh bằng URL
                .placeholder(R.drawable.dessert) // Thay thế không có URL của ảnh
                .error(R.drawable.donut_icon) // Thay thế ảnh lỗi
                .into(holder.imAvatar);

        holder.imAvatar.setOnClickListener(v -> {
            Intent intent = new Intent(context, RecipeDetailActivity.class);
            intent.putExtra("recipe_id", item.getRecipeId());
            context.startActivity(intent);
        });

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, RecipeDetailActivity.class);
            intent.putExtra("recipe_id", item.getRecipeId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return lstRecipe.size();
    }

    // dùng phương thức filter() để tìm tên công thức
    public void filter(String query) {
        lstRecipe.clear();
        if (query.isEmpty()) {
            lstRecipe.addAll(lstRecipeFull);
        } else {
            String lowerCaseQuery = query.toLowerCase();
            for (Recipe recipe : lstRecipeFull) {
                if (recipe.getRecipeName().toLowerCase().contains(lowerCaseQuery)) {
                    lstRecipe.add(recipe);
                }
            }
        }
        notifyDataSetChanged();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {
        ImageView imAvatar;
        TextView tvRecipeName, tvIngredients;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRecipeName = itemView.findViewById(R.id.tvRecipeName);
            tvIngredients = itemView.findViewById(R.id.tvDescription);
            imAvatar = itemView.findViewById(R.id.ivAvatar);
        }
    }
}
