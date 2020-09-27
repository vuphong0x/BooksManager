package com.example.booksmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toActivityStatistical(View view) {
    }

    public void toActivityCategory(View view) {
        intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }

    public void toActivityBill(View view) {
    }

    public void toActivitySelling(View view) {
    }

    public void toActivityBooks(View view) {
    }

    public void toActivityUser(View view) {
        intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }
}