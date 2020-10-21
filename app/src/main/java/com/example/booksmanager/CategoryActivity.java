package com.example.booksmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.booksmanager.DAO.TheLoaiDAO;
import com.example.booksmanager.Model.TheLoai;

public class CategoryActivity extends AppCompatActivity {
    EditText edtMaTheLoai, edtTenTheLoai, edtMoTa, edtViTri;
    TheLoaiDAO theLoaiDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        setTitle("Thể Loại");
        edtMaTheLoai = findViewById(R.id.edtCategoryCode);
        edtTenTheLoai = findViewById(R.id.edtCategoryName);
        edtMoTa = findViewById(R.id.edtDescription);
        edtViTri = findViewById(R.id.edtLocation);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            edtMaTheLoai.setText(bundle.getString("MaTheLoai"));
            edtTenTheLoai.setText(bundle.getString("TenTheLoai"));
            edtMoTa.setText(bundle.getString("MoTa"));
            edtViTri.setText(bundle.getString("ViTri"));
        }
    }

    public void addCategory(View view) {
        theLoaiDAO = new TheLoaiDAO(this);

        try {
            if (validation() < 0) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                TheLoai theLoai = new TheLoai(edtMaTheLoai.getText().toString(), edtTenTheLoai.getText().toString(),
                        edtMoTa.getText().toString(), Integer.parseInt(edtViTri.getText().toString()));
                if (theLoaiDAO.insertCategory(theLoai) > 0) {
                    Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
    }

    private int validation() {
        int check = 1;
        if (edtMaTheLoai.getText().length() == 0 || edtTenTheLoai.getText().length() == 0
         || edtViTri.getText().length() == 0 || edtMoTa.getText().length() == 0) {
            check = -1;
        }
        return check;
    }

    public void show(View view) {
        finish();
    }

    public void cancel(View view) {
        finish();
    }
}