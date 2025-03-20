package com.sinhvien.doan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Tên database và phiên bản
    private static final String TEN_DATABASE = "BakingRecipeApp.db";
    private static final int DATABASE_VERSION = 1;

    // Bảng Users
    public static final String BANG_USERS = "users";
    public static final String COT_USER_ID = "user_id";
    public static final String COT_FIREBASE_UID = "firebase_uid"; // Firebase UID

    // Bảng Recipes
    public static final String BANG_RECIPES = "recipes";
    public static final String COT_RECIPE_ID = "recipe_id";
    public static final String COT_TEN_RECIPE = "recipe_name";
    public static final String COT_INGREDIENTS = "ingredients";
    public static final String COT_STEPS = "steps";
    public static final String COT_IMG_URL = "img_src";
    public static final String COT_CATEGORY = "cate";
    public static final String COT_TIME = "time";
    public static final String COT_DOKHO = "difficulty";

    // Tạo bảng Users
    private static final String CREATE_BANG_USERS = "CREATE TABLE " + BANG_USERS + " (" +
            COT_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COT_FIREBASE_UID + " TEXT UNIQUE NOT NULL)";

    // Tạo bảng Recipes (Thêm khóa ngoại user_id)
    private static final String CREATE_BANG_RECIPES = "CREATE TABLE " + BANG_RECIPES + " (" +
            COT_RECIPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            COT_TEN_RECIPE + " TEXT NOT NULL, " +
            COT_INGREDIENTS + " TEXT NOT NULL, " +
            COT_STEPS + " TEXT NOT NULL, " +
            COT_USER_ID + " INTEGER NOT NULL, " +
            COT_IMG_URL + " TEXT, " +
            COT_CATEGORY + " INTEGER NOT NULL, " +
            COT_TIME + " INTEGER NOT NULL, " +
            COT_DOKHO + " TEXT NOT NULL, " +
            "FOREIGN KEY(" + COT_USER_ID + ") REFERENCES " + BANG_USERS + "( " + COT_USER_ID + "))";

    public DatabaseHelper(Context context) {
        super(context, TEN_DATABASE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BANG_USERS);
        db.execSQL(CREATE_BANG_RECIPES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Trích hoặc thêm user_id mỗi khi đăng nhập
    public int getUserId(String firebaseUid) {
        SQLiteDatabase db = this.getWritableDatabase();
        int userId = -1; // Mặc định là -1 nếu không tìm thấy

        // Kiểm tra nếu user đã tồn tại
        Cursor cursor = db.rawQuery("SELECT user_id FROM users WHERE firebase_uid = ?", new String[]{firebaseUid});
        if (cursor.moveToFirst()) {
            userId = cursor.getInt(0);
        } else {
            // Thêm user mới vào và lấy user_id
            ContentValues values = new ContentValues();
            values.put("firebase_uid", firebaseUid);
            long newUserId = db.insert("users", null, values);
            if (newUserId != -1) {
                userId = (int) newUserId;
            }
        }
        cursor.close();
        return userId;
    }
}
