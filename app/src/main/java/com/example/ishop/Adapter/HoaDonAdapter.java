package com.example.ishop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishop.Activity_Manage.Page_Create_Bill;
import com.example.ishop.Activity_Manage.Page_Detail_Bill;
import com.example.ishop.DAO.DonHangDAO;
import com.example.ishop.Model.DonHang;
import com.example.ishop.Model.HoaDon;
import com.example.ishop.Model.SanPham;
import com.example.ishop.R;

import java.util.ArrayList;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder> implements Filterable {
    private Context context;
    private ArrayList<HoaDon> list;
    private ArrayList<HoaDon> listold;

    public HoaDonAdapter(Context context, ArrayList<HoaDon> list) {
        this.context = context;
        this.list = list;
        this.listold = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ui_recy_manage_bill, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Data_Code_Bill.setText(list.get(position).getMaHD());
        holder.Data_Code_Orders.setText(list.get(position).getMaHD());
        holder.Data_Code_Customer.setText(getmaKH(list.get(position).getMaDH()));
        holder.Data_Date_Orders.setText(list.get(position).getNgay());
        holder.Btn_Detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Page_Detail_Bill.class);
                Bundle b = new Bundle();
                b.putString("maHD", list.get(position).getMaHD());
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
        TextView Data_Code_Bill, Data_Code_Orders, Data_Code_Customer, Data_Date_Orders;
        Button Btn_Detail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Data_Code_Bill = itemView.findViewById(R.id.Data_Code_Bill);
            Data_Code_Orders = itemView.findViewById(R.id.Data_Code_Orders);
            Data_Code_Customer = itemView.findViewById(R.id.Data_Code_Customer);
            Data_Date_Orders = itemView.findViewById(R.id.Data_Date_Orders);
            Btn_Detail = itemView.findViewById(R.id.Btn_Detail);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String stsea = constraint.toString();
                if (stsea.isEmpty()) {
                    list = listold;
                } else {
                    ArrayList<HoaDon> listclone = new ArrayList<>();
                    for (HoaDon sp : listold) {
                        if (sp.getMaHD().toLowerCase().contains(stsea.toLowerCase())) {
                            listclone.add(sp);
                        }

                    }
                    list = listclone;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<HoaDon>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    private String getmaKH(String maDH) {
        String s;
        DonHangDAO donHangDAO = new DonHangDAO(context);
        s = donHangDAO.get_DH(maDH).getMaKH();
        return s.isEmpty() ? "" : s;
    }
}
