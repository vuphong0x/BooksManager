package com.example.booksmanager.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksmanager.Database.Database;
import com.example.booksmanager.Model.TheLoaiSach;
import com.example.booksmanager.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private Database database;
    private List<TheLoaiSach> theLoaiSachList;

    public CategoryAdapter(List<TheLoaiSach> theLoaiSachList) {
        this.theLoaiSachList = theLoaiSachList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        database = new Database(parent.getContext());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvTitle.setText(theLoaiSachList.get(position).getTenTheLoai());
        holder.tvDescription.setText(theLoaiSachList.get(position).getTenTheLoai());
        holder.imgAvatar.setImageResource(R.drawable.emone);
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theLoaiSachList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return theLoaiSachList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription;
        ImageView imgAvatar, imgDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            imgDelete = itemView.findViewById(R.id.imgDelete);
        }
    }
}
