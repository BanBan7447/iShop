package com.example.ishop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishop.Model.LoaiSanPham;
import com.example.ishop.R;
import com.example.ishop.Type_Customers.Page_Detail_List_Product;

import java.util.ArrayList;

public class LoaiSPAdapter_User1 extends RecyclerView.Adapter<LoaiSPAdapter_User1.ViewHolder>{
    private Context context;
    private ArrayList<LoaiSanPham> list;

    public LoaiSPAdapter_User1(Context context, ArrayList<LoaiSanPham> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public LoaiSPAdapter_User1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ui_recy_select_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_Select_Product.setText(list.get(position).getTen());
        int id = context.getResources().getIdentifier(list.get(position).getAnh(), "mipmap", context.getPackageName());
        holder.img_Select_Product.setImageResource(id);

        //show all product of type
        holder.img_Select_Product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Page_Detail_List_Product.class);
                Bundle b = new Bundle();
                b.putString("maLSP", list.get(position).getMaLSP());
                b.putString("tenLSP", list.get(position).getTen());
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
        TextView txt_Select_Product;
        ImageView img_Select_Product;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_Select_Product = itemView.findViewById(R.id.img_Select_Product);
            txt_Select_Product = itemView.findViewById(R.id.txt_Select_Product);
        }
    }
}
