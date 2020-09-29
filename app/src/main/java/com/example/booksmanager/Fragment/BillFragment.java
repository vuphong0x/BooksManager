package com.example.booksmanager.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksmanager.Adapter.BillAdapter;
import com.example.booksmanager.Adapter.UserAdapter;
import com.example.booksmanager.DAO.HoaDonDAO;
import com.example.booksmanager.DAO.NguoiDungDAO;
import com.example.booksmanager.Model.HoaDon;
import com.example.booksmanager.Model.NguoiDung;
import com.example.booksmanager.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class BillFragment extends Fragment {
    HoaDonDAO hoaDonDAO;
    Toolbar toolbar;
    Fragment fragment;
    RecyclerView rvUser;
    BillAdapter adapter;
    List<HoaDon> list;
    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Hóa Đơn");
        hoaDonDAO = new HoaDonDAO(container.getContext());
        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new AddBillFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
            }
        });

        list = new ArrayList<>();
//        list.addAll(hoaDonDAO.get());
        rvUser = view.findViewById(R.id.list);
        rvUser.setHasFixedSize(true);
//        adapter = new BillAdapter(list);
//        rvUser.setAdapter(adapter);
        return view;
    }
}