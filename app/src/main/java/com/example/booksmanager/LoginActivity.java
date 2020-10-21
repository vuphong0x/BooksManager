package com.example.booksmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booksmanager.DAO.NguoiDungDAO;

public class LoginActivity extends AppCompatActivity {
    private EditText edtUserName, edtPassword;
    private Button btnLogin, btnCancel;
    private String strUser, strPassword;
    private CheckBox cbRemember;
    NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Đăng Nhập");
        nguoiDungDAO = new NguoiDungDAO(this);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
        cbRemember = findViewById(R.id.cbRemember);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin(v);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void rememberUser(String userName, String password, boolean status) {
        SharedPreferences preferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (!status) {
            // Xoa tinh trang luu truoc do
            editor.clear();
        } else {
            // luu du lieu
            editor.putString("USERNAME", userName);
            editor.putString("PASSWORD", password);
            editor.putBoolean("REMEMBER", status);
        }
        // luu lai toan bo
        editor.commit();
    }

    public void checkLogin(View view) {
        strUser = edtUserName.getText().toString();
        strPassword = edtPassword.getText().toString();
        if (strUser.isEmpty() || strPassword.isEmpty()) {
            Toast.makeText(this, "Tên đăng nhập và mật khẩu không được bỏ trống", Toast.LENGTH_SHORT).show();
        } else {
            if (nguoiDungDAO.checkLogin(strUser, strPassword) > 0) {
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else if (strUser.equalsIgnoreCase("admin") && strPassword.equalsIgnoreCase("admin")) {
                rememberUser(strUser, strPassword, cbRemember.isChecked());
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();

            }
        }
    }
}