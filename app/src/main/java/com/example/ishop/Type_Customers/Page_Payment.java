package com.example.ishop.Type_Customers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishop.Adapter.CTDHAdapter;
import com.example.ishop.DAO.CTDHDAO;
import com.example.ishop.DAO.DonHangDAO;
import com.example.ishop.DAO.KhachHangDAO;
import com.example.ishop.Model.DonHang;
import com.example.ishop.Model.KhachHang;
import com.example.ishop.Model.SanPham;
import com.example.ishop.R;
import com.example.ishop.Utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Page_Payment extends AppCompatActivity {
    private ImageView ic_back;
    private ImageButton BtnFix_Info_Customer_Cart;
    private RecyclerView rcv;
    private Button Btn_Payment;
    private TextView Data_Code_Orders, Data_Date_Orders, Data_Customer_Name, Data_Customer_SDT, Data_Customer_Location, Data_Code_Bill;
    private TextView Data_Sum_Quantity_Product, Data_Total_Price_Orders, Data_Delivery_Price, Data_Total_Price;
    private DonHangDAO donHangDAO;
    private CTDHDAO ctdhdao;
    private CTDHAdapter ctdhAdapter;
    private KhachHang khachHang;
    private String ngayDH, maDH;
    long ttHD;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_page_payment);

        Data_Code_Orders = findViewById(R.id.Data_Code_Orders);
        Data_Date_Orders = findViewById(R.id.Data_Date_Orders);
        Data_Customer_Name = findViewById(R.id.Data_Customer_Name);
        Data_Customer_SDT = findViewById(R.id.Data_Customer_SDT);
        Data_Customer_Location = findViewById(R.id.Data_Customer_Location);
        BtnFix_Info_Customer_Cart = findViewById(R.id.BtnFix_Info_Customer_Cart);
        Data_Sum_Quantity_Product = findViewById(R.id.Data_Sum_Quantity_Product);
        Data_Total_Price_Orders = findViewById(R.id.Data_Total_Price_Orders);
        Data_Delivery_Price = findViewById(R.id.Data_Delivery_Price);
        Data_Total_Price = findViewById(R.id.Data_Total_Price);
        Btn_Payment = findViewById(R.id.Btn_Payment);
        ic_back = findViewById(R.id.Icon_Back);
        rcv = findViewById(R.id.rcv_list_product_u);

        donHangDAO = new DonHangDAO(this);
        ctdhdao = new CTDHDAO(this);

        showInfoKH();
        showInfo();
        payment();

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    private void showInfo() {
        //get today
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        ngayDH = formatter.format(today);
        maDH = donHangDAO.createIdDH().isEmpty() ? "" : donHangDAO.createIdDH();
        Data_Code_Orders.setText(maDH);
        Data_Date_Orders.setText(ngayDH);

        ctdhAdapter = new CTDHAdapter(this, Utils.listGioHang);
        rcv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcv.setAdapter(ctdhAdapter);

        String slSp = ctdhAdapter.getSL();
        long dgSp = ctdhAdapter.getTT();
        String phiGh = Data_Delivery_Price.getText().toString();
        //Kiểm tra chuỗi có rỗng không
        int deliveryPrice = 0;
        try {
            deliveryPrice = Integer.parseInt(phiGh.isEmpty() ? "0" : phiGh);
        } catch (NumberFormatException e) {
        }

        ttHD = dgSp + deliveryPrice;
        Data_Sum_Quantity_Product.setText(slSp);
        Data_Total_Price_Orders.setText(changePrice(dgSp));
        Data_Total_Price.setText(changePrice(ttHD));
    }

    private void showInfoKH() {
        //get KH
        SharedPreferences pref = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String email = pref.getString("Email", "duytham96@gmail.com");
        KhachHangDAO khachHangDAO = new KhachHangDAO(this);
        khachHang = khachHangDAO.gettTKH(email);
        Data_Customer_Name.setText(khachHang.getTen());
        Data_Customer_SDT.setText(khachHang.getSdt());
        Data_Customer_Location.setText(khachHang.getDiachi());
        BtnFix_Info_Customer_Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Page_Fix_Profile.class));
            }
        });
    }

    private void payment() {
        Btn_Payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donHangDAO.add_DH(khachHang.getMa(), ngayDH, "Chưa xử lý", "", ttHD);
                for (SanPham sp : Utils.listGioHang) {
                    ctdhdao.add_CTDH(maDH, sp.getMaSP(), sp.getSoluong(),sp.getGia());
                }
                Utils.listGioHang.clear();
                finish();
            }
        });
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

    @Override
    protected void onResume() {
        super.onResume();
        showInfoKH();
    }
}