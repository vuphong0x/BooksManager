package com.example.booksmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.booksmanager.Fragment.ItemFragment;
import com.example.booksmanager.Fragment.UserFragment;

public class UserActivity extends AppCompatActivity {
    Fragment fragment;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Người Dùng");
        toolbar.setBackground(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        fragment = new ItemFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_addUser:
                fragment = new UserFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
                break;
            case R.id.menu_changePassword:
                fragment = new ChangePasswordFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
                break;
            case R.id.menu_logout:
                onBackPressed();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}