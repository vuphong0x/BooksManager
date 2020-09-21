package com.example.booksmanager.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.booksmanager.DAO.HoaDonDAO;
import com.example.booksmanager.DAO.NguoiDungDAO;
import com.example.booksmanager.DAO.SachDAO;
import com.example.booksmanager.DAO.TheLoaiDAO;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context) {
        super(context, "Database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NguoiDungDAO.SQL_NGUOI_DUNG);
        db.execSQL(HoaDonDAO.SQL_HOA_DON);
        db.execSQL(SachDAO.SQL_SACH);
        db.execSQL(TheLoaiDAO.SQL_THE_LOAI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NguoiDungDAO.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + HoaDonDAO.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SachDAO.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TheLoaiDAO.TABLE_NAME);
    }
}
