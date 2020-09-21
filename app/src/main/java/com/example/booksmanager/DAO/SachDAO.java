package com.example.booksmanager.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.booksmanager.Database.Database;
import com.example.booksmanager.Model.NguoiDung;
import com.example.booksmanager.Model.Sach;

public class SachDAO {
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;
    public static final String TABLE_NAME = "Sach";
    public static final String TAG = "TAG_Sach";
    public static final String SQL_SACH = "CREATE TABLE " + TABLE_NAME + "(" +
            "maSach TEXT PRIMARY KEY, " +
            "maTheLoai text," +
            "tieuDe text," +
            "tacGia text" +
            "soLuongTonKho text" +
            "nhaXuatBan text" +
            ");";

    public SachDAO(Context context) {
        dbHelper = new Database(context);
        db = dbHelper.getWritableDatabase();
    }

    public int insertSach(Sach sach) {
        ContentValues values = new ContentValues();
        values.put("maSach", sach.getMaSach());
        values.put("maTheLoai", sach.getMaTheLoai());
        values.put("tieuDe", sach.getTieuDe());
        values.put("tacGia", sach.getTacGia());
        values.put("soLuongTonKho", sach.getSoLuongTonKho());
        values.put("nhaXuatBan", sach.getNhaXuatBan());

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
