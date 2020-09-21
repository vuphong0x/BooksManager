package com.example.booksmanager.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.booksmanager.Database.Database;
import com.example.booksmanager.Model.HoaDon;

public class HoaDonDAO {
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;
    public static final String TABLE_NAME = "HoaDon";
    public static final String TAG = "TAG_HoaDon";
    public static final String SQL_HOA_DON = "CREATE TABLE " + TABLE_NAME + "(" +
            "maHoaDon TEXT PRIMARY KEY, " +
            "ngayMua TEXT" +
            ");";

    public HoaDonDAO(Context context) {
        dbHelper = new Database(context);
        db = dbHelper.getWritableDatabase();
    }

    public int insertHoaDon(HoaDon hoaDon) {
        ContentValues values = new ContentValues();
        values.put("maHoaDon", hoaDon.getMaHoaDon());
        values.put("ngayMua", hoaDon.getNgayMua());

        try {
            if (db.insert(TABLE_NAME, null, values) < 0) {
                return -1;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return 1;
    }
}
