package com.example.booksmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booksmanager.Adapter.CartAdapter;
import com.example.booksmanager.DAO.HoaDonChiTietDAO;
import com.example.booksmanager.DAO.SachDAO;
import com.example.booksmanager.Model.HoaDon;
import com.example.booksmanager.Model.HoaDonChiTiet;
import com.example.booksmanager.Model.Sach;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonChiTietActivity extends AppCompatActivity {
    EditText edtMaSach, edtMaHoaDon, edtSoLuong;
    TextView tvThanhTien;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    SachDAO sachDAO;
    List<HoaDonChiTiet> hoaDonChiTietList = new ArrayList<>();
    ListView lvCart;
    CartAdapter adapter = null;
    double thanhTien = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);

        setTitle("Chi Tiết Hóa Đơn");
        edtMaSach = findViewById(R.id.edtMaSach);
        edtMaHoaDon = findViewById(R.id.edtMaHoaDon);
        edtSoLuong = findViewById(R.id.edtSoLuong);
        tvThanhTien = findViewById(R.id.tvThanhTien);
        lvCart = findViewById(R.id.lvCart);

        adapter = new CartAdapter(this, hoaDonChiTietList);
        lvCart.setAdapter(adapter);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            edtMaHoaDon.setText(bundle.getString("MAHOADON"));
        }
    }

    public void addHoaDonChiTiet(View view) {
        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
        sachDAO = new SachDAO(this);
        try {
            if (validation() < 0) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                Sach sach = sachDAO.getSachByID(edtMaSach.getText().toString());
                if (sach != null) {
                    int position = checkMaSach(hoaDonChiTietList, edtMaSach.getText().toString());
                    HoaDon hoaDon = new HoaDon(edtMaHoaDon.getText().toString(), new Date());
                    HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(1, hoaDon, sach, Integer.parseInt(edtSoLuong.getText().toString()));
                    if (position >= 0) {
                        int soLuong = hoaDonChiTietList.get(position).getSoLuongMua();
                        hoaDonChiTiet.setSoLuongMua(soLuong + Integer.parseInt(edtSoLuong.getText().toString()));
                    } else  {
                        hoaDonChiTietList.add(hoaDonChiTiet);
                    }
                    adapter.changeDataSet(hoaDonChiTietList);
                } else {
                    Toast.makeText(this, "Mã sách không tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
    }

    public void thanhToanHoaDon(View view) {
        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
        thanhTien = 0;
        try {
            for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTietList) {
                hoaDonChiTietDAO.insertHoaDonChiTiet(hoaDonChiTiet);
                thanhTien = thanhTien + hoaDonChiTiet.getSoLuongMua() * hoaDonChiTiet.getSach().getGiaBia();
            }
            tvThanhTien.setText("Tổng tiền: " + thanhTien);
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
    }

    public int checkMaSach(List<HoaDonChiTiet> hoaDonChiTietList, String maSach) {
        int pos = -1;
        for (int i = 0; i < hoaDonChiTietList.size(); i++) {
            HoaDonChiTiet  hoaDonChiTiet = hoaDonChiTietList.get(i);
            if (hoaDonChiTiet.getSach().getMaSach().equalsIgnoreCase(maSach)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public int validation() {
        if (edtMaSach.getText().toString().isEmpty() || edtSoLuong.getText().toString().isEmpty() || edtMaHoaDon.getText().toString().isEmpty()) {
            return -1;
        }
        return 1;
    }
}