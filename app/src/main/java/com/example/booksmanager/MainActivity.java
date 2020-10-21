package com.example.booksmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    String userName, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Quản Lý Sách");
        if (checkLoginShap() < 0) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private int checkLoginShap() {
        SharedPreferences preferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        boolean chk = preferences.getBoolean("REMEMBER", false);
        if (chk) {
            userName = preferences.getString("USERNAME", "");
            password = preferences.getString("PASSWORD", "");
            return 1;
        }
        return -1;
    }

    public void toActivityStatistical(View view) {
        intent = new Intent(this, StatisticalActivity.class);
        startActivity(intent);
    }

    public void toActivityCategory(View view) {
        intent = new Intent(this, ListTheLoaiActivity.class);
        startActivity(intent);
    }

    public void toActivityBill(View view) {
        intent = new Intent(this, ListHoaDonActivity.class);
        startActivity(intent);
    }

    public void toActivitySelling(View view) {
        intent = new Intent(this, LuotSachBanChayActivity.class);
        startActivity(intent);
    }

    public void toActivityBooks(View view) {
        intent = new Intent(this, ListBookActivity.class);
        startActivity(intent);
    }

    public void toActivityUser(View view) {
        intent = new Intent(this, ListNguoiDungActivity.class);
        startActivity(intent);
    }
}