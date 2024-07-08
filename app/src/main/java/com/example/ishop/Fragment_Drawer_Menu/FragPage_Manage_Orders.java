package com.example.ishop.Fragment_Drawer_Menu;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.ishop.R;

public class FragPage_Manage_Orders extends Fragment {
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.ui_frag_page_manage_orders, container, false);

//        SearchView searchView = view.findViewById(R.id.Search_Orders);
//
//
//        String hint = "Tìm kiếm mã đơn hàng";
//        SpannableString spannableHint = new SpannableString(hint);
//
//        int hintColor = getResources().getColor(R.color.Dark);
//        spannableHint.setSpan(new ForegroundColorSpan(hintColor), 0, hint.length(), 0);
//
//        int hintSize = 48; // 48px
//        spannableHint.setSpan(new AbsoluteSizeSpan(hintSize), 0, hint.length(), 0);
//
//        searchView.setQueryHint(spannableHint);

        return view;
    }
}