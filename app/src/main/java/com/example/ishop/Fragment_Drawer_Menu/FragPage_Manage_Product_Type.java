package com.example.ishop.Fragment_Drawer_Menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.ishop.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FragPage_Manage_Product_Type extends Fragment {
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.ui_frag_page_manage_product_type, container, false);

        FloatingActionButton FloatBtn_AddProductType = view.findViewById(R.id.FloatBtn_Add_ProductType);
        FloatBtn_AddProductType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdd_ProductType();
            }
        });


        return view;
    }

    private void DialogAdd_ProductType(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = getLayoutInflater();
        View viewChangePass = layoutInflater.inflate(R.layout.ui_dialog_add_product_type, null);
        builder.setView(viewChangePass);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.fill_radius_16));
    }
}