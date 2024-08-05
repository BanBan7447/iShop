package com.example.ishop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishop.Model.KhachHang;
import com.example.ishop.Model.SanPham;
import com.example.ishop.R;
import com.example.ishop.Type_Customers.Fragment_Page_TypeC.FragPage_Profile;
import com.example.ishop.Type_Customers.Page_Detail_Product;
import com.example.ishop.Type_Manager.Page_Customer_Profile;

import java.util.ArrayList;

public class KhacHangAdapter extends RecyclerView.Adapter<KhacHangAdapter.ViewHolder> implements Filterable {
    private Context context;
    private ArrayList<KhachHang> list;
    private ArrayList<KhachHang> listold;

    public KhacHangAdapter(Context context, ArrayList<KhachHang> list) {
        this.context = context;
        this.list = list;
        this.listold = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ui_recy_manage_customer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int id = context.getResources().getIdentifier(list.get(position).getAnh(), "mipmap", context.getPackageName());
        holder.Data_Image_Customer.setImageResource(id);
        holder.Data_Code_Customer.setText(list.get(position).getMa());
        holder.Data_Name_Customer.setText(list.get(position).getTen());
        holder.Data_SDT_Customer.setText(list.get(position).getSdt());

        //send data to detail product
        holder.all_item_u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Page_Customer_Profile.class);
                Bundle b = new Bundle();
                b.putString("maKH", list.get(position).getMa());
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
        ImageView Data_Image_Customer;
        TextView Data_Code_Customer, Data_Name_Customer, Data_SDT_Customer;
        LinearLayout all_item_u;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Data_Image_Customer = itemView.findViewById(R.id.Data_Image_Customer);
            Data_Code_Customer = itemView.findViewById(R.id.Data_Code_Customer);
            Data_Name_Customer = itemView.findViewById(R.id.Data_Name_Customer);
            Data_SDT_Customer = itemView.findViewById(R.id.Data_SDT_Customer);
            all_item_u = itemView.findViewById(R.id.all_item_u);
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
                    ArrayList<KhachHang> listclone = new ArrayList<>();
                    for (KhachHang sp : listold) {
                        if (sp.getTen().toLowerCase().contains(stsea.toLowerCase())) {
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
                list = (ArrayList<KhachHang>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
