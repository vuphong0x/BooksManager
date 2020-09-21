package com.example.booksmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.booksmanager.DAO.NguoiDungDAO;
import com.example.booksmanager.Model.NguoiDung;

public class UserActivity extends AppCompatActivity {
    Button btnThemNguoiDung;
    NguoiDungDAO nguoiDungDAO;
    EditText edtUser, edtPass, edtRepass, edtPhone, edtFullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        setTitle("Thêm Người Dùng");
        btnThemNguoiDung = findViewById(R.id.btnAddUser);
        edtUser = findViewById(R.id.edUserName);
        edtPass = findViewById(R.id.edPassword);
        edtRepass = findViewById(R.id.edRePassword);
        edtPhone = findViewById(R.id.edPhone);
        edtFullname = findViewById(R.id.edFullName);
    }

    public void addUser(View view) {
        nguoiDungDAO = new NguoiDungDAO(UserActivity.this);
        NguoiDung nguoiDung = new NguoiDung(
                edtUser.getText().toString(),
                edtPass.getText().toString(),
                edtPhone.getText().toString(),
                edtFullname.getText().toString()
        );
        try {
            if (nguoiDungDAO.insertNguoiDung(nguoiDung) > 0) {
                Toast.makeText(getApplicationContext(), "User added successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "User add failed", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
        }
    }

    public void showUsersList(View view) {
    }

    public void cancel(View view) {
        finish();
    }
}