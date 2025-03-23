package com.sinhvien.doan;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class Utils {

    // database
    public static final String TEN_DATABASE = "BakingRecipeApp.db";
    public static final String TABLE_PRODUCT = "products";
    public static final String COLUMN_PRODUCT_ID = "id";
    public static final String COLUMN_PRODUCT_NAME = "name";
    public static final String COLUMN_PRODUCT_DESCRIPTION = "description";
    public static final String COLUMN_PRODUCT_AVATAR = "avatar";
    public static Bitmap convertToBitmapFromAssets(Context context, String nameImage)
    {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open("images/" +
                    nameImage);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*public static Bitmap convertToBitmapFromDrawable(Context context, String nameImage) {
        int resID = context.getResources().getIdentifier(nameImage, "drawable", context.getPackageName());

        if (resID != 0) { // Kiểm tra xem ảnh có tồn tại không
            return BitmapFactory.decodeResource(context.getResources(), resID);
        }

        return null;
    }*/
}
