package com.example.ishop.Type_Manager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.ishop.DAO.DonHangDAO;
import com.example.ishop.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Page_Statistical_Purchase extends AppCompatActivity {
 Button btnDatePicker, btnXem;
 ImageView Icon_Back1;
 EditText edt_Date;
 TextView Data_Total_Sell_Orders,Data_Sell_Orders,Data_Revenue_Orders;
 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 int mYear,mMonth,mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_page_statistical_purchase);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.statusBarColor));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        btnDatePicker = findViewById(R.id.btnDatePicker);
        edt_Date = findViewById(R.id.edt_Date);
        Data_Total_Sell_Orders = findViewById(R.id.Data_Total_Sell_Orders);
        btnXem = findViewById(R.id.btnXem);
        Data_Revenue_Orders = findViewById(R.id.Data_Revenue_Orders);
        Data_Sell_Orders = findViewById(R.id.Data_Sell_Orders);
        Icon_Back1 = findViewById(R.id.Icon_Back1);
        Icon_Back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Page_Statistical_Purchase.this,Page_Navigation_Type_M.class));

            }
        });

        //hienthitongsodonhang
        DonHangDAO donHangDAO = new DonHangDAO(this);
        String a = String.valueOf(donHangDAO.get_sDH());
        Data_Total_Sell_Orders.setText(a);


        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(Page_Statistical_Purchase.this,0,mDate,mYear,mMonth,mDay);
                d.show();
            }
        });
        btnXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ngay = edt_Date.getText().toString();
                Data_Revenue_Orders.setText(""+donHangDAO.getDoanhthu(ngay)+"VND");
                Data_Sell_Orders.setText(""+donHangDAO.get_sDH1(ngay)+"đơn");
            }
        });
    }
    DatePickerDialog.OnDateSetListener mDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
            edt_Date.setText(sdf.format(c.getTime()));
        }
    };
}