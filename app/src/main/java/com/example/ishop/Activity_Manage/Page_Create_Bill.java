package com.example.ishop.Activity_Manage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishop.DAO.DonHangDAO;
import com.example.ishop.DAO.HoaDonDAO;
import com.example.ishop.DAO.KhachHangDAO;
import com.example.ishop.Model.DonHang;
import com.example.ishop.Model.KhachHang;
import com.example.ishop.R;

import java.util.ArrayList;

public class Page_Create_Bill extends AppCompatActivity {
    private ImageView ic_back;
    private RecyclerView rcv;
    private Button Btn_Create_Bill;
    private DonHangDAO donHangDAO;
    private DonHang donHang;
    private TextView Data_Code_Orders, Data_Date_Orders,Data_Name_Employee, Data_Customer_Name,Data_Customer_SDT, Data_Customer_Location, Data_Code_Bill;
    private int codeManage;
    private String maDH;
    private HoaDonDAO hoaDonDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_page_create_bill);

        Data_Code_Bill = findViewById(R.id.Data_Code_Bill);
        Data_Code_Orders = findViewById(R.id.Data_Code_Orders);
        Data_Date_Orders = findViewById(R.id.Data_Date_Orders);
        Data_Name_Employee = findViewById(R.id.Data_Name_Employee);
        Data_Customer_Name = findViewById(R.id.Data_Customer_Name);
        Data_Customer_SDT = findViewById(R.id.Data_Customer_SDT);
        Data_Customer_Location = findViewById(R.id.Data_Customer_Location);
        ic_back = findViewById(R.id.Icon_Back_CreateBill);
        Btn_Create_Bill = findViewById(R.id.Btn_Create_Bill);
        donHangDAO = new DonHangDAO(this);
        hoaDonDAO = new HoaDonDAO(this);
        rcv = findViewById(R.id.rcv_create_Bill);

        Bundle b = getIntent().getExtras();
        codeManage = b.getInt("numberManage");
        maDH = b.getString("maDH");
        donHang = donHangDAO.get_DH(maDH);

        showInfo(); //show info khach hang, nhan vien xu ly, cac loai ma

        //set trang thai sang: dang xu ly
        donHangDAO.update_DH(donHang.getMaDH(), donHang.getMaKH(), donHang.getNgay(), "Đang xử lý", donHang.getMaNV(), donHang.getThanhtien());

        createBill();  //Btn Create Bill

        back();
    }

    private void showInfo() {
        KhachHangDAO khachHangDAO = new KhachHangDAO(this);
        KhachHang khachHang = khachHangDAO.get_KH(donHang.getMaKH());
        Data_Customer_Name.setText(khachHang.getTen());
        Data_Customer_SDT.setText(khachHang.getSdt());
        Data_Customer_Location.setText(khachHang.getDiachi());
        Data_Code_Bill.setText(hoaDonDAO.createIdHD());
        Data_Code_Orders.setText(donHang.getMaDH());
        Data_Date_Orders.setText(donHang.getNgay());

        //nếu đơn hàng đã hoặc đang xử ly
        //thì ko cho xuất bill
        if (codeManage != 0) {
            Btn_Create_Bill.setEnabled(false);
        }
    }

    private void createBill() {
        Btn_Create_Bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cần hàm lấy ngày, getprefe bằng email cho ma nv,
                hoaDonDAO.add_HD(maDH, "today", "maNV", 10);
                finish();
            }
        });
    }

    private void back() {
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //back thi trang thai: chua xu ly
                if (codeManage == 0) {
                    donHangDAO.update_DH(donHang.getMaDH(), donHang.getMaKH(), donHang.getNgay(), "Chưa xử lý", donHang.getMaNV(), donHang.getThanhtien());
                }
                finish();
            }
        });
    }
}