package com.example.ishop.Type_Manager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.ishop.DAO.HoaDonDAO;
import com.example.ishop.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Page_Statistical_Revenue extends AppCompatActivity {
    Button btn_Xem,btn_tuNgay,btn_denNgay;
    EditText edt_tuNgay,edt_denNgay;
    TextView Data_Total_Revenue,Data_Revenue;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    int mYear,mMonth,mDay;
    ImageView Icon_Back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_page_statistical_revenue);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.statusBarColor));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        btn_tuNgay = findViewById(R.id.btn_tuNgay);
        btn_denNgay = findViewById(R.id.btn_denNgay);
        Data_Total_Revenue = findViewById(R.id.Data_Total_Revenue);
        btn_Xem = findViewById(R.id.btn_Xem);
        edt_tuNgay = findViewById(R.id.edt_tuNgay);
        edt_denNgay = findViewById(R.id.edt_denNgay);
        Data_Revenue = findViewById(R.id.Data_Revenue);

        HoaDonDAO hoaDonDAO = new HoaDonDAO(this);
        Data_Total_Revenue.setText(""+hoaDonDAO.getDoanhthu()+"VND");

        Icon_Back = findViewById(R.id.Icon_Back);
        Icon_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Page_Statistical_Revenue.this,Page_Navigation_Type_M.class));
            }
        });

        btn_tuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(Page_Statistical_Revenue.this, 0,mDateTuNgay, mYear,mMonth,mDay);
                d.show();
            }
        });
        btn_denNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(Page_Statistical_Revenue.this, 0,mDateDenNgay, mYear,mMonth,mDay);
                d.show();

            }
        });
        btn_Xem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tuNgay = edt_tuNgay.getText().toString();
                String denNgay = edt_denNgay.getText().toString();
                Data_Revenue.setText(""+hoaDonDAO.getDoanhthuD(tuNgay,denNgay)+"VND");
            }
        });




    }
    DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
            edt_tuNgay.setText(sdf.format(c.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
            edt_denNgay.setText(sdf.format(c.getTime()));
        }
    };
}