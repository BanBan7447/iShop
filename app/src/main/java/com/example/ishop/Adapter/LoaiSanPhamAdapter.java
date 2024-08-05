package com.example.ishop.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishop.DAO.LoaiSanPhamDAO;
import com.example.ishop.DAO.SanPhamDAO;
import com.example.ishop.Model.LoaiSanPham;
import com.example.ishop.Model.SanPham;
import com.example.ishop.R;
import com.example.ishop.Type_Manager.Page_Manage_Product;

import java.util.ArrayList;

public class LoaiSanPhamAdapter extends RecyclerView.Adapter<LoaiSanPhamAdapter.ViewHolder>{
    private Context context;
    private ArrayList<LoaiSanPham> list;

    public LoaiSanPhamAdapter(Context context, ArrayList<LoaiSanPham> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ui_recy_manage_product_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Data_Code_ProductType.setText(list.get(position).getMaLSP());
        holder.Data_Name_ProductType.setText(list.get(position).getTen());
        holder.Data_Quantity_ProductType.setText(countProduct(list.get(position).getMaLSP()));
        int id = context.getResources().getIdentifier(list.get(position).getAnh(), "mipmap", context.getPackageName());
        holder.Data_Image_ProductType.setImageResource(id);

        holder.all_item_m_ProductT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Page_Manage_Product.class);
                Bundle b = new Bundle();
                b.putString("tenLSP", list.get(position).getTen());
                b.putString("maLSP", list.get(position).getMaLSP());
                i.putExtras(b);
                context.startActivity(i);
            }
        });

        //update
        holder.Dialog_Fix_ProductType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateLoaiSach(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Data_Code_ProductType, Data_Name_ProductType, Data_Quantity_ProductType;
        ImageView Data_Image_ProductType;
        ImageButton Dialog_Fix_ProductType;
        RelativeLayout all_item_m_ProductT;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Data_Code_ProductType = itemView.findViewById(R.id.Data_Code_ProductType);
            Data_Name_ProductType = itemView.findViewById(R.id.Data_Name_ProductType);
            Data_Quantity_ProductType = itemView.findViewById(R.id.Data_Quantity_ProductType);
            Data_Image_ProductType = itemView.findViewById(R.id.Data_Image_ProductType);
            Dialog_Fix_ProductType = itemView.findViewById(R.id.Dialog_Fix_ProductType);
            all_item_m_ProductT = itemView.findViewById(R.id.all_item_m_ProductT);
        }
    }


    private String countProduct(String maLSP) {
        ArrayList<SanPham> listsp = new SanPhamDAO(context).get_SP();
        int dem = 0;
        for (SanPham sp: listsp) {
            dem += sp.getMaLSP().equals(maLSP) ? 1 : 0;
        }
        return String.valueOf(dem);
    }

    //Hàm update Loai San Pham
    public void updateLoaiSach(int i){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view1 = inflater.inflate(R.layout.ui_dialog_fix_product_type, null);
        builder.setView(view1);

        EditText EdtFix_Code_ProductType = view1.findViewById(R.id.EdtFix_Code_ProductType);
        EditText EdtFix_Name_ProductType = view1.findViewById(R.id.EdtFix_Name_ProductType);
        Button btnUpdate = view1.findViewById(R.id.Btn_Fix_ProductType);

        EdtFix_Code_ProductType.setText(list.get(i).getMaLSP());
        EdtFix_Code_ProductType.setEnabled(false);
        EdtFix_Name_ProductType.setText(list.get(i).getTen());

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.fill_radius_16));
        alertDialog.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO(context);
                loaiSanPhamDAO.update_LSP(list.get(i).getMaLSP(),list.get(i).getAnh(), list.get(i).getTen());
                list.set(i, new LoaiSanPham(EdtFix_Code_ProductType.getText().toString(), list.get(i).getAnh() , EdtFix_Name_ProductType.getText().toString()));
                notifyDataSetChanged();
                alertDialog.dismiss();
            }
        });
    }


}
