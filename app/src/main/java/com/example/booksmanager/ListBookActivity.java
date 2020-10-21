package com.example.booksmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.booksmanager.Adapter.BookAdapter;
import com.example.booksmanager.DAO.SachDAO;
import com.example.booksmanager.Model.Sach;

import java.util.ArrayList;
import java.util.List;

public class ListBookActivity extends AppCompatActivity {
    public static List<Sach> dsSach = new ArrayList<>();
    ListView lvBook;
    BookAdapter adapter = null;
    SachDAO sachDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_book);

        setTitle("Quản Lý Sách");
        lvBook = findViewById(R.id.lvBook);
        sachDAO = new SachDAO(this);
        dsSach = sachDAO.getAllBook();

        adapter = new BookAdapter(this, dsSach);
        lvBook.setAdapter(adapter);
        lvBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Sach sach = (Sach) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListBookActivity.this, BookActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("MaSach", sach.getMaSach());
                bundle.putString("MaTheLoai", sach.getMaTheLoai());
                bundle.putString("TenSach", sach.getTenSach());
                bundle.putString("TacGia", sach.getTacGia());
                bundle.putString("NXB", sach.getNXB());
                bundle.putString("GiaBia", String.valueOf(sach.getGiaBia()));
                bundle.putString("SoLuong", String.valueOf(sach.getSoLuong()));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        lvBook.setTextFilterEnabled(true);
        EditText edtSearch = findViewById(R.id.edtSearchBook);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("Text [" + s + "] - Start [" + start + "] - Before [" + before + "] - Count [" + count + "]");
                if (count < before) {
                    adapter.resetData();
                }
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(ListBookActivity.this, BookActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}