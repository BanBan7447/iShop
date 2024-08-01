package com.example.ishop.Fragment_Drawer_Menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishop.Adapter.DonHangAdapter;
import com.example.ishop.DAO.DonHangDAO;
import com.example.ishop.Model.DonHang;
import com.example.ishop.R;

import java.util.ArrayList;

public class FragPage_Manage_Orders extends Fragment {
    private Button Btn_NotDone, Btn_Progress, Btn_Done;
    private RecyclerView rcv;
    private DonHangDAO donHangDAO;
    private DonHangAdapter donHangAdapter;
    private ArrayList<DonHang> list;
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.ui_frag_page_manage_orders, container, false);
//        SearchView searchView = view.findViewById(R.id.Search_Orders);
//        String hint = "Tìm kiếm mã đơn hàng";
//        SpannableString spannableHint = new SpannableString(hint);
//        int hintColor = getResources().getColor(R.color.Dark);
//        spannableHint.setSpan(new ForegroundColorSpan(hintColor), 0, hint.length(), 0);
//        int hintSize = 48; // 48px
//        spannableHint.setSpan(new AbsoluteSizeSpan(hintSize), 0, hint.length(), 0);
//        searchView.setQueryHint(spannableHint);
        Btn_NotDone = view.findViewById(R.id.Btn_Select_NotDone);
        Btn_Progress = view.findViewById(R.id.Btn_Select_Progress);
        Btn_Done = view.findViewById(R.id.Btn_Select_Done);
        rcv = view.findViewById(R.id.rcv_manage_orders);
        donHangDAO = new DonHangDAO(getContext());
        list = donHangDAO.get_ListDH();
        ArrayList<DonHang> listNotDone = new ArrayList<>(), listProgress = new ArrayList<>(), listDone = new ArrayList<>();

        for (DonHang dh : list) {
            if (dh.getTrangthai().equals("Chưa xử lý")) {
                listNotDone.add(dh);
            }
            if (dh.getTrangthai().equals("Đang xử lý")) {
                listProgress.add(dh);
            }
            if (dh.getTrangthai().equals("Đã xử lý")) {
                listDone.add(dh);
            }
        }

        donHangAdapter = new DonHangAdapter(getContext(), listNotDone);
        rcv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcv.setAdapter(donHangAdapter);

        showNotDone(listNotDone);
        showProgress(listProgress);
        showDone(listDone);
        return view;
    }

    //
    private void showNotDone(ArrayList<DonHang> listNotDone) {
        Btn_NotDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donHangAdapter = new DonHangAdapter(getContext(), listNotDone);
                rcv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                rcv.setAdapter(donHangAdapter);
            }
        });
    }

    //
    private void showProgress(ArrayList<DonHang> listProgress) {
        Btn_Progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donHangAdapter = new DonHangAdapter(getContext(), listProgress);
                rcv.setAdapter(donHangAdapter);
            }
        });
    }

    //
    private void showDone(ArrayList<DonHang> listDone) {
        Btn_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donHangAdapter = new DonHangAdapter(getContext(), listDone);
                rcv.setAdapter(donHangAdapter);
            }
        });
    }
}