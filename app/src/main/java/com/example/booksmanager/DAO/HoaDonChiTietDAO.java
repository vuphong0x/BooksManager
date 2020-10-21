package com.example.booksmanager.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.booksmanager.Database.Database;
import com.example.booksmanager.Model.HoaDon;
import com.example.booksmanager.Model.HoaDonChiTiet;
import com.example.booksmanager.Model.NguoiDung;
import com.example.booksmanager.Model.Sach;

import java.lang.annotation.Target;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonChiTietDAO {
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;
    public static final String TABLE_NAME = "HoaDonChiTiet";
    public static final String TAG = "TAG_HoaDonChiTiet";
    public static final String SQL_HOA_DON_CHI_TIET = "CREATE TABLE " + TABLE_NAME + "(" +
            "maHoaDonChiTiet INTEGER PRIMARY KEY AUTOINCREMENT," +
            "maHoaDon TEXT," +
            "maSach TEXT," +
            "soLuong INTEGER" +
            ");";
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public HoaDonChiTietDAO(Context context) {
        dbHelper = new Database(context);
        db = dbHelper.getWritableDatabase();
    }

    // Insert
    public int insertHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet) {
        ContentValues values = new ContentValues();
        values.put("maHoaDon", hoaDonChiTiet.getHoaDon().getMaHoaDon());
        values.put("maSach", hoaDonChiTiet.getSach().getMaSach());
        values.put("soLuong", hoaDonChiTiet.getSoLuongMua());

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
    public List<HoaDonChiTiet> getAllHoaDonChiTiet() {
        List<HoaDonChiTiet> hoaDonChiTietList = new ArrayList<>();
        String SQL = "SELECT maHoaDonChiTiet, HoaDon.maHoaDon, HoaDon.ngayMua, " +
                "Sach.maSach, Sach.maTheLoai, Sach.tenSach, Sach.tacGia, Sach.NXB, Sach.giaBia" +
                "Sach.soLuong, HoaDonChiTiet.soLuong FROM HoaDonChiTiet INNER JOIN HoaDon " +
                "on HoaDonChiTiet.maHoaDon = HoaDon.maHoaDon INNER JOIN Sach on Sach.maSach = HoaDonChiTiet.maSach";
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();
        try {
            while (cursor.isAfterLast() == false) {
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.setMaHoaDonChiTiet(cursor.getInt(cursor.getColumnIndex("maHoaDonChiTiet")));
                hoaDonChiTiet.setHoaDon(new HoaDon(cursor.getString(1), sdf.parse(cursor.getString(2))));
                hoaDonChiTiet.setSach(new Sach(cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getInt(8), cursor.getInt(9)));
                hoaDonChiTiet.setSoLuongMua(cursor.getInt(cursor.getColumnIndex("soLuong")));
                hoaDonChiTietList.add(hoaDonChiTiet);
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }

        return hoaDonChiTietList;
    }

    public List<HoaDonChiTiet> getHoaDonChiTietByID(String maHoaDon) {
        List<HoaDonChiTiet> hoaDonChiTietList = new ArrayList<>();
        String SQL = "Select maHoaDonChiTiet, HoaDon.maHoaDon, HoaDon.ngayMua, " +
                "Sach.maSach, Sach.maTheLoai, Sach.tenSach, Sach.tacGia, Sach.NXB, Sach.giaBia, " +
                "Sach.soLuong, HoaDonChiTiet.soLuong FROM HoaDonChiTiet INNER JOIN HoaDon " +
                "on HoaDonChiTiet.maHoaDon = HoaDon.maHoaDon INNER JOIN Sach on Sach.maSach = HoaDonChiTiet.maSach " +
                "WHERE HoaDonChiTiet.maHoaDon = '" + maHoaDon + "'";
        Cursor c = db.rawQuery(SQL, null);
        c.moveToFirst();
        try {
            while (!c.isAfterLast()) {
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.setMaHoaDonChiTiet(c.getInt(c.getColumnIndex("maHoaDonChiTiet")));
                hoaDonChiTiet.setHoaDon(new HoaDon(c.getString(1), sdf.parse(c.getString(2))));
                hoaDonChiTiet.setSach(new Sach(c.getString(3), c.getString(4), c.getString(5), c.getString(6), c.getString(7), c.getInt(8), c.getInt(9)));
                hoaDonChiTietList.add(hoaDonChiTiet);
                c.moveToNext();
            }
            c.close();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return hoaDonChiTietList;
    }

    // Update
    public long updateHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHoaDonChiTiet", hoaDonChiTiet.getMaHoaDonChiTiet());
        contentValues.put("maHoaDon", hoaDonChiTiet.getHoaDon().getMaHoaDon());
        contentValues.put("maSach", hoaDonChiTiet.getSach().getMaSach());
        contentValues.put("soLuong", hoaDonChiTiet.getSoLuongMua());
        return db.update(TABLE_NAME, contentValues, "maHoaDonChiTiet=?", new String[]{String.valueOf(hoaDonChiTiet.getMaHoaDonChiTiet())});
    }


    // Delete
    public int deleteHoaDonChiTiet(String maHoaDonChiTiet) {
        int result = db.delete(TABLE_NAME, "maHoaDonChiTiet=?", new String[]{maHoaDonChiTiet});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    // Check
    public boolean checkHoaDon(String maHoaDon) {
        // SELECT
        String[] columns = {"maHoaDon"};
        // WHERE clause
        String selection = "maHoaDon=?";
        // WHERE clause arguments
        String[] selectionArgs = {maHoaDon};
        Cursor c = null;
        try {
            c = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
            c.moveToFirst();
            int i = c.getCount();
            c.close();
            if (i <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public double getDoanhThuTheoNgay() {
        double doanhThu = 0;
        String SQL = "SELECT SUM(tongTien) FROM (SELECT SUM(Sach.giaBia * HoaDonChiTiet.soLuong) as 'tongTien' " +
        "FROM HoaDon INNER JOIN HoaDonChiTiet on HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon " +
        "INNER JOIN Sach on HoaDonChiTiet.maSach = Sach.maSach WHERE HoaDon.ngayMua = date('now') " +
        "GROUP BY HoaDonChiTiet.maSach)tmp";
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            doanhThu = cursor.getDouble(0);
            cursor.moveToNext();
        }
        cursor.close();
        return doanhThu;
    }

    public double getDoanhThuTheoThang() {
        double doanhThu = 0;
        String SQL = "SELECT SUM(tongTien) FROM (SELECT SUM(Sach.giaBia * HoaDonChiTiet.soLuong) as 'tongTien' " +
                "FROM HoaDon INNER JOIN HoaDonChiTiet on HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon " +
                "INNER JOIN Sach on HoaDonChiTiet.maSach = Sach.maSach WHERE strftime('%m', HoaDon.ngayMua) = " +
                "strftime('%m', 'now') GROUP BY HoaDonChiTiet.maSach)tmp";
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            doanhThu = cursor.getDouble(0);
            cursor.moveToNext();
        }
        cursor.close();
        return doanhThu;
    }

    public double getDoanhThuTheoNam() {
        double doanhThu = 0;
        String SQL = "SELECT SUM(tongTien) FROM (SELECT SUM(Sach.giaBia * HoaDonChiTiet.soLuong) as 'tongTien' " +
                "FROM HoaDon INNER JOIN HoaDonChiTiet on HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon " +
                "INNER JOIN Sach on HoaDonChiTiet.maSach = Sach.maSach WHERE strftime('%Y', HoaDon.ngayMua) = " +
                "strftime('%Y', 'now') GROUP BY HoaDonChiTiet.maSach)tmp";
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            doanhThu = cursor.getDouble(0);
            cursor.moveToNext();
        }
        cursor.close();
        return doanhThu;
    }
}
