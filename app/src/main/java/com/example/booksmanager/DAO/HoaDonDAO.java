package com.example.booksmanager.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.booksmanager.Database.Database;
import com.example.booksmanager.Model.HoaDon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;
    public static final String TABLE_NAME = "HoaDon";
    public static final String TAG = "TAG_HoaDon";
    public static final String SQL_HOA_DON = "CREATE TABLE " + TABLE_NAME + "(" +
            "maHoaDon TEXT PRIMARY KEY, " +
            "ngayMua DATE" +
            ");";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public HoaDonDAO(Context context) {
        dbHelper = new Database(context);
        db = dbHelper.getWritableDatabase();
    }

    // Insert
    public int insertBill(HoaDon hoaDon) {
        ContentValues values = new ContentValues();
        values.put("maHoaDon", hoaDon.getMaHoaDon());
        values.put("ngayMua", sdf.format(hoaDon.getNgayMua()));

        try {
            if (db.insert(TABLE_NAME, null, values) < 0) {
                return -1;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return 1;
    }

    // getAll
    public List<HoaDon> getAllBill() {
        List<HoaDon> hoaDonList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaHoaDon(cursor.getString(cursor.getColumnIndex("maHoaDon")));
            try {
                hoaDon.setNgayMua(sdf.parse(cursor.getString(cursor.getColumnIndex("ngayMua"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            hoaDonList.add(hoaDon);
            cursor.moveToNext();
        }
        cursor.close();
        return hoaDonList;
    }

    // Update
    public int updateBill(HoaDon hoaDon) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHoaDon", hoaDon.getMaHoaDon());
        contentValues.put("ngayMua", hoaDon.getNgayMua().toString());
        int result = db.update(TABLE_NAME, contentValues, "maHoaDon=?", new String[]{hoaDon.getMaHoaDon()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    // Delete
    public int deleteBill(String maHoaDon) {
        int result = db.delete(TABLE_NAME, "maHoaDon = ?", new String[]{maHoaDon});
        if (result == 0) {
            return -1;
        }
        return 1;
    }
}
