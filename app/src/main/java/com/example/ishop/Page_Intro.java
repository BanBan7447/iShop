package com.example.ishop;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishop.DAO.DonHangDAO;
import com.example.ishop.DAO.HoaDonDAO;
import com.example.ishop.DAO.KhachHangDAO;
import com.example.ishop.DAO.SanPhamDAO;
import com.example.ishop.Model.KhachHang;
import com.example.ishop.Model.NhanVien;

public class Page_Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_page_intro);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.statusBarColor));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Page_Intro.this, Page_Sign_In.class);
                startActivity(intent);
                finish();
            }
        }, 1400);
    }
}