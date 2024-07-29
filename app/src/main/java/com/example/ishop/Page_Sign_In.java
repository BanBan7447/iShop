package com.example.ishop;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.ishop.DAO.KhachHangDAO;

import com.example.ishop.DAO.NhanVienDAO;

import com.example.ishop.Type_Customers.Page_Detail_List_Product;
import com.example.ishop.Type_Customers.Page_Navigation_Type_C;
import com.example.ishop.Type_Manager.Page_Navigation_Type_M;
import com.example.ishop.service.LoginService;

import java.util.ArrayList;

public class Page_Sign_In extends AppCompatActivity {
    IntentFilter intentFilter;
    KhachHangDAO khachHangDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_page_sign_in);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.statusBarColor));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        EditText Edt_EmailorSDT_SI = findViewById(R.id.Edt_EmailorSDT_SI);
        EditText Edt_Password_SI = findViewById(R.id.Edt_Password_SI);
        Button btnSignIn_SI = findViewById(R.id.Btn_SignIn_SI);

        intentFilter = new IntentFilter();
        intentFilter.addAction("login");
        intentFilter.addAction("loginNV");
        intentFilter.addAction("loginQL");
        btnSignIn_SI.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String email = Edt_EmailorSDT_SI.getText().toString();
               String matkhau = Edt_Password_SI.getText().toString();

               Intent intent = new Intent(Page_Sign_In.this, LoginService.class);
               Bundle bundle = new Bundle();
               bundle.putString("email", email);
               bundle.putString("matkhau", matkhau);
               intent.putExtras(bundle);
               startService(intent);
           }
       });
        Button btnSignUp_SI = findViewById(R.id.Btn_SignUp_SI);
        btnSignUp_SI.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                startActivity(new Intent(Page_Sign_In.this, Page_Sign_Up.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myBroadcast,intentFilter);
    }

    public BroadcastReceiver myBroadcast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, @NonNull Intent intent) {
            switch (intent.getAction()) {
                case "login":
                    Bundle bundle = intent.getExtras();
                    boolean check = bundle.getBoolean("check");
                    if (check) {
                        startActivity(new Intent(Page_Sign_In.this, Page_Navigation_Type_C.class));
                    } else {
                        Toast.makeText(context, "Login Fail", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "loginNV":
                    Bundle bundle1 = intent.getExtras();
                    boolean checkNV = bundle1.getBoolean("checkNV");
                    if(checkNV){
                        startActivity(new Intent(Page_Sign_In.this, Page_Navigation_Type_C.class));
                    }else {
                        Toast.makeText(context, "Login Fail", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "loginQL":
                    Bundle bundle2 = intent.getExtras();
                    boolean checkQL = bundle2.getBoolean("checkQL");
                    if(checkQL){
                        startActivity(new Intent(Page_Sign_In.this, Page_Navigation_Type_C.class));
                    }else {
                        Toast.makeText(context, "Login Fail", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    };

}