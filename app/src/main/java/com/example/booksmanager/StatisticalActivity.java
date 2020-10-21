package com.example.booksmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

import com.example.booksmanager.DAO.HoaDonChiTietDAO;
import com.example.booksmanager.Model.HoaDonChiTiet;

public class StatisticalActivity extends AppCompatActivity {
    TextView tvDate, tvMonth, tvYear;
    HoaDonChiTietDAO hoaDonChiTietDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistical);

        setTitle("Doanh Thu");
        tvDate = findViewById(R.id.tvDate);
        tvMonth = findViewById(R.id.tvMonth);
        tvYear = findViewById(R.id.tvYear);

        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
        tvDate.setText("Hôm nay: " + hoaDonChiTietDAO.getDoanhThuTheoNgay());
        tvMonth.setText("Tháng này: " + hoaDonChiTietDAO.getDoanhThuTheoThang());
        tvYear.setText("Năm nay: " + hoaDonChiTietDAO.getDoanhThuTheoNam());
    }
}