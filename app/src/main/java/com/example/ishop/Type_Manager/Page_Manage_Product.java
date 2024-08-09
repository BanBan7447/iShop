package com.example.ishop.Type_Manager;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishop.Adapter.DonHangAdapter;
import com.example.ishop.Adapter.SanPhamManageAdapter;
import com.example.ishop.DAO.SanPhamDAO;
import com.example.ishop.Model.SanPham;
import com.example.ishop.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Page_Manage_Product extends AppCompatActivity {
    private TextView Data_Name_ProductType;
    private RecyclerView rcv;
    private ImageView Icon_Back;
    private FloatingActionButton FloatBtn_Add_Product;
    private SanPhamDAO sanPhamDAO;
    private String maLSP;
    private SearchView searchView;
    private SanPhamManageAdapter sanPhamManageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_page_manage_product);

        Icon_Back = findViewById(R.id.Icon_Back);
        Data_Name_ProductType = findViewById(R.id.Data_Name_ProductType);
        rcv = findViewById(R.id.rcv_list_product_M);
        FloatBtn_Add_Product = findViewById(R.id.FloatBtn_Add_Product);
        searchView = findViewById(R.id.Search_Product);

        Bundle b = getIntent().getExtras();
        maLSP = b.getString("maLSP");
        String tenLSP = b.getString("tenLSP");

        Data_Name_ProductType.setText(tenLSP);

        loaddata();

        timkiem();

        FloatBtn_Add_Product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Page_Manage_Product.this, Page_Add_Product.class);
                Bundle b = new Bundle();
                b.putString("maLSP", maLSP);
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

    private void loaddata() {
        ArrayList<SanPham> list = new ArrayList<>();
        ArrayList<SanPham> listsp = new ArrayList<>();
        sanPhamDAO = new SanPhamDAO(this);
        list = sanPhamDAO.get_SP();
        for (SanPham sp : list) {
            if (sp.getMaLSP().equals(maLSP)) {
                listsp.add(sp);
            }
        }
         sanPhamManageAdapter = new SanPhamManageAdapter(this, listsp);
        rcv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcv.setAdapter(sanPhamManageAdapter);
    }

    private void timkiem() {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                sanPhamManageAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                sanPhamManageAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loaddata();
    }
}