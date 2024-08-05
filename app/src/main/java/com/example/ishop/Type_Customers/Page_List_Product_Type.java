package com.example.ishop.Type_Customers;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishop.Adapter.LoaiSPAdapter_User2;
import com.example.ishop.DAO.LoaiSanPhamDAO;
import com.example.ishop.Model.LoaiSanPham;
import com.example.ishop.R;

import java.util.ArrayList;

public class Page_List_Product_Type extends AppCompatActivity {
    private RecyclerView rcv_list_productType;
    private ImageView Icon_Back;
    private LoaiSanPhamDAO loaiSanPhamDAO;
    private LoaiSPAdapter_User2 loaiSanPhameAdapterUser;
    private ArrayList<LoaiSanPham> listlsp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_page_list_product_type);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rcv_list_productType = findViewById(R.id.rcv_list_productType);
        Icon_Back = findViewById(R.id.Icon_Back_ListType_User);

        //show list type
        loadData();

        //back
        Icon_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    //load data
    private void loadData() {
        loaiSanPhamDAO = new LoaiSanPhamDAO(this);
        listlsp = loaiSanPhamDAO.get_LSP();
        loaiSanPhameAdapterUser = new LoaiSPAdapter_User2(this, listlsp);
        rcv_list_productType.setLayoutManager(new GridLayoutManager(this, 2));
        rcv_list_productType.setAdapter(loaiSanPhameAdapterUser);
    }
}