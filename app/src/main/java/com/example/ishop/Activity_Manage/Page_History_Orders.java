package com.example.ishop.Activity_Manage;

import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishop.Adapter.HoaDonAdapter_U;
import com.example.ishop.DAO.DonHangDAO;
import com.example.ishop.DAO.HoaDonDAO;
import com.example.ishop.DAO.KhachHangDAO;
import com.example.ishop.Model.DonHang;
import com.example.ishop.Model.HoaDon;
import com.example.ishop.R;

import java.util.ArrayList;

public class Page_History_Orders extends AppCompatActivity {
    private RecyclerView rcv;
    private HoaDonDAO hoaDonDAO;
    private HoaDonAdapter_U hoaDonAdapter;
    private DonHangDAO donHangDAO;
    private KhachHangDAO khachHangDAO;
    private ImageView Icon_Back;
    private SearchView searchView;
    private String maKH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_page_history_orders);

        rcv = findViewById(R.id.rcv_list_bill_history);
        Icon_Back = findViewById(R.id.Icon_Back);
        hoaDonDAO = new HoaDonDAO(this);
        donHangDAO = new DonHangDAO(this);
        khachHangDAO = new KhachHangDAO(this);

        Bundle b = getIntent().getExtras();
        if (b != null){
            maKH = b.getString("maKH", "");
        } else {
            maKH = "";
        }
        if (maKH.isEmpty()){
            SharedPreferences pref = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String email = pref.getString("Email","duytham96@gmail.com");
            maKH = khachHangDAO.gettTKH(email).getMa();
        }

        ArrayList<DonHang> listdh = donHangDAO.get_ListDH();
        ArrayList<HoaDon> listhd = hoaDonDAO.get_ListHD();
        ArrayList<HoaDon> list = new ArrayList<>();
        for (DonHang dh : listdh) {
            if (dh.getMaKH().equals(maKH) && dh.getTrangthai().equals("Đã xử lý")){
                for (HoaDon hd : listhd){
                    if (hd.getMaDH().equals(dh.getMaDH())){
                        list.add(hd);
                    }
                }
            }
        }

        hoaDonAdapter = new HoaDonAdapter_U(this, list);
        rcv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcv.setAdapter(hoaDonAdapter);

        searchHD();

        Icon_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void searchHD() {
        searchView = findViewById(R.id.Search_Orders);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                hoaDonAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                hoaDonAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }
}