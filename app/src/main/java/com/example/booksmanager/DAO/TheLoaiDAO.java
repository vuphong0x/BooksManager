package com.example.booksmanager.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.booksmanager.Database.Database;
import com.example.booksmanager.Model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiDAO {
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;

    public static final String TABLE_NAME = "TheLoai";
    public static final String TAG = "TAG_TheLoai";
    public static final String SQL_THE_LOAI = "CREATE TABLE " + TABLE_NAME + "(" +
            "maTheLoai TEXT PRIMARY KEY, " +
            "tenTheLoai text," +
            "moTa text," +
            "viTri int" +
            ");";

    public TheLoaiDAO(Context context) {
        dbHelper = new Database(context);
        db = dbHelper.getWritableDatabase();
    }

    public int insertCategory(TheLoai theLoai) {
        ContentValues values = new ContentValues();
        values.put("maTheLoai", theLoai.getMaTheLoai());
        values.put("tenTheLoai", theLoai.getTenTheLoai());
        values.put("moTa", theLoai.getMoTa());
        values.put("viTri", theLoai.getViTri());

        if (checkPrimaryKey(theLoai.getMaTheLoai())) {
            int result = db.update(TABLE_NAME, values, "maTheLoai=?", new String[]{theLoai.getMaTheLoai()});
            if (result == 0) {
                return -1;
            }
            return 1;
        } else {
            try {
                if (db.insert(TABLE_NAME, null, values) == -1) {
                    return -1;
                }
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        }
        return 1;
    }

    public List<TheLoai> getAllCategory() {
        List<TheLoai> theLoaiList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            TheLoai theLoai = new TheLoai();
            theLoai.setMaTheLoai(cursor.getString(cursor.getColumnIndex("maTheLoai")));
            theLoai.setTenTheLoai(cursor.getString(cursor.getColumnIndex("tenTheLoai")));
            theLoai.setViTri(cursor.getInt(cursor.getColumnIndex("viTri")));
            theLoai.setMoTa(cursor.getString(cursor.getColumnIndex("moTa")));
            theLoaiList.add(theLoai);
            cursor.moveToNext();
        }
        cursor.close();
        return theLoaiList;
    }

    // Update
    public int updateCategory(TheLoai theLoai) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maTheLoai", theLoai.getMaTheLoai());
        contentValues.put("tenTheLoai", theLoai.getTenTheLoai());
        contentValues.put("moTa", theLoai.getMoTa());
        contentValues.put("viTri", theLoai.getViTri());
        int result = db.update(TABLE_NAME, contentValues, "maTheLoai=?", new String[]{theLoai.getMaTheLoai()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    // Delete
    public int deleteCategory (String maTheLoai) {
        int result = db.delete(TABLE_NAME, "maTheLoai=?", new String[]{maTheLoai});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    // Check
    public boolean checkPrimaryKey(String strPrimaryKey) {
        String[] columns = {"maTheLoai"};
        String selection = "maTheLoai=?";
        String[] selectionArgs = {strPrimaryKey};
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
            cursor.moveToFirst();
            int i = cursor.getCount();
            cursor.close();
            if (i <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
