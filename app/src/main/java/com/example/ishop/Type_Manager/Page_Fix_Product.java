package com.example.ishop.Type_Manager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ishop.DAO.SanPhamDAO;
import com.example.ishop.Model.SanPham;
import com.example.ishop.R;

public class Page_Fix_Product extends AppCompatActivity {
    private ImageView Icon_Back;
    private EditText EdtFix_Code_Product, EdtFix_Name_Product, EdtFix_Price_Product, EdtFix_Description_Product;
    private Button Btn_Fix_Product;
    private SanPhamDAO sanPhamDAO;
    private SanPham sanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_page_fix_product);

        Icon_Back = findViewById(R.id.Icon_Back);
        EdtFix_Code_Product = findViewById(R.id.EdtFix_Code_Product);
        EdtFix_Name_Product = findViewById(R.id.EdtFix_Name_Product);
        EdtFix_Price_Product = findViewById(R.id.EdtFix_Price_Product);
        EdtFix_Description_Product = findViewById(R.id.EdtFix_Description_Product);
        Btn_Fix_Product = findViewById(R.id.Btn_Fix_Product);
        sanPhamDAO = new SanPhamDAO(this);

        Bundle b = getIntent().getExtras();
        String maSP = b.getString("maSP");
        sanPham = sanPhamDAO.get_SPP(maSP);

        EdtFix_Code_Product.setText(sanPham.getMaSP());
        EdtFix_Code_Product.setEnabled(false);
        EdtFix_Name_Product.setText(sanPham.getTen());
        EdtFix_Price_Product.setText(String.valueOf(sanPham.getGia()));
        EdtFix_Description_Product.setText(sanPham.getMota());

        fixProduct();

        Icon_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void fixProduct() {
        Btn_Fix_Product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = EdtFix_Name_Product.getText().toString();
                String gia = EdtFix_Price_Product.getText().toString();
                String mota = EdtFix_Description_Product.getText().toString();

                if (ten.isEmpty() || mota.isEmpty() || gia.isEmpty()) {
                    Toast.makeText(Page_Fix_Product.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    int gian = 0;
                    try {
                        gian = Integer.parseInt(gia);
                    } catch (NumberFormatException e) {
                        gian = 0;
                        Toast.makeText(getApplicationContext(), "Không phải là số nguyên hợp lệ", Toast.LENGTH_SHORT).show();
                    }
                    if (gian>0){
                        sanPhamDAO.update_SP(sanPham.getMaSP(), sanPham.getAnh(), ten, gian, mota, sanPham.getSoluong(), sanPham.getMaLSP());
                        Toast.makeText(Page_Fix_Product.this, "Done", Toast.LENGTH_SHORT).show();

                        finish();
                    }else {
                        Toast.makeText(Page_Fix_Product.this, "Nhập giá >0", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}