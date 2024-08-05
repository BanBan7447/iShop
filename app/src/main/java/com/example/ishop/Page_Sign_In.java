package com.example.ishop;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.ishop.DAO.KhachHangDAO;

import com.example.ishop.DAO.NhanVienDAO;

import com.example.ishop.DAO.QuanLyDAO;
import com.example.ishop.Type_Customers.Page_Detail_List_Product;
import com.example.ishop.Type_Customers.Page_Navigation_Type_C;
import com.example.ishop.Type_Employee.Page_Navigation_Type_E;
import com.example.ishop.Type_Manager.Page_Navigation_Type_M;
import com.example.ishop.service.LoginService;

import java.util.ArrayList;

public class Page_Sign_In extends AppCompatActivity {
    IntentFilter intentFilter;
    private KhachHangDAO khachHangDAO;
    private NhanVienDAO nhanVienDAO;
    private QuanLyDAO quanLyDAO;
    private  EditText Edt_EmailorSDT_SI,Edt_Password_SI;
    private CheckBox ckRememberPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_page_sign_in);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.statusBarColor));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        Edt_EmailorSDT_SI = findViewById(R.id.Edt_EmailorSDT_SI);
        Edt_Password_SI = findViewById(R.id.Edt_Password_SI);
        Button btnSignIn_SI = findViewById(R.id.Btn_SignIn_SI);
        ckRememberPass = findViewById(R.id.checkRememberPass);
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        String sEmail = pref.getString("Email","");
        String sPass = pref.getString("Pass","");
        Boolean rem = pref.getBoolean("REMEMBER", false);

        Edt_EmailorSDT_SI.setText(sEmail);
        Edt_Password_SI.setText(sPass);
        ckRememberPass.setChecked(rem);



        khachHangDAO = new KhachHangDAO(this);
        nhanVienDAO = new NhanVienDAO(this);
        quanLyDAO = new QuanLyDAO(this);

//        intentFilter = new IntentFilter();
//        intentFilter.addAction("login");
//        intentFilter.addAction("loginNV");
//        intentFilter.addAction("loginQL");
        btnSignIn_SI.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               checkLogin();

//               Intent intent = new Intent(Page_Sign_In.this, LoginService.class);
//               Bundle bundle = new Bundle();
//               bundle.putString("email", email);
//               bundle.putString("matkhau", matkhau);
//               intent.putExtras(bundle);
//               startService(intent);
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
    public void rememberUser(String u, String p, boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("Email",u);
        if(!status){
            edit.clear();
        }else {
            edit.putString("Email",u);
            edit.putString("Pass",p);
            edit.putBoolean("REMEMBER",status);
        }
        //luu lai toan bo
        edit.commit();
    }
    public void checkLogin(){
        String email = Edt_EmailorSDT_SI.getText().toString();
        String sdt = Edt_EmailorSDT_SI.getText().toString();
        String matkhau = Edt_Password_SI.getText().toString();

        boolean checkKH = khachHangDAO.checkKH(email,matkhau);
        boolean checkNV = nhanVienDAO.check_NV(email,sdt,matkhau);
        boolean checkQL = quanLyDAO.check_QL(email,sdt,matkhau);
        if(email.isEmpty()|| matkhau.isEmpty()){
            Toast.makeText(Page_Sign_In.this, "Vui long nhập đầy đủ thông tin ", Toast.LENGTH_SHORT).show();
        }else
            if(checkKH){
                startActivity(new Intent(Page_Sign_In.this,Page_Navigation_Type_C.class));
                rememberUser(email,matkhau,ckRememberPass.isChecked());
            }if (checkNV){
                startActivity(new Intent(Page_Sign_In.this, Page_Navigation_Type_E.class));
                rememberUser(email,matkhau,ckRememberPass.isChecked());
            }if (checkQL){
                startActivity(new Intent(Page_Sign_In.this,Page_Navigation_Type_M.class));
                rememberUser(email,matkhau,ckRememberPass.isChecked());
            }

        else
            if(!checkNV && !checkKH && !checkQL){
                Toast.makeText(Page_Sign_In.this, "Email và mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }


    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        registerReceiver(myBroadcast,intentFilter);
//    }

//    public BroadcastReceiver myBroadcast = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            switch (intent.getAction()) {
//                case "login":
//                    Bundle bundle = intent.getExtras();
//                    boolean check = bundle.getBoolean("check");
//                    if (check) {
//                        startActivity(new Intent(Page_Sign_In.this, Page_Navigation_Type_C.class));
//                    } else {
//                        Toast.makeText(context, "Login Fail", Toast.LENGTH_SHORT).show();
//                    }
//                    break;
//                case "loginNV":
//                    Bundle bundle1 = intent.getExtras();
//                    boolean checkNV = bundle1.getBoolean("checkNV");
//                    if(checkNV){
//                        startActivity(new Intent(Page_Sign_In.this, Page_Navigation_Type_C.class));
//                    }else {
//                        Toast.makeText(context, "Login Fail", Toast.LENGTH_SHORT).show();
//                    }
//                    break;
//                case "loginQL":
//                    Bundle bundle2 = intent.getExtras();
//                    boolean checkQL = bundle2.getBoolean("checkQL");
//                    if(checkQL){
//                        startActivity(new Intent(Page_Sign_In.this, Page_Navigation_Type_C.class));
//                    }else {
//                        Toast.makeText(context, "Login Fail", Toast.LENGTH_SHORT).show();
//                    }
//                    break;
//                default:
//                    break;
//            }
//        }
//    };


}