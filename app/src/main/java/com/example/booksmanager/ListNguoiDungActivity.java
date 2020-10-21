package com.example.booksmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.booksmanager.Adapter.UserAdapter;
import com.example.booksmanager.DAO.NguoiDungDAO;
import com.example.booksmanager.Model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class ListNguoiDungActivity extends AppCompatActivity {
    public  static List<NguoiDung> dsNguoiDung = new ArrayList<>();
    ListView lvNguoiDung;
    UserAdapter adapter = null;
    NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nguoi_dung);

        setTitle("Người Dùng");
        lvNguoiDung = findViewById(R.id.lvNguoiDung);
        nguoiDungDAO = new NguoiDungDAO(this);
        dsNguoiDung = nguoiDungDAO.getAllUser();

        adapter = new UserAdapter(this, dsNguoiDung);
        lvNguoiDung.setAdapter(adapter);
        lvNguoiDung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListNguoiDungActivity.this, NguoiDungDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("UserName", dsNguoiDung.get(position).getUserName());
                bundle.putString("Phone", dsNguoiDung.get(position).getNumberPhone());
                bundle.putString("Name", dsNguoiDung.get(position).getName());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        lvNguoiDung.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dsNguoiDung.clear();
        dsNguoiDung = nguoiDungDAO.getAllUser();
        adapter.changeDataSet(nguoiDungDAO.getAllUser());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.add:
                intent = new Intent(this, UserActivity.class);
                startActivity(intent);
                return true;
            case R.id.changePass:
                intent = new Intent(this, ChangePasswordActivity.class);
                startActivity(intent);
                return true;
            case R.id.logOut:
                SharedPreferences preferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                // Xoa tinh trang luu truoc do
                editor.clear();
                editor.commit();
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}