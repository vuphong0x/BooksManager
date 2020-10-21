package com.example.booksmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.booksmanager.DAO.NguoiDungDAO;
import com.example.booksmanager.Model.NguoiDung;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText edtPassword, edtRePassword;
    NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        edtPassword = findViewById(R.id.edtPassword);
        edtRePassword = findViewById(R.id.edtRePassword);
    }

    public int validateForm() {
        int check = 1;
        if (edtPassword.getText().length() == 0 || edtRePassword.getText().length() == 0) {
            Toast.makeText(this, "Không được bỏ trống thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String pass = edtPassword.getText().toString();
            String rePass = edtRePassword.getText().toString();
            if (!pass.equals(rePass)) {
                Toast.makeText(this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }

    public void changePassword(View view) {
        SharedPreferences preferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String userName = preferences.getString("USERNAME", "");
        nguoiDungDAO = new NguoiDungDAO(this);
        NguoiDung user = new NguoiDung(userName, edtPassword.getText().toString(), "", "");
        try {
            if (validateForm() > 0) {
                if (nguoiDungDAO.changePasswordUser(user) > 0) {
                    Toast.makeText(this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Lưu thất bại", Toast.LENGTH_SHORT).show();
                }
            }
            finish();
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
    }

    public void cancel(View view) {
        finish();
    }
}