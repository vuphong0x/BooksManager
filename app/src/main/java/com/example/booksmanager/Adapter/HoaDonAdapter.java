package com.example.booksmanager.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksmanager.DAO.HoaDonChiTietDAO;
import com.example.booksmanager.DAO.HoaDonDAO;
import com.example.booksmanager.DAO.SachDAO;
import com.example.booksmanager.Model.HoaDon;
import com.example.booksmanager.Model.Sach;
import com.example.booksmanager.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonAdapter extends BaseAdapter implements Filterable {
    List<HoaDon> arrHoaDon;
    List<HoaDon> arrSortHoaDon;
    private Filter hoaDonFilter;
    public Activity context;
    public LayoutInflater inflater;
    HoaDonDAO hoaDonDAO;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public HoaDonAdapter(Activity context, List<HoaDon> arrHoaDon) {
        super();
        this.arrHoaDon = arrHoaDon;
        this.context = context;
        this.arrSortHoaDon = arrHoaDon;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hoaDonDAO = new HoaDonDAO(context);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(context);
    }

    @Override
    public int getCount() {
        return arrHoaDon.size();
    }

    @Override
    public Object getItem(int position) {
        return arrHoaDon.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView imgIcon;
        TextView tvMaHoaDon;
        TextView tvNgayMua;
        ImageView imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_hoa_don, null);
            holder.imgIcon = convertView.findViewById(R.id.imgIcon);
            holder.tvMaHoaDon = convertView.findViewById(R.id.tvMaHoaDon);
            holder.tvNgayMua = convertView.findViewById(R.id.tvNgayMua);
            holder.imgDelete = convertView.findViewById(R.id.imgDelete);

            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (hoaDonChiTietDAO.checkHoaDon(arrHoaDon.get(position).getMaHoaDon())) {
                        Toast.makeText(context, "Bạn phải xóa hóa đơn chi tiết  trước", Toast.LENGTH_SHORT).show();
                    } else {
                        hoaDonDAO.deleteBill(arrHoaDon.get(position).getMaHoaDon());
                        arrHoaDon.remove(position);
                        notifyDataSetChanged();
                    }
                }
            });

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        HoaDon _entry = arrHoaDon.get(position);
        holder.imgIcon.setImageResource(R.drawable.hdicon);
        holder.tvMaHoaDon.setText(_entry.getMaHoaDon());
        holder.tvNgayMua.setText(sdf.format(_entry.getNgayMua()));

        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataSet(List<HoaDon> items) {
        this.arrHoaDon = items;
        notifyDataSetChanged();
    }

    public void resetData() {
        arrHoaDon = arrSortHoaDon;
    }

    @Override
    public Filter getFilter() {
        if (hoaDonFilter == null) {
            hoaDonFilter = new CustomFilter();
        }
        return hoaDonFilter;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortHoaDon;
                results.count = arrSortHoaDon.size();
            } else {
                List<HoaDon> hoaDonList = new ArrayList<>();
                for (HoaDon hoaDon : arrHoaDon) {
                    if (hoaDon.getMaHoaDon().toUpperCase().startsWith(constraint.toString().toUpperCase())) {
                        hoaDonList.add(hoaDon);
                    }
                }

                results.values = hoaDonList;
                results.count = hoaDonList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0) {
                notifyDataSetChanged();
            } else {
                arrHoaDon = (List<HoaDon>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
