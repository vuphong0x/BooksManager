package com.example.booksmanager.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksmanager.DAO.NguoiDungDAO;
import com.example.booksmanager.DAO.TheLoaiDAO;
import com.example.booksmanager.Model.NguoiDung;
import com.example.booksmanager.Model.TheLoai;
import com.example.booksmanager.R;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    List<TheLoai> arrTheLoai;
    public Activity context;
    public LayoutInflater inflater;
    TheLoaiDAO theLoaiDAO;

    public CategoryAdapter(Activity context, List<TheLoai> arrTheLoai) {
        super();
        this.context = context;
        this.arrTheLoai = arrTheLoai;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        theLoaiDAO = new TheLoaiDAO(context);
    }

    @Override
    public int getCount() {
        return arrTheLoai.size();
    }

    @Override
    public Object getItem(int position) {
        return arrTheLoai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView imgIcon;
        TextView tvMaTheLoai;
        TextView tvTenTheLoai;
        ImageView imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_the_loai, null);
            holder.imgIcon = convertView.findViewById(R.id.imgIcon);
            holder.tvMaTheLoai = convertView.findViewById(R.id.tvMaTheLoai);
            holder.tvTenTheLoai = convertView.findViewById(R.id.tvTenTheLoai);
            holder.imgDelete = convertView.findViewById(R.id.imgDelete);

            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    theLoaiDAO.deleteCategory(arrTheLoai.get(position).getMaTheLoai());
                    arrTheLoai.remove(position);
                    notifyDataSetChanged();
                }
            });

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TheLoai _entry = arrTheLoai.get(position);
        holder.imgIcon.setImageResource(R.drawable.category);
        holder.tvMaTheLoai.setText(_entry.getMaTheLoai());
        holder.tvTenTheLoai.setText(_entry.getTenTheLoai());

        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataSet(List<TheLoai> items) {
        this.arrTheLoai = items;
        notifyDataSetChanged();
    }
}
