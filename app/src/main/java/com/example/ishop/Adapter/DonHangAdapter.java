package com.example.ishop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishop.Activity_Manage.Page_Create_Bill;
import com.example.ishop.DAO.LoaiSanPhamDAO;
import com.example.ishop.Model.DonHang;
import com.example.ishop.Model.LoaiSanPham;
import com.example.ishop.R;

import java.util.ArrayList;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHolder>{
    private Context context;
    private ArrayList<DonHang> list;

    public DonHangAdapter(Context context, ArrayList<DonHang> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ui_recy_manage_orders, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Data_Code_Orders.setText(list.get(position).getMaDH());
        holder.Data_Code_Customer.setText(list.get(position).getMaKH());
        holder.Data_Date_Orders.setText(list.get(position).getNgay());
        holder.Data_Satus.setText(list.get(position).getTrangthai());
        if (list.get(position).getTrangthai().equals("Đang xử lý")) {
            holder.Data_Satus.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0065CC")));
        } else if (list.get(position).getTrangthai().equals("Đã xử lý")) {
            holder.Data_Satus.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#63BF67")));
        } else holder.Data_Satus.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#BCBCBC")));

        holder.Recy_Orders_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Page_Create_Bill.class);
                Bundle b = new Bundle();
                b.putInt("numberManage", changceNumber(list.get(position).getTrangthai()));
                b.putString("maDH", list.get(position).getMaDH());
                i.putExtras(b);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout Recy_Orders_Create;
        TextView Data_Code_Orders, Data_Code_Customer, Data_Date_Orders, Data_Satus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Data_Code_Orders = itemView.findViewById(R.id.Data_Code_Orders);
            Data_Code_Customer = itemView.findViewById(R.id.Data_Code_Customer);
            Data_Date_Orders = itemView.findViewById(R.id.Data_Date_Orders);
            Data_Satus = itemView.findViewById(R.id.Data_Satus);
            Recy_Orders_Create = itemView.findViewById(R.id.Recy_Orders_Create);
        }
    }

    //
    private int changceNumber(String trangthai) {
        if (trangthai.equals("Chưa xử lý")) {
            return 0;
        }
        return 1;
    }
}
