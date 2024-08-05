package com.example.ishop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishop.Model.SanPham;
import com.example.ishop.R;

import java.util.ArrayList;

public class CTDHAdapter extends RecyclerView.Adapter<CTDHAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SanPham> list;

    public CTDHAdapter(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ui_recy_product_payment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        long gia = list.get(position).getGia() * list.get(position).getSoluong();
        int id = context.getResources().getIdentifier(list.get(position).getAnh(), "mipmap", context.getPackageName());
        holder.Data_Image_Product.setImageResource(id);
        holder.Data_Name_Product.setText(list.get(position).getTen());
        holder.Data_Name_ProductType.setText(changeNameType(list.get(position).getMaLSP()));
        holder.Data_Price_Product.setText(changePrice(gia));
        holder.Quantity_ProductCart.setText(String.valueOf(list.get(position).getSoluong()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Data_Image_Product;
        TextView Data_Name_Product, Data_Price_Product, Data_Name_ProductType, Quantity_ProductCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Data_Image_Product = itemView.findViewById(R.id.Data_Image_ProductCart);
            Data_Name_Product = itemView.findViewById(R.id.Data_Name_ProductCart);
            Data_Price_Product = itemView.findViewById(R.id.Data_Price_ProductCart);
            Data_Name_ProductType = itemView.findViewById(R.id.Data_Name_ProductTypeCart);
            Quantity_ProductCart = itemView.findViewById(R.id.Data_Quantity_ProductCart);
        }
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

    public String getSL(){
        int sl = 0;
        for (SanPham sp : list) {
            sl += sp.getSoluong();
        }
        if (changePrice(sl).isEmpty()){
            return "0";
        } else
       return changePrice(sl);
    }

    public long getTT(){
        long tt = 0;
        for (SanPham sp : list) {
            tt += sp.getSoluong()*sp.getGia();
        }

        return tt;
    }

    private String changeNameType(String s) {
        if (s.equals("IP27"))
            return "iPhone";
        if (s.equals("IA10"))
            return "iPad";
        if (s.equals("IM84"))
            return "Mac";
        if (s.equals("IW15"))
            return "Apple Watch";
        return "";
    }
}
