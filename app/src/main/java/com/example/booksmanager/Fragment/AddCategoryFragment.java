package com.example.booksmanager.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.booksmanager.R;

public class AddCategoryFragment extends Fragment {
    EditText edtCategoryCode, edtCategoryName, edtLocation, edtDescription;
    Button btnAdd, btnCancel;
    Toolbar toolbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_category, container, false);
        toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Thêm Thể Loại");
        
        edtCategoryCode = view.findViewById(R.id.edtCategoryCode);
        edtCategoryName = view.findViewById(R.id.edtCategoryName);
        edtLocation = view.findViewById(R.id.edtLocation);
        edtDescription = view.findViewById(R.id.edtDescription);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnCancel = view.findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(v);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel(v);
            }
        });
        return view;
    }

    private void add(View v) {
    }

    public void cancel(View view) {
        getActivity().finish();
    }
}