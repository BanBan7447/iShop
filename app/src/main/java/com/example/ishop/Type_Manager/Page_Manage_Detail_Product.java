package com.example.ishop.Type_Manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ishop.DAO.SanPhamDAO;
import com.example.ishop.Model.SanPham;
import com.example.ishop.R;

public class Page_Manage_Detail_Product extends AppCompatActivity {
    private ImageView Icon_Back, Data_Image_Product;
    private TextView Data_TitleName_Product, Data_Code_Product, Data_Name_Product, Data_Price_Product, Data_Description_Product, Data_Inventory_Product;
    private Button Btn_Page_Fix_Product;
    private SanPhamDAO sanPhamDAO;
    private SanPham sanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_page_detail_manage_product);

        Icon_Back = findViewById(R.id.Icon_Back);
        Data_TitleName_Product = findViewById(R.id.Data_TitleName_Product);
        Data_Image_Product = findViewById(R.id.Data_Image_Product);
        Data_Code_Product = findViewById(R.id.Data_Code_Product);
        Data_Name_Product = findViewById(R.id.Data_Name_Product);
        Data_Price_Product = findViewById(R.id.Data_Price_Product);
        Data_Description_Product = findViewById(R.id.Data_Description_Product);
        Data_Inventory_Product = findViewById(R.id.Data_Inventory_Product);
        Btn_Page_Fix_Product = findViewById(R.id.Btn_Page_Fix_Product);

        showInfo();

        Btn_Page_Fix_Product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Page_Fix_Product.class);
                Bundle b = new Bundle();
                b.putString("maSP", sanPham.getMaSP());
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

    private void showInfo() {
        sanPhamDAO = new SanPhamDAO(this);
        Bundle b = getIntent().getExtras();
        sanPham = sanPhamDAO.get_SPP(b.getString("maSP", ""));
        Data_TitleName_Product.setText(sanPham.getTen());
        int id = this.getResources().getIdentifier(sanPham.getAnh(), "mipmap", getPackageName());
        Data_Image_Product.setImageResource(id);
        Data_Code_Product.setText(sanPham.getMaSP());
        Data_Name_Product.setText(sanPham.getTen());
        Data_Price_Product.setText(changePrice(sanPham.getGia()));
        Data_Description_Product.setText(sanPham.getMota());
        Data_Inventory_Product.setText(String.valueOf(sanPham.getSoluong()));
    }

    //hàm dấu chấm vào giá
    private String changePrice(int n) {
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
        showInfo();
    }
}