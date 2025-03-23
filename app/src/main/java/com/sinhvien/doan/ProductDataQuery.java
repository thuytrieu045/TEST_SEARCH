package com.sinhvien.doan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ProductDataQuery {
    private static int imageResource;

    public static long Insert(Context context, Product product) {
        DatabaseHelper productDBHelper = new DatabaseHelper(context);
        SQLiteDatabase database = productDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utils.COLUMN_PRODUCT_NAME, product.getName());
        values.put(Utils.COLUMN_PRODUCT_DESCRIPTION, product.getDescription());
        values.put(Utils.COLUMN_PRODUCT_AVATAR, product.getAvatar());
        long result = database.insert(Utils.TABLE_PRODUCT, null, values);
        return result;
    }
    //Lấy danh sách
    public static ArrayList<Product> getAll(Context context) {
        ArrayList<Product> lstProduct = new ArrayList<>();
        DatabaseHelper productDBHelper = new DatabaseHelper(context);
        SQLiteDatabase db = productDBHelper.getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM " + Utils.TABLE_PRODUCT, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            int id = cs.getInt(0);
            String name = cs.getString(1);
            String description = cs.getString(2);
            String avatar = cs.getString(3);
            lstProduct.add(new Product(id, name, description, avatar, imageResource));
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return lstProduct;
    }
    //Xóa item
    public static boolean delete (Context context, int id)
    {
        DatabaseHelper productDBHelper = new DatabaseHelper(context);
        SQLiteDatabase db = productDBHelper.getWritableDatabase();
        int rs = db.delete(Utils.TABLE_PRODUCT, Utils.COLUMN_PRODUCT_ID+"=?", new
                String[]{String.valueOf(id)});
        return rs > 0;
    }
    //Cập nhật
    public static int update (Context context, Product product)
    {
        DatabaseHelper productDBHelper = new DatabaseHelper(context);
        SQLiteDatabase db = productDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utils.COLUMN_PRODUCT_NAME, product.getName());
        values.put(Utils.COLUMN_PRODUCT_DESCRIPTION, product.getDescription());
        values.put(Utils.COLUMN_PRODUCT_AVATAR, product.getAvatar());
        int rs = db.update(Utils.TABLE_PRODUCT, values,
                Utils.COLUMN_PRODUCT_ID+"=?", new String[]{String.valueOf(product.getId())});
        return rs;
    }
}
