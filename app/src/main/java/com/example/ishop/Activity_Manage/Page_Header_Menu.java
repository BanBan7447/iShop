package com.example.ishop.Activity_Manage;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.ishop.R;

public class Page_Header_Menu extends AppCompatActivity {

    public Page_Header_Menu(ImageView dataImageManager, TextView dataCodeManager, TextView dataNameManager) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_header_drawer_type_m);


    }
}