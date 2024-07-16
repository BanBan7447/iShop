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

        return view;
    }
}