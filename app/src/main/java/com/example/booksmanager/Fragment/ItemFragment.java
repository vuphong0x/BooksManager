package com.example.booksmanager.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.booksmanager.Adapter.UserAdapter;
import com.example.booksmanager.DAO.NguoiDungDAO;
import com.example.booksmanager.Database.Database;
import com.example.booksmanager.Model.NguoiDung;
import com.example.booksmanager.MyItemRecyclerViewAdapter;
import com.example.booksmanager.R;
import com.example.booksmanager.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

public class ItemFragment extends Fragment {
    NguoiDungDAO nguoiDungDAO;
    RecyclerView rvUser;
    UserAdapter userAdapter;
    List<NguoiDung> userList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        nguoiDungDAO = new NguoiDungDAO(container.getContext());
        userList = new ArrayList<>();
        userList.addAll(nguoiDungDAO.getAllUser());
        rvUser = view.findViewById(R.id.list);
        rvUser.setHasFixedSize(true);
        userAdapter = new UserAdapter(userList);
        rvUser.setAdapter(userAdapter);
        return view;
    }
}