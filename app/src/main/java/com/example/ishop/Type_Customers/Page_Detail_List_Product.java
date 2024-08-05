package com.example.ishop.Type_Customers;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishop.Adapter.LoaiSPAdapter_User1;
import com.example.ishop.Adapter.SanPhamAdapter;
import com.example.ishop.DAO.SanPhamDAO;
import com.example.ishop.Model.SanPham;
import com.example.ishop.R;

import java.util.ArrayList;

public class Page_Detail_List_Product extends AppCompatActivity {
    private SanPhamDAO sanPhamDAO;
    private SanPhamAdapter sanPhamAdapter;
    private RecyclerView rcv;
    private ArrayList<SanPham> list;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_page_detail_list_product);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.statusBarColor));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        ImageView Icon_Back_Product_User =findViewById(R.id.Icon_Back_Product_User);
        TextView Name_Product_Type_User = findViewById(R.id.Name_Product_Type_User);
        rcv = findViewById(R.id.rcv_list_product_user);

        //get intent
        Bundle b = getIntent().getExtras();
        String maLSP = b.getString("maLSP");
        Name_Product_Type_User.setText(b.getString("tenLSP"));

        //show product of type
        sanPhamDAO = new SanPhamDAO(this);
        list = sanPhamDAO.get_SP();
        ArrayList<SanPham> list1 = new ArrayList<>();
        for (SanPham sp:list){
            if (sp.getMaLSP().equals(maLSP))
                list1.add(sp);
        }

        sanPhamAdapter = new SanPhamAdapter(this, list1);
        rcv.setLayoutManager(new GridLayoutManager(this, 2));
        rcv.setAdapter(sanPhamAdapter);
        rcv.scrollToPosition(0);

        //back;
        Icon_Back_Product_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        timkiem();
    }

    private void timkiem() {
        searchView = findViewById(R.id.Search_Orders);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                sanPhamAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sanPhamAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }
}