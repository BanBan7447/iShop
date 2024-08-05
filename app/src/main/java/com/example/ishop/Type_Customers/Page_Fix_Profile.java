package com.example.ishop.Type_Customers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishop.DAO.KhachHangDAO;
import com.example.ishop.Model.KhachHang;
import com.example.ishop.R;

public class Page_Fix_Profile extends AppCompatActivity {
    private ImageView Icon_Back;
    private TextView EdtFix_Name, EdtFix_SDT, EdtFix_Email, EdtFix_Location;
    private Button Btn_Fix_Profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_page_fix_profile);

        Icon_Back = findViewById(R.id.Icon_Back);
        EdtFix_Name = findViewById(R.id.EdtFix_Name);
        EdtFix_SDT = findViewById(R.id.EdtFix_SDT);
        EdtFix_Email = findViewById(R.id.EdtFix_Email);
        EdtFix_Location = findViewById(R.id.EdtFix_Location);
        Btn_Fix_Profile = findViewById(R.id.Btn_Fix_Profile);

        //get KH
        SharedPreferences pref = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String email = pref.getString("Email", "duytham96@gmail.com");
        if (!email.isEmpty()) {
            KhachHangDAO khachHangDAO = new KhachHangDAO(this);
            KhachHang khachHang = khachHangDAO.gettTKH(email);
            EdtFix_Name.setText(khachHang.getTen());
            EdtFix_SDT.setText(khachHang.getSdt());
            EdtFix_Email.setText(khachHang.getEmail());
            EdtFix_Location.setText(khachHang.getDiachi());

            Btn_Fix_Profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String ten = EdtFix_Name.getText().toString();
                    String sdt = EdtFix_SDT.getText().toString();
                    String em = EdtFix_Email.getText().toString();
                    String diachi = EdtFix_Location.getText().toString();

                    khachHangDAO.update_KH(khachHang.getMa(), khachHang.getAnh(), ten, sdt, em, khachHang.getMatkhau(), diachi);
                    SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
                    SharedPreferences.Editor edit = pref.edit();
                    edit.putString("Email", em);
                    edit.commit();

                    finish();
                }
            });
        }
        Icon_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}