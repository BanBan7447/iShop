package com.example.ishop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishop.Model.SanPham;
import com.example.ishop.R;

import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SanPham> list;

    public SanPhamAdapter(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Data_Image_Product;
        TextView Data_Name_Product, Data_Price_Product;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Data_Image_Product = itemView.findViewById(R.id.Data_Image_Product);
            Data_Name_Product = itemView.findViewById(R.id.Data_Name_Product);
            Data_Price_Product = itemView.findViewById(R.id.Data_Price_Product);
        }
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
            n=n/1000;
        }
        return n + s;
    }
}
