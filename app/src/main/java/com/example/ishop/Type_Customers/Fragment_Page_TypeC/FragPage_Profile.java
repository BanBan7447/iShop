package com.example.ishop.Type_Customers.Fragment_Page_TypeC;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ishop.Activity_Manage.Page_History_Orders;
import com.example.ishop.DAO.DonHangDAO;
import com.example.ishop.Database.DBHelper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ishop.DAO.KhachHangDAO;
import com.example.ishop.Database.DBHelper;
import com.example.ishop.Model.DonHang;
import com.example.ishop.Model.KhachHang;
import com.example.ishop.R;
import com.example.ishop.Type_Customers.Page_Fix_Profile;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class FragPage_Profile extends Fragment {
    private ImageButton Btn_MovePage_Fix_Profile;
    private ImageView Data_Image_Customer, Page_Show_History_Orders;
    private ArrayList<KhachHang> list;
    private TextView Data_Name_Customer,Data_SDT_Customer,Data_Email_Customer,Data_Location_Customer,Data_Quantity_Cart;
    private KhachHang kh;


    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.ui_frag_page_profile, container, false);
        Data_Name_Customer =  view.findViewById(R.id.Data_Name_Customer);
        Data_SDT_Customer =  view.findViewById(R.id.Data_SDT_Customer);
        Data_Email_Customer =  view.findViewById(R.id.Data_Email_Customer);
        Data_Location_Customer =  view.findViewById(R.id.Data_Location_Customer);
        Data_Quantity_Cart =  view.findViewById(R.id.Data_Quantity_Cart);
        Data_Image_Customer =  view.findViewById(R.id.Data_Image_Customer);
        Page_Show_History_Orders =  view.findViewById(R.id.Page_Show_History_Orders);
        Btn_MovePage_Fix_Profile =  view.findViewById(R.id.Btn_MovePage_Fix_Profile);


        KhachHangDAO khachHangDAO = new KhachHangDAO(this.getContext());

        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String email = pref.getString("Email","duytham96@gmail.com");
        kh = khachHangDAO.gettTKH(email);
        Data_Name_Customer.setText(kh.getTen());
        Data_SDT_Customer.setText(kh.getSdt());
        Data_Email_Customer.setText(kh.getEmail());
        Data_Location_Customer.setText(kh.getDiachi());

        int id = getContext().getResources().getIdentifier(kh.getAnh(), "mipmap", getContext().getPackageName());
        Data_Image_Customer.setImageResource(id);
        String olPass = kh.getMatkhau();

        //so don da dat
        showDonebill();

        //show lich su dat hang
        Page_Show_History_Orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Page_History_Orders.class));
            }
        });

        //fix profile
        Btn_MovePage_Fix_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Page_Fix_Profile.class));
            }
        });
        return view;
    }

    private void showDonebill() {
        int dem = 0;
        DonHangDAO donHangDAO = new DonHangDAO(getContext());
        ArrayList<DonHang> list = donHangDAO.get_ListDH();
        for (DonHang dh : list) {
            if (dh.getMaKH().equals(kh.getMa()) && dh.getTrangthai().equals("Đã xử lý")){
                dem++;
            }
        }
        Data_Quantity_Cart.setText(String.valueOf(dem));
    }
}