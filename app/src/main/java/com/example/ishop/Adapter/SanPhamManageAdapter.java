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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishop.DAO.LoaiSanPhamDAO;
import com.example.ishop.Model.DonHang;
import com.example.ishop.Model.SanPham;
import com.example.ishop.R;
import com.example.ishop.Type_Customers.Page_Detail_Product;
import com.example.ishop.Type_Manager.Page_Fix_Product;
import com.example.ishop.Type_Manager.Page_Manage_Detail_Product;

import java.util.ArrayList;

public class SanPhamManageAdapter extends RecyclerView.Adapter<SanPhamManageAdapter.ViewHolder> implements Filterable {
    private Context context;
    private ArrayList<SanPham> list;
    private ArrayList<SanPham> listold;

    public SanPhamManageAdapter(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
        this.listold = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ui_recy_manage_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int id = context.getResources().getIdentifier(list.get(position).getAnh(), "mipmap", context.getPackageName());
        holder.Data_Image_Product.setImageResource(id);
        holder.Data_Code_Product.setText(list.get(position).getMaSP());
        holder.Data_Name_Product.setText(list.get(position).getTen());
        holder.Data_Price_Product.setText(changePrice(list.get(position).getGia()));
        holder.Data_Name_ProductType.setText(nameType(list.get(position).getMaLSP()));

        //send data to detail product
        holder.all_item_m_Product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Page_Manage_Detail_Product.class);
                Bundle b = new Bundle();
                b.putString("maSP", list.get(position).getMaSP());
                i.putExtras(b);
                context.startActivity(i);
            }
        });

        //update
        holder.Btn_Page_Fix_Product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Page_Fix_Product.class);
                Bundle b = new Bundle();
                b.putString("maSP", list.get(position).getMaSP());
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
        ImageView Data_Image_Product;
        TextView Data_Name_Product, Data_Price_Product, Data_Name_ProductType, Data_Code_Product;
        RelativeLayout all_item_m_Product;
        Button Btn_Page_Fix_Product;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Data_Image_Product = itemView.findViewById(R.id.Data_Image_Product);
            Data_Code_Product = itemView.findViewById(R.id.Data_Code_Product);
            Data_Name_Product = itemView.findViewById(R.id.Data_Name_Product);
            Data_Price_Product = itemView.findViewById(R.id.Data_Price_Product);
            Data_Name_ProductType = itemView.findViewById(R.id.Data_Name_ProductType);
            Btn_Page_Fix_Product = itemView.findViewById(R.id.Btn_Page_Fix_Product);
            all_item_m_Product = itemView.findViewById(R.id.all_item_m_Product);
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
                    ArrayList<SanPham> listclone = new ArrayList<>();
                    for (SanPham sp : listold) {
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
                list = (ArrayList<SanPham>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    private String nameType(String maLSP) {
        LoaiSanPhamDAO dao = new LoaiSanPhamDAO(context);
        String name = dao.get_nameLSP(maLSP);
        return name != null ? name : "***";
    }

    //hàm dấu chấm vào giá
    String changePrice(int n) {
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
