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

import com.example.booksmanager.DAO.SachDAO;
import com.example.booksmanager.Model.Sach;
import com.example.booksmanager.R;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends BaseAdapter implements Filterable {
    List<Sach> arrSach;
    List<Sach> arrSortSach;
    private Filter sachFilter;
    public Activity context;
    public LayoutInflater inflater;
    SachDAO sachDAO;

    public BookAdapter(Activity context, List<Sach> arrSach) {
        super();
        this.arrSach = arrSach;
        this.context = context;
        this.arrSortSach = arrSach;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sachDAO = new SachDAO(context);
    }

    @Override
    public int getCount() {
        return arrSach.size();
    }

    @Override
    public Object getItem(int position) {
        return arrSach.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView imgIcon;
        TextView tvTenSach;
        TextView tvGiaSach;
        TextView tvSoLuong;
        ImageView imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_book, null);
            holder.imgIcon = convertView.findViewById(R.id.imgIcon);
            holder.tvTenSach = convertView.findViewById(R.id.tvTenSach);
            holder.tvSoLuong = convertView.findViewById(R.id.tvSoLuong);
            holder.tvGiaSach = convertView.findViewById(R.id.tvGia);
            holder.imgDelete = convertView.findViewById(R.id.imgDelete);

            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sachDAO.deleteBook(arrSach.get(position).getMaSach());
                    arrSach.remove(position);
                    notifyDataSetChanged();
                }
            });

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Sach _entry = arrSach.get(position);
        holder.imgIcon.setImageResource(R.drawable.book);
        holder.tvTenSach.setText("Mã sách: " + _entry.getMaSach());
        holder.tvSoLuong.setText("Số lượng: " + _entry.getSoLuong());
        holder.tvGiaSach.setText("Giá sách: " + _entry.getGiaBia() + "");
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataSet(List<Sach> items) {
        this.arrSach = items;
        notifyDataSetChanged();
    }

    public void resetData() {
        arrSach = arrSortSach;
    }

    @Override
    public Filter getFilter() {
        if (sachFilter == null) {
            sachFilter = new CustomFilter();
        }
        return sachFilter;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortSach;
                results.count = arrSortSach.size();
            } else {
                List<Sach> sachList = new ArrayList<>();
                for (Sach sach : arrSach) {
                    if (sach.getMaSach().toUpperCase().startsWith(constraint.toString().toUpperCase())) {
                        sachList.add(sach);
                    }
                }

                results.values = sachList;
                results.count = sachList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0) {
                notifyDataSetChanged();
            } else {
                arrSach = (List<Sach>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
