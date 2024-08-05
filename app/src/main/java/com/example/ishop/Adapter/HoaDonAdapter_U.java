package com.example.ishop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishop.Activity_Manage.Page_Detail_Bill;
import com.example.ishop.Activity_Manage.Page_Detail_History_Orders;
import com.example.ishop.DAO.DonHangDAO;
import com.example.ishop.Model.HoaDon;
import com.example.ishop.R;

import java.util.ArrayList;

public class HoaDonAdapter_U extends RecyclerView.Adapter<HoaDonAdapter_U.ViewHolder> implements Filterable {
    private Context context;
    private ArrayList<HoaDon> list;
    private ArrayList<HoaDon> listold;

    public HoaDonAdapter_U(Context context, ArrayList<HoaDon> list) {
        this.context = context;
        this.list = list;
        this.listold = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ui_recy_history_orders, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Data_Code_Bill.setText(list.get(position).getMaHD());
        holder.Data_Code_Orders.setText(list.get(position).getMaDH());
        holder.Data_Total_Price.setText(changePrice(list.get(position).getThanhtien()));
        holder.Data_Date_Orders.setText(dateDH(list.get(position).getMaDH()));
        holder.Recy_History_Orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Page_Detail_History_Orders.class);
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
        TextView Data_Code_Bill, Data_Code_Orders, Data_Total_Price, Data_Date_Orders;
        RelativeLayout Recy_History_Orders;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Data_Code_Bill = itemView.findViewById(R.id.Data_Code_Bill);
            Data_Code_Orders = itemView.findViewById(R.id.Data_Code_Orders);
            Data_Total_Price = itemView.findViewById(R.id.Data_Total_Price);
            Data_Date_Orders = itemView.findViewById(R.id.Data_Date_Orders);
            Recy_History_Orders = itemView.findViewById(R.id.Recy_History_Orders);
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
                    ArrayList<HoaDon> listclone2 = new ArrayList<>();
                    for (HoaDon hd : listold) {
                        if (hd.getMaHD().toLowerCase().contains(stsea.toLowerCase())) {
                            listclone.add(hd);
                        }
                        if (dateDH(hd.getMaDH()).toLowerCase().contains(stsea.toLowerCase())) {
                            listclone2.add(hd);
                        }
                    }
                    list = listclone.isEmpty() ? listclone2 : listclone;
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

    private String dateDH(String maDH) {
        DonHangDAO donHangDAO = new DonHangDAO(context);
        String s = donHangDAO.get_DH(maDH).getNgay();
        s = s.isEmpty() ? "" : s;
        return s;
    }

    //hàm dấu chấm vào giá
    String changePrice(long n) {
        String s = "";
        while (n / 1000 > 0) {
            if (n % 1000 == 0) {
                s += ".000";
            } else {
                s = "." + n % 1000 + s;
            }
            n = n / 1000;
        }
        return n + s;
    }
}
