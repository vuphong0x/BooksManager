package com.example.booksmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.booksmanager.Adapter.CartAdapter;
import com.example.booksmanager.DAO.HoaDonChiTietDAO;
import com.example.booksmanager.Model.HoaDonChiTiet;

import java.util.ArrayList;
import java.util.List;

public class ListHoaDonChiTietByIdActivity extends AppCompatActivity {
    public List<HoaDonChiTiet> dsHDCT = new ArrayList<>();
    ListView lvCart;
    CartAdapter adapter = null;
    HoaDonChiTietDAO hoaDonChiTietDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hoa_don_chi_tiet_by_id);

        setTitle("Hóa Đơn Chi Tiết");
        lvCart = findViewById(R.id.lvHoaDonChiTiet);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            dsHDCT = hoaDonChiTietDAO.getHoaDonChiTietByID(bundle.getString("MaHoaDon"));

        }

        adapter = new CartAdapter(this, dsHDCT);
        lvCart.setAdapter(adapter);
    }
}