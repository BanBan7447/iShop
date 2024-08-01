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
import android.widget.TextView;
import com.example.ishop.Database.DBHelper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ishop.DAO.KhachHangDAO;
import com.example.ishop.Database.DBHelper;
import com.example.ishop.Model.KhachHang;
import com.example.ishop.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class FragPage_Profile extends Fragment {
    private DBHelper dbHelper;
    ShapeableImageView Data_Image_Customer;
    private KhachHang khachHang;
    private ArrayList<KhachHang> list;
    TextView Data_Name_Customer,Data_SDT_Customer,Data_Email_Customer,Data_Location_Customer,Data_Quantity_Cart;
    private int KhachHang;
    private int sKhachHang;
    private int eKhachHang;
    private int lcKhachHang;
    private int pKhachHang;
    public FragPage_Profile() {
        dbHelper = new DBHelper(getContext());
    }
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.ui_frag_page_profile, container, false);
        Data_Name_Customer =  view.findViewById(R.id.Data_Name_Customer);
        Data_SDT_Customer =  view.findViewById(R.id.Data_SDT_Customer);
        Data_Email_Customer =  view.findViewById(R.id.Data_Email_Customer);
        Data_Location_Customer =  view.findViewById(R.id.Data_Location_Customer);
        Data_Quantity_Cart =  view.findViewById(R.id.Data_Quantity_Cart);

        KhachHangDAO khachHangDAO = new KhachHangDAO(this.getContext());

        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String email = pref.getString("Email","");
        ArrayList<KhachHang> list = khachHangDAO.gettTKH(email);
      //  String a = "";
        Data_Name_Customer.setText(list.get(KhachHang).getTenKH());
        Data_SDT_Customer.setText(list.get(sKhachHang).getSdtKH());
        Data_Email_Customer.setText(list.get(eKhachHang).getEmailKH());
        Data_Location_Customer.setText(list.get(lcKhachHang).getDiachiKH());
        String olPass = list.get(pKhachHang).getMatkhauKH();

//
        return view;
    }


}