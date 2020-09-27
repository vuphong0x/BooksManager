package com.example.booksmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.booksmanager.DAO.NguoiDungDAO;
import com.example.booksmanager.DAO.TheLoaiDAO;
import com.example.booksmanager.Model.NguoiDung;

public class CategoryActivity extends AppCompatActivity {
    Button btnAddCategory;
    TheLoaiDAO theLoaiDAO;
    EditText edtCategoryCode, edtCategoryName, edtLocation, edtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
    }

    public void addCategory(View view) {
        theLoaiDAO = new TheLoaiDAO(CategoryActivity.this);
//        NguoiDung nguoiDung = new NguoiDung(
//                edtUser.getText().toString(),
//                edtPass.getText().toString(),
//                edtPhone.getText().toString(),
//                edtFullname.getText().toString()
//        );
//        try {
//            if (nguoiDungDAO.insertNguoiDung(nguoiDung) > 0) {
//                Toast.makeText(getApplicationContext(), "User added successfully", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(getApplicationContext(), "User add failed", Toast.LENGTH_SHORT).show();
//            }
//        } catch (Exception e) {
//        }
    }
}