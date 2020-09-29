package com.example.booksmanager.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksmanager.DAO.NguoiDungDAO;
import com.example.booksmanager.Database.Database;
import com.example.booksmanager.Model.NguoiDung;
import com.example.booksmanager.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private NguoiDungDAO nguoiDungDAO;
    private List<NguoiDung> nguoiDungList;
    private Spinner spinner;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public UserAdapter(List<NguoiDung> nguoiDungList) {
        this.nguoiDungList = nguoiDungList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        nguoiDungDAO = new NguoiDungDAO(parent.getContext());
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvTitle.setText(nguoiDungList.get(position).getName());
        holder.tvDescription.setText(nguoiDungList.get(position).getNumberPhone());
        holder.imgAvatar.setImageResource(R.drawable.emone);
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nguoiDungList.remove(position);
                nguoiDungDAO.deleteUser(nguoiDungList.get(position).getUserName());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return nguoiDungList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription;
        ImageView imgAvatar, imgDelete;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            imgDelete = itemView.findViewById(R.id.imgDelete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
