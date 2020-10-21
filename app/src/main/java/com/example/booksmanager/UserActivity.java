package com.example.booksmanager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booksmanager.DAO.NguoiDungDAO;
import com.example.booksmanager.Model.NguoiDung;

public class UserActivity extends AppCompatActivity {
    Button btnThemNguoiDung;
    NguoiDungDAO nguoiDungDAO;
    EditText edtUserName, edtPass, edtRePass, edtPhone, edtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        setTitle("Thêm Người Dùng");
        btnThemNguoiDung = findViewById(R.id.btnAddUser);
        edtUserName = findViewById(R.id.edtUserName);
        edtPass = findViewById(R.id.edtPassword);
        edtPhone = findViewById(R.id.edtPhone);
        edtName = findViewById(R.id.edtFullName);
        edtRePass = findViewById(R.id.edtRePassword);
    }

    public void addUser(View view) {
        nguoiDungDAO = new NguoiDungDAO(this);
        NguoiDung user = new NguoiDung(edtUserName.getText().toString(), edtPass.getText().toString(),
                edtPhone.getText().toString(), edtName.getText().toString());
        try {
            if (validateForm() > 0) {
                if (nguoiDungDAO.insertNguoiDung(user) > 0) {
                    Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
    }

    public int validateForm() {
        int check = 1;
        if (edtUserName.getText().length() == 0 || edtName.getText().length() == 0 ||
                edtPhone.getText().length() == 0 || edtPass.getText().length() == 0
                || edtRePass.getText().length() == 0) {
            Toast.makeText(this, "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String pass = edtPass.getText().toString();
            String rePass = edtRePass.getText().toString();
            if (!pass.equals(rePass)) {
                Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }

    public void showUser(View view) {
        finish();
    }
}