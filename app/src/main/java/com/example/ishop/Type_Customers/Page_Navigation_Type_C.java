package com.example.ishop.Type_Customers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.ishop.DAO.GioHangDAO;
import com.example.ishop.DAO.KhachHangDAO;
import com.example.ishop.Model.SanPham;
import com.example.ishop.R;
import com.example.ishop.Type_Customers.Fragment_Page_TypeC.FragPage_Cart;
import com.example.ishop.Type_Customers.Fragment_Page_TypeC.FragPage_Home;
import com.example.ishop.Type_Customers.Fragment_Page_TypeC.FragPage_Notification;
import com.example.ishop.Type_Customers.Fragment_Page_TypeC.FragPage_Profile;
import com.example.ishop.Utils.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Page_Navigation_Type_C extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_page_navigation_type_c);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.statusBarColor));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        BottomNavigationView bottomNavigationView = findViewById(R.id.Bottom_Navigation_Type_C);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.Layout_Bottom, new FragPage_Home()).commit();

        //bottomNavigationView.setSelectedItemId(R.id.Fragment_Home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId() == R.id.Fragment_Home) {
                    fragment = new FragPage_Home();
                } else if (item.getItemId() == R.id.Fragment_Cart) {
                    fragment = new FragPage_Cart();
                } else if (item.getItemId() == R.id.Fragment_Notification) {
                    fragment = new FragPage_Notification();
                } else {
                    fragment = new FragPage_Profile();
                }

                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.Layout_Bottom, fragment).commit();
                }

                //bottomNavigationView.getMenu().findItem(item.getItemId()).setChecked(true);
                return true;
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!Utils.listGioHang.isEmpty()) {
            GioHangDAO gioHangDAO = new GioHangDAO(this);
            SharedPreferences pref = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String email = pref.getString("Email", "");
            KhachHangDAO khachHangDAO = new KhachHangDAO(this);
            String maKH = khachHangDAO.gettTKH(email).getMa();
            if (!maKH.isEmpty()) {
                for (SanPham sp : Utils.listGioHang) {
                    gioHangDAO.add_SP(maKH, sp.getAnh(), sp.getTen(), changeNameType(sp.getMaLSP()), sp.getGia(), sp.getSoluong());
                }
            }
        }
    }

    private String changeNameType(String s) {
        if (s.equals("IP27"))
            return "iPhone";
        if (s.equals("IA10"))
            return "iPad";
        if (s.equals("IM84"))
            return "Mac";
        if (s.equals("IW15"))
            return "Apple Watch";
        return "";
    }
}