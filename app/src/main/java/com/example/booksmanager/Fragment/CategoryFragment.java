package com.example.booksmanager.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksmanager.Adapter.CategoryAdapter;
import com.example.booksmanager.Adapter.UserAdapter;
import com.example.booksmanager.DAO.NguoiDungDAO;
import com.example.booksmanager.DAO.TheLoaiDAO;
import com.example.booksmanager.Model.NguoiDung;
import com.example.booksmanager.Model.TheLoaiSach;
import com.example.booksmanager.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {
    TheLoaiDAO theLoaiDAO;
    RecyclerView rvUser;
    Toolbar toolbar;
    CategoryAdapter adapter;
    List<TheLoaiSach> list;
    FloatingActionButton fab;
    Fragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set title toolbar
        toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Thể Loại Sách");

        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new AddCategoryFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
            }
        });
        theLoaiDAO = new TheLoaiDAO(container.getContext());
        list = new ArrayList<>();
        list.addAll(theLoaiDAO.getAllCategory());

        rvUser = view.findViewById(R.id.list);
        rvUser.setHasFixedSize(true);
        adapter = new CategoryAdapter(list);
        rvUser.setAdapter(adapter);
        return view;
    }
}
