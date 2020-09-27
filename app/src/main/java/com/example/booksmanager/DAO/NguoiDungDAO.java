package com.example.booksmanager.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.booksmanager.Database.Database;
import com.example.booksmanager.Model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungDAO {
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;
    public static final String TABLE_NAME = "NguoiDung";
    public static final String TAG = "TAG_NguoiDung";
    public static final String SQL_NGUOI_DUNG = "CREATE TABLE " + TABLE_NAME + "(" +
            "name TEXT," +
            "numberPhone text," +
            "userName text PRIMARY KEY," +
            "password text" +
            ");";

    public NguoiDungDAO(Context context) {
        dbHelper = new Database(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<NguoiDung> getAllUser() {
        List<NguoiDung> nguoiDungList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            NguoiDung nguoiDung = new NguoiDung();
            nguoiDung.setName(cursor.getString(cursor.getColumnIndex("name")));
            nguoiDung.setNumberPhone(cursor.getString(cursor.getColumnIndex("numberPhone")));
            nguoiDung.setUserName(cursor.getString(cursor.getColumnIndex("userName")));
            nguoiDung.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            nguoiDungList.add(nguoiDung);
            cursor.moveToNext();
        }
        cursor.close();
        return nguoiDungList;
    }

    public int insertNguoiDung(NguoiDung nguoiDung) {
        ContentValues values = new ContentValues();
        values.put("name", nguoiDung.getName());
        values.put("numberPhone", nguoiDung.getNumberPhone());
        values.put("userName", nguoiDung.getUserName());
        values.put("password", nguoiDung.getPassword());

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
