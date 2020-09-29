package com.example.booksmanager.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.booksmanager.Adapter.UserAdapter;
import com.example.booksmanager.DAO.NguoiDungDAO;
import com.example.booksmanager.Model.NguoiDung;
import com.example.booksmanager.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends Fragment {
    NguoiDungDAO nguoiDungDAO;
    Toolbar toolbar;
    Fragment fragment;
    RecyclerView rvUser;
    UserAdapter userAdapter;
    List<NguoiDung> userList;
    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Người Dùng");
        nguoiDungDAO = new NguoiDungDAO(container.getContext());
        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new AddUserFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
            }
        });

        userList = new ArrayList<>();
        userList.addAll(nguoiDungDAO.getAllUser());
        rvUser = view.findViewById(R.id.list);
        rvUser.setHasFixedSize(true);
        userAdapter = new UserAdapter(userList);
        rvUser.setAdapter(userAdapter);

        // Set listener when click on item recycle view
        userAdapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        return view;
    }
}