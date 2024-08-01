package com.example.ishop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishop.DAO.KhachHangDAO;
import com.example.ishop.Database.DBHelper;

public class Page_Sign_Up extends AppCompatActivity {
        EditText Edt_UserName_SU,Edt_Email_SU,Edt_SDT_SU,Edt_Password_SU;
        private KhachHangDAO khachHangDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ui_page_sign_up);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.statusBarColor));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        Edt_UserName_SU = findViewById(R.id.Edt_UserName_SU);
        Edt_Email_SU = findViewById(R.id.Edt_Email_SU);
        Edt_SDT_SU = findViewById(R.id.Edt_SDT_SU);
        Edt_Password_SU = findViewById(R.id.Edt_Password_SU);
        khachHangDAO = new KhachHangDAO(this);
        Button btnSignIn_SU = findViewById(R.id.Btn_SignIn_SU);
        Button btn_SignUp_SU = findViewById(R.id.Btn_SignUp_SU);
        btnSignIn_SU.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                startActivity(new Intent(Page_Sign_Up.this, Page_Sign_In.class));
            }
        });
        btn_SignUp_SU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Edt_UserName_SU.getText().toString();
                String email = Edt_Email_SU.getText().toString();
                String sdt = Edt_SDT_SU.getText().toString();
                String pass = Edt_Password_SU.getText().toString();

                if(username.isEmpty() || email.isEmpty() || sdt.isEmpty() || pass.isEmpty()){
                    Toast.makeText(Page_Sign_Up.this, "Vui lòng nhập đầy đủ thông tin  ", Toast.LENGTH_SHORT).show();
                }else {
                    khachHangDAO.add_KH("","",username,sdt,email,pass,"");
                    Toast.makeText(Page_Sign_Up.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Page_Sign_Up.this,Page_Sign_In.class));
                }
            }
        });


    }
}