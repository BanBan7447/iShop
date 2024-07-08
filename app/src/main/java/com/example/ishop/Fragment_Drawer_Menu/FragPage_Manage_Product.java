package com.example.ishop.Fragment_Drawer_Menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.ishop.R;
import com.example.ishop.Type_Manager.Page_Add_Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FragPage_Manage_Product extends Fragment {
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.ui_frag_page_manage_product, container, false);

        FloatingActionButton FloatBtn_Add_Product = view.findViewById(R.id.FloatBtn_Add_Product);
        FloatBtn_Add_Product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Page_Add_Product.class));
            }
        });

        return view;
    }
}