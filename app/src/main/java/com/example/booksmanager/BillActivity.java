package com.example.booksmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.example.booksmanager.Fragment.BillFragment;
import com.example.booksmanager.Fragment.UserFragment;

public class BillActivity extends AppCompatActivity {
    Toolbar toolbar;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        // Setup Toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Hóa Đơn");
        toolbar.setBackground(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set default Fragment
        fragment = new BillFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
    }
}