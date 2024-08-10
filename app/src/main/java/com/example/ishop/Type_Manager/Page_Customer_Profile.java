package com.example.ishop.Type_Manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishop.Activity_Manage.Page_History_Orders;
import com.example.ishop.DAO.DonHangDAO;
import com.example.ishop.DAO.KhachHangDAO;
import com.example.ishop.Model.DonHang;
import com.example.ishop.Model.KhachHang;
import com.example.ishop.R;

import java.util.ArrayList;

public class Page_Customer_Profile extends AppCompatActivity {
    private ImageView Icon_Back, Data_Image_Customer;
    private RelativeLayout Btn_Page_History_Orders;
    private KhachHang khachHang;
    private ArrayList<KhachHang> list;
    private TextView Data_Name_Customer,Data_SDT_Customer,Data_Email_Customer,Data_Location_Customer,Data_Quantity_Cart;
    private KhachHangDAO khachHangDAO;
    private KhachHang kh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_page_customer_profile);

        Icon_Back = findViewById(R.id.Icon_Back);
        Data_Name_Customer =  findViewById(R.id.Data_Name_Customer);
        Data_SDT_Customer =  findViewById(R.id.Data_SDT_Customer);
        Data_Email_Customer =  findViewById(R.id.Data_Email_Customer);
        Data_Location_Customer =  findViewById(R.id.Data_Location_Customer);
        Data_Quantity_Cart =  findViewById(R.id.Data_Quantity_Cart);
        Data_Image_Customer =  findViewById(R.id.Data_Image_Customer);
        Btn_Page_History_Orders =  findViewById(R.id.Btn_Page_History_Orders);

        Bundle b = getIntent().getExtras();
        String maKH = b.getString("maKH", "IC1014");
        khachHangDAO = new KhachHangDAO(this);
        kh = khachHangDAO.get_KH(maKH);

        int id =getResources().getIdentifier(kh.getAnh(), "mipmap", getPackageName());
        Data_Image_Customer.setImageResource(id);
        Data_Name_Customer.setText(kh.getTen());
        Data_SDT_Customer.setText(kh.getSdt());
        Data_Email_Customer.setText(kh.getEmail());
        Data_Location_Customer.setText(kh.getDiachi());


        //so don da dat
        showDonebill();

        //show lich su dat hang
        Btn_Page_History_Orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext() , Page_History_Orders.class);
                Bundle b = new Bundle();
                b.putString("maKH", kh.getMa());
                i.putExtras(b);
                startActivity(i);
            }
        });

        Icon_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showDonebill() {
        int dem = 0;
        DonHangDAO donHangDAO = new DonHangDAO(this);
        ArrayList<DonHang> list = donHangDAO.get_ListDH();
        for (DonHang dh : list) {
            if (dh.getMaKH().equals(kh.getMa()) && dh.getTrangthai().equals("Đã xử lý")){
                dem++;
            }
        }
        Data_Quantity_Cart.setText(String.valueOf(dem));
    }
}