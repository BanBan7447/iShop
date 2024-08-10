package com.example.ishop.Activity_Manage;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishop.R;

public class Page_Header_Menu extends AppCompatActivity {
    private ImageView Data_Image_Manager;
    private TextView Data_Code_Manager, Data_Name_Manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_header_drawer_type_m);


    }
}