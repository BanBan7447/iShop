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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishop.Adapter.HoaDonAdapter;
import com.example.ishop.DAO.HoaDonDAO;
import com.example.ishop.Model.HoaDon;
import com.example.ishop.R;

import java.util.ArrayList;

public class FragPage_Manage_Bill extends Fragment {
    private RecyclerView rcv;
    private HoaDonDAO hoaDonDAO;
    private HoaDonAdapter hoaDonAdapter;
    private ArrayList<HoaDon> list;
    private SearchView searchView;
    @NonNull
    @Override
    public View onCreateView (@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.ui_frag_page_manage_bill, container, false);

        rcv = view.findViewById(R.id.rcv_list_bill);
        searchView = view.findViewById(R.id.Search_Bill);
        hoaDonDAO = new HoaDonDAO(getContext());
        list = hoaDonDAO.get_ListHD();
        hoaDonAdapter = new HoaDonAdapter(getContext(), list);
        rcv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rcv.setAdapter(hoaDonAdapter);
        timkiem();
        return view;
    }

    private void timkiem() {
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                hoaDonAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                hoaDonAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }
}