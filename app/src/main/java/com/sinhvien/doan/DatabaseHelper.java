package com.sinhvien.doan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Tên database và phiên bản
    private static final String DATABASE_NAME = "BakingRecipeApp.db";
    private static final int DATABASE_VERSION = 1;

    // Tên bảng và cột
    // Bảng Users
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_ROLE = "role"; // "user" or "admin"

    // Bảng Recipes
    private static final String TABLE_RECIPES = "recipes";
    private static final String COLUMN_RECIPE_ID = "recipe_id";
    private static final String COLUMN_RECIPE_NAME = "recipe_name";
    private static final String COLUMN_INGREDIENTS = "ingredients";
    private static final String COLUMN_USER_ID = "user_id"; // Khóa ngoại (User tạo công thức)

    // Tạo bảng Users
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USERNAME + " TEXT UNIQUE, " +
            COLUMN_PASSWORD + " TEXT, " +
            COLUMN_ROLE + " TEXT)";

    // Tạo bảng Recipes
    private static final String CREATE_TABLE_RECIPES = "CREATE TABLE " + TABLE_RECIPES + " (" +
            COLUMN_RECIPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_RECIPE_NAME + " TEXT, " +
            COLUMN_INGREDIENTS + " TEXT, " +
            COLUMN_USER_ID + " INTEGER, " +
            "FOREIGN KEY (" + COLUMN_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_ID + "))";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_RECIPES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // Hàm đăng ký tài khoản
    public boolean registerUser(String username, String password, String role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_ROLE, role);

        long result = db.insert(TABLE_USERS, null, values);
        return result != -1; // Nếu insert thành công, trả về true
    }

    // Hàm kiểm tra đăng nhập
    public boolean checkLogin(String username, String password, String role) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ? AND " + COLUMN_ROLE + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password, role});
        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        return isValid;
    }
}
