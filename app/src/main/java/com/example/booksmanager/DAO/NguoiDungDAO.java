package com.example.booksmanager.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
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
            "userName TEXT PRIMARY KEY," +
            "password TEXT," +
            "phone TEXT," +
            "name TEXT" +
            ");";

    public NguoiDungDAO(Context context) {
        dbHelper = new Database(context);
        db = dbHelper.getWritableDatabase();
    }

    // Insert
    public int insertNguoiDung(NguoiDung nguoiDung) {
        ContentValues values = new ContentValues();
        values.put("name", nguoiDung.getName());
        values.put("phone", nguoiDung.getNumberPhone());
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

    // getAll
    public List<NguoiDung> getAllUser() {
        List<NguoiDung> nguoiDungList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            NguoiDung nguoiDung = new NguoiDung();
            nguoiDung.setName(cursor.getString(cursor.getColumnIndex("name")));
            nguoiDung.setNumberPhone(cursor.getString(cursor.getColumnIndex("phone")));
            nguoiDung.setUserName(cursor.getString(cursor.getColumnIndex("userName")));
            nguoiDung.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            nguoiDungList.add(nguoiDung);
            cursor.moveToNext();
        }
        cursor.close();
        return nguoiDungList;
    }

    // Update
    public long updateUser(NguoiDung nguoiDung) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", nguoiDung.getName());
        contentValues.put("phone", nguoiDung.getNumberPhone());
        contentValues.put("userName", nguoiDung.getUserName());
        contentValues.put("password", nguoiDung.getPassword());
        return db.update(TABLE_NAME, contentValues, "userName=?", new String[]{nguoiDung.getUserName()});
    }

    public int changeInfoUser(String userName, String name, String phone) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        int result = db.update(TABLE_NAME, contentValues, "userName=?", new String[]{userName});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public int changePasswordUser(NguoiDung nguoiDung) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("userName", nguoiDung.getUserName());
        contentValues.put("password", nguoiDung.getPassword());
        int result = db.update(TABLE_NAME, contentValues, "userName=?", new String[]{nguoiDung.getUserName()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    // Delete
    public void deleteUser(String userName) {
        db.delete(TABLE_NAME, "userName = ?", new String[]{userName});
    }

    // Check Login
    public int checkLogin(String username, String password) {
        int result = db.delete(TABLE_NAME, "userName=? AND password=?", new String[]{username, password});
        if (result == 0)
            return -1;
        return 1;
    }

}
