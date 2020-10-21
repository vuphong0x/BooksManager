package com.example.booksmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booksmanager.DAO.SachDAO;
import com.example.booksmanager.DAO.TheLoaiDAO;
import com.example.booksmanager.Model.Sach;
import com.example.booksmanager.Model.TheLoai;

import java.util.ArrayList;
import java.util.List;


public class BookActivity extends AppCompatActivity {
    SachDAO sachDAO;
    TheLoaiDAO theLoaiDAO;
    Spinner spinner;
    EditText edtMaSach, edtTenSach, edtNXB, edtTacGia, edtGiaBia, edtSoluong;
    String maTheLoai = "";
    List<TheLoai> listTheLoai = new ArrayList<>();
    List<String> listTenTheLoai = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        setTitle("Thêm Sách");
        spinner = findViewById(R.id.spinner);
        getTheLoai();
        edtMaSach = findViewById(R.id.edtBookCode);
        edtTenSach = findViewById(R.id.edtBookName);
        edtNXB = findViewById(R.id.edtNXB);
        edtTacGia = findViewById(R.id.edtAuthor);
        edtGiaBia = findViewById(R.id.edtPrice);
        edtSoluong = findViewById(R.id.edtNumber);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maTheLoai = listTheLoai.get(spinner.getSelectedItemPosition()).getMaTheLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            edtMaSach.setText(bundle.getString("MaSach"));
            String maTheLoai = bundle.getString("MaTheLoai");
            edtTenSach.setText(bundle.getString("MaSach"));
            edtNXB.setText(bundle.getString("MaSach"));
            edtTacGia.setText(bundle.getString("MaSach"));
            edtSoluong.setText(bundle.getString("MaSach"));
            spinner.setSelection(checkPositionCategory(maTheLoai));
        }
    }

    public void getTheLoai() {
        theLoaiDAO = new TheLoaiDAO(this);
        listTheLoai = theLoaiDAO.getAllCategory();
        for (TheLoai theLoai : listTheLoai) {
            listTenTheLoai.add(theLoai.getTenTheLoai());
        }
        ArrayAdapter<String> dataApdater = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listTenTheLoai);
        dataApdater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataApdater);
    }

    public void addBook(View view) {
        String maSach = edtMaSach.getText().toString();
        String tenSach = edtTenSach.getText().toString();
        String tacGia = edtTacGia.getText().toString();
        String NXB = edtNXB.getText().toString();
        double giaBia = Double.parseDouble(edtGiaBia.getText().toString());
        int soLuong = Integer.parseInt(edtSoluong.getText().toString());

        sachDAO = new SachDAO(this);
        Sach sach = new Sach(maSach, maTheLoai, tenSach, tacGia, NXB, giaBia, soLuong);
        try {
            if (sachDAO.insertBook(sach) > 0) {
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
    }

    public void show(View view) {
        finish();
    }

    public int checkPositionCategory(String maTheLoai) {
        for (int i = 0; i < listTheLoai.size(); i++) {
            if (maTheLoai.equals(listTheLoai.get(i).getMaTheLoai())) {
                return i;
            }
        }
        return 0;
    }
}