package com.example.booksmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.booksmanager.DAO.NguoiDungDAO;

public class NguoiDungDetailActivity extends AppCompatActivity {
    EditText edtFullName, edtPhone;
    NguoiDungDAO nguoiDungDAO;
    String userName, fullName, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung_detail);

        setTitle("Chi Tiết Người Dùng");
        edtFullName = findViewById(R.id.edtUserName);
        edtPhone = findViewById(R.id.edtPhone);
        nguoiDungDAO = new NguoiDungDAO(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        userName = bundle.getString("UserName");
        phone = bundle.getString("Phone");
        fullName = bundle.getString("Name");

        edtFullName.setText(fullName);
        edtPhone.setText(phone);
    }

    public void updateUser(View view) {
        Log.e("updateUser", "updateUser");
        if (nguoiDungDAO.changeInfoUser(userName, edtFullName.getText().toString(), edtPhone.getText().toString()) > 0) {
            Log.e("updateUser", "updateUser-01");
            Toast.makeText(this, "Lưu thành công", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void huy(View view) {
        finish();
    }
}