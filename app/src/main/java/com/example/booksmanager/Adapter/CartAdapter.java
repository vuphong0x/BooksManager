package com.example.booksmanager.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.booksmanager.DAO.HoaDonChiTietDAO;
import com.example.booksmanager.Model.HoaDonChiTiet;
import com.example.booksmanager.Model.Sach;
import com.example.booksmanager.R;

import java.util.List;

public class CartAdapter extends BaseAdapter {
    List<HoaDonChiTiet> arrHoaDonChiTiet;
    public Activity context;
    public LayoutInflater inflater;
    HoaDonChiTietDAO hoaDonChiTietDAO;

    public CartAdapter(Activity context, List<HoaDonChiTiet> arrHoaDonChiTiet) {
        super();
        this.arrHoaDonChiTiet = arrHoaDonChiTiet;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(context);
    }

    @Override
    public int getCount() {
        return arrHoaDonChiTiet.size();
    }

    @Override
    public Object getItem(int position) {
        return arrHoaDonChiTiet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView imgDelete;
        TextView tvMaSach;
        TextView tvGiaBia;
        TextView tvSoLuong;
        TextView tvThanhTien;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_cart, null);
            holder.tvMaSach = convertView.findViewById(R.id.tvMaSach);
            holder.tvGiaBia = convertView.findViewById(R.id.tvGiaSach);
            holder.tvSoLuong = convertView.findViewById(R.id.tvSoLuong);
            holder.tvThanhTien = convertView.findViewById(R.id.tvThanhTien);
            holder.imgDelete = convertView.findViewById(R.id.imgDelete);

            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hoaDonChiTietDAO.deleteHoaDonChiTiet(String.valueOf(arrHoaDonChiTiet.get(position).getMaHoaDonChiTiet()));
                    arrHoaDonChiTiet.remove(position);
                    notifyDataSetChanged();
                }
            });

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        HoaDonChiTiet _entry = arrHoaDonChiTiet.get(position);
        holder.tvMaSach.setText("Mã sách: " + _entry.getSach().getMaSach());
        holder.tvSoLuong.setText("Số lượng: " + _entry.getSoLuongMua());
        holder.tvGiaBia.setText("Giá bìa: " + _entry.getSach().getGiaBia() + " VND");
        holder.tvThanhTien.setText("Thành tiền: " + _entry.getSoLuongMua()*_entry.getSach().getGiaBia() + " VND");
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataSet(List<HoaDonChiTiet> items) {
        this.arrHoaDonChiTiet = items;
        notifyDataSetChanged();
    }
}
