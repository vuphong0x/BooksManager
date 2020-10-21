package com.example.booksmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.booksmanager.Adapter.BookAdapter;
import com.example.booksmanager.DAO.SachDAO;
import com.example.booksmanager.Model.Sach;

import java.util.ArrayList;
import java.util.List;

public class LuotSachBanChayActivity extends AppCompatActivity {
    public static List<Sach> dsSach = new ArrayList<>();
    ListView lvBook;
    BookAdapter adapter = null;
    SachDAO sachDAO;
    EditText edtThang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luot_sach_ban_chay);

        setTitle("Top 10 Sách Bán Chạy");
        lvBook = findViewById(R.id.lvBookTop);
        edtThang = findViewById(R.id.edtThang);
    }

    public void viewSachTop10(View view) {
        int month = Integer.parseInt(edtThang.getText().toString());
        if (edtThang.getText().toString().isEmpty()) {
            Toast.makeText(this, "Nhập tháng cần tìm", Toast.LENGTH_SHORT).show();
        } else if (month > 13 || month < 0) {
            Toast.makeText(this, "Không đúng định dạng tháng", Toast.LENGTH_SHORT).show();
        } else {
            sachDAO = new SachDAO(this);
            dsSach = sachDAO.get10BestSellBook(edtThang.getText().toString());
            adapter = new BookAdapter(this, dsSach);
            lvBook.setAdapter(adapter);
        }
    }
}