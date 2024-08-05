package com.example.ishop.Activity_Manage;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishop.Adapter.CTDHAdapter;
import com.example.ishop.DAO.CTDHDAO;
import com.example.ishop.DAO.DonHangDAO;
import com.example.ishop.DAO.HoaDonDAO;
import com.example.ishop.DAO.KhachHangDAO;
import com.example.ishop.DAO.NhanVienDAO;
import com.example.ishop.DAO.SanPhamDAO;
import com.example.ishop.Model.CTDH;
import com.example.ishop.Model.DonHang;
import com.example.ishop.Model.HoaDon;
import com.example.ishop.Model.KhachHang;
import com.example.ishop.Model.NhanVien;
import com.example.ishop.Model.SanPham;
import com.example.ishop.R;

import java.util.ArrayList;

public class Page_Detail_Bill extends AppCompatActivity {
    private ImageView Icon_Back;
    private TextView Data_Code_Bill, Data_Code_Orders, Data_Date_Orders, Data_Code_Employee, Data_Name_Employee, Data_Customer_Name;
    private TextView Data_Customer_SDT, Data_Customer_Location, Data_Sum_Quantity_Product, Data_Total_Price_Orders, Data_Delivery_Price, Data_Total_Price;
    private RecyclerView rcv;
    private String maHD;
    private HoaDonDAO hoaDonDAO;
    private HoaDon hoaDon;
    private DonHangDAO donHangDAO;
    private DonHang donHang;
    private NhanVienDAO nhanVienDAO;
    private NhanVien nhanVien;
    private KhachHangDAO khachHangDAO;
    private KhachHang khachHang;
    private CTDHDAO ctdhdao;
    private CTDHAdapter ctdhAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_page_detail_bill);

        Data_Sum_Quantity_Product = findViewById(R.id.Data_Sum_Quantity_Product);
        Data_Total_Price_Orders = findViewById(R.id.Data_Total_Price_Orders);
        Data_Customer_Location = findViewById(R.id.Data_Customer_Location);
        Data_Delivery_Price = findViewById(R.id.Data_Delivery_Price);
        Data_Code_Employee = findViewById(R.id.Data_Code_Employee);
        Data_Name_Employee = findViewById(R.id.Data_Name_Employee);
        Data_Customer_Name = findViewById(R.id.Data_Customer_Name);
        Data_Customer_SDT = findViewById(R.id.Data_Customer_SDT);
        Data_Total_Price = findViewById(R.id.Data_Total_Price);
        Data_Code_Orders = findViewById(R.id.Data_Code_Orders);
        Data_Date_Orders = findViewById(R.id.Data_Date_Orders);
        Data_Code_Bill = findViewById(R.id.Data_Code_Bill);
        rcv = findViewById(R.id.rcv_list_product_m);
        Icon_Back = findViewById(R.id.Icon_Back);
        ctdhdao = new CTDHDAO(this);
        hoaDonDAO = new HoaDonDAO(this);
        donHangDAO = new DonHangDAO(this);
        nhanVienDAO = new NhanVienDAO(this);
        khachHangDAO = new KhachHangDAO(this);

        showInfo();

        totalPrice();

        Icon_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    private void showInfo() {
        Bundle b = getIntent().getExtras();
        maHD = b.getString("maHD","");
        hoaDon = hoaDonDAO.get_HD(maHD);
        donHang = donHangDAO.get_DH(hoaDon.getMaDH());
        nhanVien = nhanVienDAO.get_NV2(hoaDon.getMaNV());
        khachHang = khachHangDAO.get_KH(donHang.getMaKH());
        Data_Code_Bill.setText(hoaDon.getMaHD());
        Data_Code_Orders.setText(hoaDon.getMaDH());
        Data_Date_Orders.setText(hoaDon.getNgay());
        Data_Code_Employee.setText(nhanVien.getMa());
        Data_Name_Employee.setText(nhanVien.getTen());
        Data_Customer_Name.setText(khachHang.getTen());
        Data_Customer_SDT.setText(khachHang.getSdt());
        Data_Customer_Location.setText(khachHang.getDiachi());
    }

    private void totalPrice() {
        ArrayList<CTDH> listct = ctdhdao.get_CTDH(donHang.getMaDH());
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

        long ttDH = dgSp + deliveryPrice;

        Data_Sum_Quantity_Product.setText(slSp);
        Data_Total_Price_Orders.setText(changePrice(dgSp));
        Data_Total_Price.setText(changePrice(ttDH));
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
}