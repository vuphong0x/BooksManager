package com.example.booksmanager.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.booksmanager.Database.Database;
import com.example.booksmanager.Model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;

    public static final String TABLE_NAME = "Sach";
    public static final String TAG = "TAG_Sach";
    public static final String SQL_SACH = "CREATE TABLE " + TABLE_NAME + "(" +
            "maSach TEXT PRIMARY KEY, " +
            "maTheLoai TEXT, " +
            "tenSach TEXT, " +
            "tacGia TEXT, " +
            "NXB TEXT, " +
            "giaBia DOUBLE, " +
            "soLuong NUMBER" +
            ");";

    public SachDAO(Context context) {
        dbHelper = new Database(context);
        db = dbHelper.getWritableDatabase();
    }

    // Insert
    public int insertBook(Sach sach) {
        ContentValues values = new ContentValues();
        values.put("maSach", sach.getMaSach());
        values.put("maTheLoai", sach.getMaTheLoai());
        values.put("tenSach", sach.getTenSach());
        values.put("tacGia", sach.getTacGia());
        values.put("NXB", sach.getNXB());
        values.put("giaBia", sach.getGiaBia());
        values.put("soLuong", sach.getSoLuong());
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
    public List<Sach> getAllBook() {
        List<Sach> sachList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Sach sach = new Sach();
            sach.setMaSach(cursor.getString(cursor.getColumnIndex("maSach")));
            sach.setMaTheLoai(cursor.getString(cursor.getColumnIndex("maTheLoai")));
            sach.setTenSach(cursor.getString(cursor.getColumnIndex("tenSach")));
            sach.setTacGia(cursor.getString(cursor.getColumnIndex("tacGia")));
            sach.setNXB(cursor.getString(cursor.getColumnIndex("NXB")));
            sach.setGiaBia(cursor.getDouble(cursor.getColumnIndex("giaBia")));
            sach.setSoLuong(cursor.getInt(cursor.getColumnIndex("soLuong")));
            sachList.add(sach);
            cursor.moveToNext();
        }
        cursor.close();
        return sachList;
    }

    public Sach getSachByID(String maSach) {
        Sach sach = null;
        String selection = "maSach=?";
        String[] selectionArgs = {maSach};
        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            sach = new Sach();
            sach.setMaSach(cursor.getString(0));
            sach.setMaTheLoai(cursor.getString(1));
            sach.setTenSach(cursor.getString(2));
            sach.setTacGia(cursor.getString(3));
            sach.setNXB(cursor.getString(4));
            sach.setGiaBia(cursor.getDouble(5));
            sach.setSoLuong(cursor.getInt(6));
            break;
        }
        cursor.close();
        return sach;
    }

    // 10 sách bán chạy nhất
    public List<Sach> get10BestSellBook(String month) {
        List<Sach> sachList = new ArrayList<>();
        if (Integer.parseInt(month) < 10) {
            month = "0" + month;
        }
        String SQL = "SELECT maSach, " +
                "SUM(soLuong) as soLuong FROM HoaDonChiTiet INNER JOIN HoaDon " +
                "ON HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon WHERE strftime('%m', HoaDon.ngayMua) = '" + month + "' " +
                "GROUP BY maSach ORDER BY soLuong DESC LIMIT 10";
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Sach sach = new Sach();
            sach.setMaSach(cursor.getString(0));
//            sach.setMaTheLoai(cursor.getString(1));
//            sach.setTenSach(cursor.getString(2));
//            sach.setTacGia(cursor.getString(3));
//            sach.setNXB(cursor.getString(4));
//            sach.setGiaBia(cursor.getDouble(2));
            sach.setSoLuong(cursor.getInt(1));
            sachList.add(sach);
            cursor.moveToNext();
        }
        cursor.close();
        return sachList;
    }

    // Update
    public long updateBook(Sach sach) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maSach", sach.getMaSach());
        contentValues.put("maTheLoai", sach.getMaTheLoai());
        contentValues.put("tenSach", sach.getTenSach());
        contentValues.put("tacGia", sach.getTacGia());
        contentValues.put("NXB", sach.getNXB());
        contentValues.put("giaBia", sach.getGiaBia());
        contentValues.put("soLuong", sach.getSoLuong());
        return db.update(TABLE_NAME, contentValues, "userName=?", new String[]{sach.getMaSach()});
    }

    // Delete
    public int deleteBook(String maSach) {
        int result = db.delete(TABLE_NAME, "maSach=?", new String[]{maSach});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    // Check
    public boolean checkPrimaryKey(String strPrimaryKey) {
        String[] colums = {"maSach"};
        String selection = "maSach=?";
        String[] selectionArgs = {strPrimaryKey};
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_NAME, colums, selection, selectionArgs, null, null, null);
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

    public Sach checkBook(String strPrimaryKey) {
        Sach sach = new Sach();
        String[] colums = {"maSach"};
        String selection = "maSach=?";
        String[] selectionArgs = {strPrimaryKey};
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_NAME, colums, selection, selectionArgs, null, null, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                sach.setMaSach(cursor.getString(cursor.getColumnIndex("maSach")));
                sach.setMaTheLoai(cursor.getString(cursor.getColumnIndex("maTheLoai")));
                sach.setTenSach(cursor.getString(cursor.getColumnIndex("tenSach")));
                sach.setTacGia(cursor.getString(cursor.getColumnIndex("tacGia")));
                sach.setNXB(cursor.getString(cursor.getColumnIndex("NXB")));
                sach.setGiaBia(cursor.getDouble(cursor.getColumnIndex("giaBia")));
                sach.setSoLuong(cursor.getInt(cursor.getColumnIndex("soLuong")));
                break;
            }
            cursor.close();
            return sach;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
