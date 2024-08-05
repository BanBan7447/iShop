package com.example.ishop.Type_Customers.Fragment_Page_TypeC;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishop.R;

public class FragPage_Notification extends Fragment {
    private RecyclerView rcv_notification;
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.ui_frag_page_notification, container, false);

        rcv_notification = view.findViewById(R.id.rcv_notification);
        return view;
    }
}