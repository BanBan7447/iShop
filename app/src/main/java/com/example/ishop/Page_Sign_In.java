package com.example.ishop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.ishop.Type_Customers.Page_Navigation_Type_C;
import com.example.ishop.Type_Manager.Page_Navigation_Type_M;

public class Page_Sign_In extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_page_sign_in);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.statusBarColor));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        Button btnSignIn_SI = findViewById(R.id.Btn_SignIn_SI);
        btnSignIn_SI.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(Page_Sign_In.this, Page_Navigation_Type_C.class));
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
}