package com.example.ishop.Fragment_Drawer_Menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ishop.R;
import com.example.ishop.Type_Manager.Page_Statistical_Customer;
import com.example.ishop.Type_Manager.Page_Statistical_Purchase;
import com.example.ishop.Type_Manager.Page_Statistical_Product;
import com.example.ishop.Type_Manager.Page_Statistical_Revenue;

public class FragPage_Statistical extends Fragment {
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.ui_frag_page_statistical, container, false);

        RelativeLayout MovePage_Total_Revenue = view.findViewById(R.id.MovePage_Revenue_Statistical);
        MovePage_Total_Revenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Page_Statistical_Revenue.class));
            }
        });

        RelativeLayout MovePage_Total_Customer_SignUp = view.findViewById(R.id.MovePage_Customer_Statistical);
        MovePage_Total_Customer_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Page_Statistical_Customer.class));
            }
        });

        RelativeLayout MovePage_Total_Customer_Buy = view.findViewById(R.id.MovePage_Product_Statistical);
        MovePage_Total_Customer_Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Page_Statistical_Product.class));
            }
        });

        RelativeLayout MovePage_iPhone_Orders = view.findViewById(R.id.MovePage_Orders_Statistical);
        MovePage_iPhone_Orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Page_Statistical_Purchase.class));
            }
        });

        return view;
    }
}