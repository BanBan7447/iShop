package com.example.ishop.Type_Manager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishop.DAO.SanPhamDAO;
import com.example.ishop.Model.SanPham;
import com.example.ishop.R;

public class Page_Add_Product extends AppCompatActivity {
    private ImageView Icon_Back;
    private TextView EdtAdd_Code_Product, EdtAdd_Name_Product, EdtAdd_Price_Product, EdtAdd_Description_Product;
    private Button Btn_Add_Product;
    private SanPhamDAO sanPhamDAO;
    private String maLSP = "IP27"; //gan tam maLSP IP27
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_page_add_product);

        Icon_Back = findViewById(R.id.Icon_Back);
        EdtAdd_Code_Product = findViewById(R.id.EdtAdd_Code_Product);
        EdtAdd_Name_Product = findViewById(R.id.EdtAdd_Name_Product);
        EdtAdd_Price_Product = findViewById(R.id.EdtAdd_Price_Product);
        EdtAdd_Description_Product = findViewById(R.id.EdtAdd_Description_Product);
        Btn_Add_Product = findViewById(R.id.Btn_Add_Product);

        addProduct();
        
        Icon_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addProduct() {
        sanPhamDAO = new SanPhamDAO(this);
        EdtAdd_Code_Product.setText(sanPhamDAO.createIdSP(maLSP));
        EdtAdd_Code_Product.setEnabled(false);

        Btn_Add_Product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = EdtAdd_Name_Product.getText().toString();
                String gia = EdtAdd_Price_Product.getText().toString();
                String mota = EdtAdd_Description_Product.getText().toString();

                if (ten.isEmpty() || mota.isEmpty() || gia.isEmpty()) {
                    Toast.makeText(Page_Add_Product.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    int gian = 0;
                    try {
                        gian = Integer.parseInt(gia);
                    } catch (NumberFormatException e) {
                        gian = 0;
                        Toast.makeText(getApplicationContext(), "Không phải là số nguyên hợp lệ", Toast.LENGTH_SHORT).show();
                    }
                    if (gian>0){
                        sanPhamDAO.add_SP("", ten, gian, mota, 1, maLSP);
                        Toast.makeText(Page_Add_Product.this, "Done", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(Page_Add_Product.this, "Nhập giá >0", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}