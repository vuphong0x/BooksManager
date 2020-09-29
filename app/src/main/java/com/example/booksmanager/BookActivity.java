package com.example.booksmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.booksmanager.Fragment.BookFragment;
import com.example.booksmanager.Fragment.UserFragment;
import com.example.booksmanager.Fragment.AddUserFragment;

public class BookActivity extends AppCompatActivity {
    Toolbar toolbar;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        // Setup Toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Sách");
        toolbar.setBackground(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set default Fragment
        fragment = new BookFragment();
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
}