package com.example.booksmanager.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.booksmanager.DAO.NguoiDungDAO;
import com.example.booksmanager.Model.NguoiDung;
import com.example.booksmanager.R;

public class AddUserFragment extends Fragment {
    Button btnAddUser, btnCancel;
    NguoiDungDAO nguoiDungDAO;
    EditText edtUser, edtPass, edtRepass, edtPhone, edtFullname;
    Toolbar toolbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);
        toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Thêm Người Dùng");
        btnAddUser = view.findViewById(R.id.btnAddUser);
        btnCancel = view.findViewById(R.id.btnCancel);
        edtUser = view.findViewById(R.id.edUserName);
        edtPass = view.findViewById(R.id.edPassword);
        edtRepass = view.findViewById(R.id.edRePassword);
        edtPhone = view.findViewById(R.id.edPhone);
        edtFullname = view.findViewById(R.id.edFullName);

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser(v);
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

    public void addUser(View view) {
        nguoiDungDAO = new NguoiDungDAO(getContext());
        NguoiDung nguoiDung = new NguoiDung(
                edtUser.getText().toString(),
                edtPass.getText().toString(),
                edtPhone.getText().toString(),
                edtFullname.getText().toString()
        );
        try {
            if (nguoiDungDAO.insertNguoiDung(nguoiDung) > 0) {
                Toast.makeText(getContext(), "User added successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "User add failed", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
        }
    }
    public void cancel(View view) {
        getActivity().finish();
    }
}