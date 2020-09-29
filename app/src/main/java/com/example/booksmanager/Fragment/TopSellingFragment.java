package com.example.booksmanager.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksmanager.Adapter.BookAdapter;
import com.example.booksmanager.DAO.SachDAO;
import com.example.booksmanager.Model.Sach;
import com.example.booksmanager.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TopSellingFragment extends Fragment {
    SachDAO sachDAO;
    RecyclerView recyclerView;
    Toolbar toolbar;
    BookAdapter adapter;
    List<Sach> list;
    FloatingActionButton fab;
    Fragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set title toolbar
        toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Sách Bán Chạy");

        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new AddBookFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
            }
        });
        sachDAO = new SachDAO(container.getContext());
        list = new ArrayList<>();
//        list.addAll(sachDAO.getAllBook());

        recyclerView = view.findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        adapter = new BookAdapter(list);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
