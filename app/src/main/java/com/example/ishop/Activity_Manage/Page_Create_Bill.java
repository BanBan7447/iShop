package com.example.ishop.Activity_Manage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishop.Adapter.CTDHAdapter;
import com.example.ishop.DAO.CTDHDAO;
import com.example.ishop.DAO.DonHangDAO;
import com.example.ishop.DAO.HoaDonDAO;
import com.example.ishop.DAO.KhachHangDAO;
import com.example.ishop.DAO.NhanVienDAO;
import com.example.ishop.DAO.QuanLyDAO;
import com.example.ishop.DAO.SanPhamDAO;
import com.example.ishop.Model.CTDH;
import com.example.ishop.Model.DonHang;
import com.example.ishop.Model.KhachHang;
import com.example.ishop.Model.NhanVien;
import com.example.ishop.Model.SanPham;
import com.example.ishop.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Page_Create_Bill extends AppCompatActivity {
    private ImageView ic_back;
    private RecyclerView rcv;
    private Button Btn_Create_Bill;
    private TextView Data_Code_Orders, Data_Date_Orders, Data_Name_Employee, Data_Customer_Name, Data_Customer_SDT, Data_Customer_Location, Data_Code_Bill;
    private TextView Data_Sum_Quantity_Product, Data_Total_Price_Orders, Data_Delivery_Price, Data_Total_Price;
    private DonHangDAO donHangDAO;
    private DonHang donHang;
    private HoaDonDAO hoaDonDAO;
    private NhanVienDAO nhanVienDAO;
    private NhanVien nhanVien;
    private CTDHDAO ctdhdao;
    private CTDHAdapter ctdhAdapter;
    private QuanLyDAO quanLyDAO;
    private int codeManage;
    private String maDH, status = "";
    private long ttHD;

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

        Data_Sum_Quantity_Product = findViewById(R.id.Data_Sum_Quantity_Product);
        Data_Total_Price_Orders = findViewById(R.id.Data_Total_Price_Orders);
        Data_Delivery_Price = findViewById(R.id.Data_Delivery_Price);
        Data_Total_Price = findViewById(R.id.Data_Total_Price);

        ic_back = findViewById(R.id.Icon_Back_CreateBill);
        rcv = findViewById(R.id.rcv_create_Bill);
        Btn_Create_Bill = findViewById(R.id.Btn_Create_Bill);

        donHangDAO = new DonHangDAO(this);
        hoaDonDAO = new HoaDonDAO(this);
        nhanVienDAO = new NhanVienDAO(this);
        ctdhdao = new CTDHDAO(this);
        quanLyDAO = new QuanLyDAO(this);

        //get data tu manage Order
        Bundle b = getIntent().getExtras();
        codeManage = b.getInt("numberManage");
        maDH = b.getString("maDH");
        donHang = donHangDAO.get_DH(maDH);

        //get NV
        SharedPreferences pref = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String email = pref.getString("Email", "");

        nhanVien = nhanVienDAO.get_NV(email) == null ? quanLyDAO.get_QL(email) : nhanVienDAO.get_NV(email);

        showInfo(); //show info khach hang, nhan vien xu ly, tt don hang

        showListDetailProduct(); // show list ctdh, thong tin ttoan

        createBill();  //Btn Create Bill

        back();
    }


    private void showInfo() {
        KhachHangDAO khachHangDAO = new KhachHangDAO(this);
        KhachHang khachHang = khachHangDAO.get_KH(donHang.getMaKH());
        String tenNV;
        Data_Customer_Name.setText(khachHang.getTen());
        Data_Customer_SDT.setText(khachHang.getSdt());
        Data_Customer_Location.setText(khachHang.getDiachi());
        Data_Code_Bill.setText(hoaDonDAO.createIdHD().isEmpty() ? "" : hoaDonDAO.createIdHD());
        Data_Code_Orders.setText(donHang.getMaDH());
        Data_Date_Orders.setText(donHang.getNgay());
        if (nhanVien != null) {
            tenNV = nhanVien.getTen();
        } else {
            tenNV = "";
        }
        Data_Name_Employee.setText(tenNV);

        //nếu đơn hàng đã hoặc đang xử ly
        //thì ko cho xuất bill
        if (codeManage != 0) {
            Btn_Create_Bill.setEnabled(false);
        } else {
            //set trang thai sang: dang xu ly
            donHangDAO.update_DH(donHang.getMaDH(), donHang.getMaKH(), donHang.getNgay(), "Đang xử lý", donHang.getMaNV(), donHang.getThanhtien());

        }

    }

    private void showListDetailProduct() {
        ArrayList<CTDH> listct = ctdhdao.get_CTDH(maDH);
        ArrayList<SanPham> listsp = new ArrayList<>();
        for (CTDH ctdh : listct) {
            SanPham sp = new SanPhamDAO(this).get_SPP(ctdh.getMaSP());
            sp.setSoluong(ctdh.getSoluong());
            listsp.add(sp);
        }

        ctdhAdapter = new CTDHAdapter(this, listsp);
        rcv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcv.setAdapter(ctdhAdapter);

        //thong tin ttoan
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

    private void createBill() {
        Btn_Create_Bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get today
                Date today = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String ngayHD = formatter.format(today);

                //create HD
                if (ttHD < 0) {
                    ttHD = 0;
                }

                //check nhanVien ! null
                String maNV;
                if (nhanVien != null) {
                    maNV = nhanVien.getMa();
                } else {
                    maNV = "";
                }
                hoaDonDAO.add_HD(maDH, ngayHD, maNV, ttHD);
                donHangDAO.update_DH(donHang.getMaDH(), donHang.getMaKH(), donHang.getNgay(), "Đã xử lý", donHang.getMaNV(), donHang.getThanhtien());
                status = "Đã xử lý";
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
    protected void onDestroy() {
        super.onDestroy();
        if (codeManage == 0 && status.isEmpty()) {
            donHangDAO.update_DH(donHang.getMaDH(), donHang.getMaKH(), donHang.getNgay(), "Chưa xử lý", donHang.getMaNV(), donHang.getThanhtien());
        }
    }
}