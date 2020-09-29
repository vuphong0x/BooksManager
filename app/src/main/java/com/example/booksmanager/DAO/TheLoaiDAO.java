package com.example.booksmanager.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.booksmanager.Database.Database;
import com.example.booksmanager.Model.NguoiDung;
import com.example.booksmanager.Model.TheLoaiSach;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TheLoaiDAO {
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;
    public static final String TABLE_NAME = "TheLoaiSach";
    public static final String TAG = "TAG_TheLoaiSach";
    public static final String SQL_THE_LOAI = "CREATE TABLE " + TABLE_NAME + "(" +
            "maTheLoai TEXT PRIMARY KEY, " +
            "tenTheLoai text," +
            "moTa text," +
            "viTri text" +
            ");";

    public TheLoaiDAO(Context context) {
        dbHelper = new Database(context);
        db = dbHelper.getWritableDatabase();
    }

    public int insertTheLoai(TheLoaiSach theLoaiSach) {
        ContentValues values = new ContentValues();
        values.put("maTheLoai", theLoaiSach.getMaTheLoai());
        values.put("tenTheLoai", theLoaiSach.getTenTheLoai());
        values.put("moTa", theLoaiSach.getMoTa());
        values.put("viTri", theLoaiSach.getViTri());

        try {
            if (db.insert(TABLE_NAME, null, values) < 0) {
                return -1;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return 1;
    }

    public List<TheLoaiSach> getAllCategory() {
        List<TheLoaiSach> theLoaiSachList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            TheLoaiSach theLoaiSach = new TheLoaiSach();
            theLoaiSach.setMaTheLoai(cursor.getString(cursor.getColumnIndex("maTheLoai")));
            theLoaiSach.setTenTheLoai(cursor.getString(cursor.getColumnIndex("tenTheLoai")));
            theLoaiSach.setViTri(cursor.getString(cursor.getColumnIndex("viTri")));
            theLoaiSach.setMoTa(cursor.getString(cursor.getColumnIndex("moTa")));
            theLoaiSachList.add(theLoaiSach);
            cursor.moveToNext();
        }
        cursor.close();
        return theLoaiSachList;
    }
}
