package com.example.ishop.Type_Customers;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ishop.Model.SanPham;
import com.example.ishop.R;
import com.example.ishop.Utils.Utils;

public class Page_Detail_Product extends AppCompatActivity {
    private ImageView back, imag;
    private TextView ten, gia, sl, mota;
    private ImageButton up, down;
    private Button addToCart;
    private int dem = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_page_buy_product);

        back = findViewById(R.id.Icon_Back_detail_product);
        imag = findViewById(R.id.Data_Image_Product);
        ten = findViewById(R.id.Data_Name_Product);
        gia = findViewById(R.id.Data_Price_Product);
        sl = findViewById(R.id.Quantity_Product);
        mota = findViewById(R.id.Data_Description_Product);
        addToCart = findViewById(R.id.Btn_AddToCart);
        up = findViewById(R.id.Btn_Up_Product);
        down = findViewById(R.id.Btn_Down_Product);

        //get data
        Bundle b = getIntent().getExtras();
        String masp1 = b.getString("masp");
        String ten1 = b.getString("ten");
        String anh1 = b.getString("anh");
        int gia1 = b.getInt("gia");
        String mota1 = b.getString("mota");
        String malsp1 = b.getString("malsp");

        //show
        int id = getResources().getIdentifier(anh1, "mipmap", getPackageName());
        imag.setImageResource(id);
        ten.setText(ten1);
        gia.setText(changePrice(gia1));
        mota.setText(mota1);

        //back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //up, down soluong
        upDown();

        //add product to listCart
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.listGioHang.size() > 0){
                    boolean kt = true;
                    for (int i = 0; i < Utils.listGioHang.size(); i++) {
                        if (masp1.equals(Utils.listGioHang.get(i).getMaSP())){
                            Utils.listGioHang.get(i).setSoluong(Utils.listGioHang.get(i).getSoluong()+dem);
                            kt = false;
                        }

                    }
                    if (kt == true) {
                        Utils.listGioHang.add(new SanPham(masp1, anh1, ten1, gia1, "", dem, malsp1));
                    }
                } else {
                    Utils.listGioHang.add(new SanPham(masp1, anh1, ten1, gia1, "", dem, malsp1));
                }
                Toast.makeText(Page_Detail_Product.this, "Add to cart", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //ham up and down soluong
    private void upDown(){
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dem++;
                sl.setText(dem + "");
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dem>1)
                    dem--;
                sl.setText(dem + "");
            }
        });

    }

    //hàm dấu chấm vào giá
    String changePrice(int n) {
        String s = "";
        while (n / 1000 > 0) {
            if (n % 1000 == 0) {
                s += ".000";
            } else {
                s = "." + n % 1000 + s;
            }
            n=n/1000;
        }
        return n + s;
    }
}