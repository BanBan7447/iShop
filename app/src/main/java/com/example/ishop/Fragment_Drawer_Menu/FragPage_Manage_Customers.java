package com.example.ishop.Fragment_Drawer_Menu;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishop.Adapter.KhacHangAdapter;
import com.example.ishop.DAO.KhachHangDAO;
import com.example.ishop.Model.KhachHang;
import com.example.ishop.R;

import java.util.ArrayList;

public class FragPage_Manage_Customers extends Fragment {
    private RecyclerView rcv;
    private KhachHangDAO khachHangDAO;
    private ArrayList<KhachHang> list;
    private KhacHangAdapter khacHangAdapter;
    private SearchView searchView;
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.ui_frag_page_manage_customers, container, false);

        rcv = view.findViewById(R.id.rcv_list_KH);
        searchView = view.findViewById(R.id.Search_Orders);

        khachHangDAO = new KhachHangDAO(getContext());
        list = khachHangDAO.get_ListKH();

        khacHangAdapter = new KhacHangAdapter(getContext(), list);
        rcv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rcv.setAdapter(khacHangAdapter);

        timkiem();
        return view;
    }

    private void timkiem() {
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                khacHangAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                khacHangAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }
}