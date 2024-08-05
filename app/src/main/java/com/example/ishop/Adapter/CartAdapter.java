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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SanPham> list;

    public CartAdapter(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ui_recy_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int id = context.getResources().getIdentifier(list.get(position).getAnh(), "mipmap", context.getPackageName());
        holder.Data_Image_Product.setImageResource(id);
        holder.Data_Name_Product.setText(list.get(position).getTen());
        holder.Data_Name_ProductType.setText(changeNameType(list.get(position).getMaLSP()));
        holder.Data_Price_Product.setText(changePrice(list.get(position).getGia()));
        holder.Quantity_ProductCart.setText(String.valueOf(list.get(position).getSoluong()));

        //down
        holder.Btn_Down_ProductCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl = list.get(position).getSoluong();
                if (sl > 1)
                    list.get(position).setSoluong(sl - 1);
                holder.Quantity_ProductCart.setText(String.valueOf(list.get(position).getSoluong()));
            }
        });

        //up
        holder.Btn_Up_ProductCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl = list.get(position).getSoluong();
                list.get(position).setSoluong(sl + 1);
                holder.Quantity_ProductCart.setText(String.valueOf(list.get(position).getSoluong()));
            }
        });
        //delete product
        holder.Btn_Del_ProductCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Data_Image_Product, Btn_Down_ProductCart, Btn_Up_ProductCart;
        TextView Data_Name_Product, Data_Price_Product, Data_Name_ProductType, Quantity_ProductCart;
        ImageButton Btn_Del_ProductCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Data_Image_Product = itemView.findViewById(R.id.Data_Image_Product);
            Data_Name_Product = itemView.findViewById(R.id.Data_Name_Product);
            Data_Price_Product = itemView.findViewById(R.id.Data_Price_Product);
            Btn_Del_ProductCart = itemView.findViewById(R.id.Btn_Del_ProductCart);
            Data_Name_ProductType = itemView.findViewById(R.id.Data_Name_ProductType);
            Btn_Down_ProductCart = itemView.findViewById(R.id.Btn_Down_ProductCart);
            Btn_Up_ProductCart = itemView.findViewById(R.id.Btn_Up_ProductCart);
            Quantity_ProductCart = itemView.findViewById(R.id.Quantity_ProductCart);
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
            n = n / 1000;
        }
        return n + s;
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
