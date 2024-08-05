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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishop.Model.HoaDon;
import com.example.ishop.Model.SanPham;
import com.example.ishop.R;
import com.example.ishop.Type_Customers.Page_Detail_Product;

import java.util.ArrayList;
import java.util.Locale;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder> implements Filterable {
    private Context context;
    private ArrayList<SanPham> list;
    private ArrayList<SanPham> listold;

    public SanPhamAdapter(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
        this.listold = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ui_recy_product_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Data_Price_Product.setText(changePrice(list.get(position).getGia()));
        holder.Data_Name_Product.setText(list.get(position).getTen());
        int id = context.getResources().getIdentifier(list.get(position).getAnh(), "mipmap", context.getPackageName());
        holder.Data_Image_Product.setImageResource(id);

        //send data to detail product
        holder.all_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Page_Detail_Product.class);
                Bundle b = new Bundle();
                b.putString("masp", list.get(position).getMaSP());
                b.putString("anh", list.get(position).getAnh());
                b.putString("ten", list.get(position).getTen());
                b.putInt("gia", list.get(position).getSoluong());
                b.putString("mota", list.get(position).getMota());
                b.putString("malsp", list.get(position).getMaLSP());
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
        TextView Data_Name_Product, Data_Price_Product;
        LinearLayout all_item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Data_Image_Product = itemView.findViewById(R.id.Data_Image_Product);
            Data_Name_Product = itemView.findViewById(R.id.Data_Name_Product);
            Data_Price_Product = itemView.findViewById(R.id.Data_Price_Product);
            all_item = itemView.findViewById(R.id.all_item);
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
