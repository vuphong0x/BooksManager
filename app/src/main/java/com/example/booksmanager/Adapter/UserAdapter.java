package com.example.booksmanager.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksmanager.DAO.NguoiDungDAO;
import com.example.booksmanager.DAO.SachDAO;
import com.example.booksmanager.Model.NguoiDung;
import com.example.booksmanager.Model.Sach;
import com.example.booksmanager.R;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends BaseAdapter {
    List<NguoiDung> arrNguoiDung;
    public Activity context;
    public LayoutInflater inflater;
    NguoiDungDAO nguoiDungDAO;

    public UserAdapter(Activity context, List<NguoiDung> arrNguoiDung) {
        super();
        this.context = context;
        this.arrNguoiDung = arrNguoiDung;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nguoiDungDAO = new NguoiDungDAO(context);
    }

    @Override
    public int getCount() {
        return arrNguoiDung.size();
    }

    @Override
    public Object getItem(int position) {
        return arrNguoiDung.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView imgIcon;
        TextView tvName;
        TextView tvPhone;
        ImageView imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_nguoi_dung, null);
            holder.imgIcon = convertView.findViewById(R.id.imgIcon);
            holder.tvName = convertView.findViewById(R.id.tvName);
            holder.tvPhone = convertView.findViewById(R.id.tvPhone);
            holder.imgDelete = convertView.findViewById(R.id.imgDelete);

            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nguoiDungDAO.deleteUser(arrNguoiDung.get(position).getUserName());
                    arrNguoiDung.remove(position);
                    notifyDataSetChanged();
                }
            });

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        NguoiDung _entry = arrNguoiDung.get(position);
        if (position % 3 == 0) {
            holder.imgIcon.setImageResource(R.drawable.emone);
        } else if (position % 3 == 1) {
            holder.imgIcon.setImageResource(R.drawable.emtwo);
        } else {
            holder.imgIcon.setImageResource(R.drawable.emthree);
        }
        holder.tvName.setText(_entry.getName());
        holder.tvPhone.setText(_entry.getNumberPhone());

        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataSet(List<NguoiDung> items) {
        this.arrNguoiDung = items;
        notifyDataSetChanged();
    }
}
