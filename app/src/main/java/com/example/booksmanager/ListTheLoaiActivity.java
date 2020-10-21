package com.example.booksmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.booksmanager.Adapter.CategoryAdapter;
import com.example.booksmanager.DAO.TheLoaiDAO;
import com.example.booksmanager.Model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class ListTheLoaiActivity extends AppCompatActivity {
    public static List<TheLoai> dsTheLoai = new ArrayList<>();
    ListView lvTheLoai;
    CategoryAdapter adapter = null;
    TheLoaiDAO theLoaiDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_the_loai);

        setTitle("Thể Loại");
        lvTheLoai = findViewById(R.id.lvTheLoai);
        registerForContextMenu(lvTheLoai);
        theLoaiDAO = new TheLoaiDAO(this);
        dsTheLoai = theLoaiDAO.getAllCategory();

        adapter = new CategoryAdapter(this, dsTheLoai);
        lvTheLoai.setAdapter(adapter);
        lvTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListTheLoaiActivity.this, CategoryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("MaTheLoai", dsTheLoai.get(position).getMaTheLoai());
                bundle.putString("TenTheLoai", dsTheLoai.get(position).getTenTheLoai());
                bundle.putString("MoTa", dsTheLoai.get(position).getMoTa());
                bundle.putString("ViTri", String.valueOf(dsTheLoai.get(position).getViTri()));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_theloai, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(ListTheLoaiActivity.this, CategoryActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dsTheLoai.clear();
        dsTheLoai = theLoaiDAO.getAllCategory();
        adapter.changeDataSet(dsTheLoai);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
        menu.setHeaderTitle("Chọn Thông Tin");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menu_ctx_edit:
                intent = new Intent(ListTheLoaiActivity.this, CategoryActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_ctx_delete:
                intent = new Intent(ListTheLoaiActivity.this, CategoryActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onContextItemSelected(item);
    }
}