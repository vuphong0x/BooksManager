package com.example.booksmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.booksmanager.DAO.TheLoaiDAO;
import com.example.booksmanager.Fragment.AddCategoryFragment;
import com.example.booksmanager.Fragment.CategoryFragment;
import com.example.booksmanager.Fragment.UserFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CategoryActivity extends AppCompatActivity {
    Toolbar toolbar;
    Fragment fragment;
    TheLoaiDAO theLoaiDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Setup Toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Thể Loại Sách");
        toolbar.setBackground(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set default Fragment
        fragment = new CategoryFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addCategory(View view) {
        theLoaiDAO = new TheLoaiDAO(CategoryActivity.this);
//        TheLoaiSach theLoaiSach = new TheLoaiSach(
//                edtUser.getText().toString(),
//                edtPass.getText().toString(),
//                edtPhone.getText().toString(),
//                edtFullname.getText().toString()
//        );
//        try {
//            if (theLoaiDAO.insertNguoiDung(nguoiDung) > 0) {
//                Toast.makeText(getApplicationContext(), "User added successfully", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(getApplicationContext(), "User add failed", Toast.LENGTH_SHORT).show();
//            }
//        } catch (Exception e) {
//        }
    }
}