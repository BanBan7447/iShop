package com.example.ishop.Type_Customers.Fragment_Page_TypeC;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishop.Adapter.LoaiSPAdapter_User1;
import com.example.ishop.Adapter.SanPhamAdapter;
import com.example.ishop.DAO.LoaiSanPhamDAO;
import com.example.ishop.DAO.SanPhamDAO;
import com.example.ishop.Model.LoaiSanPham;
import com.example.ishop.Model.SanPham;
import com.example.ishop.R;
import com.example.ishop.Type_Customers.Page_List_Product_Type;
import com.example.ishop.Utils.Utils;

import java.util.ArrayList;

public class FragPage_Home extends Fragment {
    @Nullable
    private LoaiSanPhamDAO loaiSanPhamDAO;
    private LoaiSPAdapter_User1 loaiSanPhameAdapterUser;
    private RecyclerView rcv_product_type, rcv1, rcv2, rcv3, rcv4;
    private ArrayList<LoaiSanPham> listlsp;
    private SanPhamDAO sanPhamDAO;
    private SanPhamAdapter sanPhamAdapter;
    private ArrayList<SanPham> listsp, list1, list2, list3, list4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); // Kích hoạt menu trong Fragment
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.ui_frag_page_home, container, false);

        Toolbar toolbar = view.findViewById(R.id.Toolbar_Page);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        rcv_product_type = view.findViewById(R.id.rcv_LoaiSanPham_home);
        rcv1 = view.findViewById(R.id.rcv_product_iphone);
        rcv2 = view.findViewById(R.id.rcv_product_ipad);
        rcv3 = view.findViewById(R.id.rcv_product_mac);
        rcv4 = view.findViewById(R.id.rcv_product_appleWatch);
        loaiSanPhamDAO = new LoaiSanPhamDAO(getContext());
        sanPhamDAO = new SanPhamDAO(getContext());

        loadData();

        //show all loai san pham
        TextView txt_Select_All = view.findViewById(R.id.txt_Select_All);
        txt_Select_All.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Page_List_Product_Type.class));
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_tool_bar, menu);
        MenuItem menuItem = menu.findItem(R.id.active_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Tìm kiếm sản phẩm");
        super.onCreateOptionsMenu(menu, inflater);
    }

    //load Data
    private void loadData() {
        //loai san pham
        listlsp = loaiSanPhamDAO.get_LSP();
        loaiSanPhameAdapterUser = new LoaiSPAdapter_User1(getContext(), listlsp);
        rcv_product_type.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcv_product_type.setAdapter(loaiSanPhameAdapterUser);

        //san pham
        listsp = sanPhamDAO.get_SP();
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        list4 = new ArrayList<>();
        for (SanPham sp : listsp){
            if (sp.getMaLSP().equals("IP27"))
                list1.add(sp);
            if (sp.getMaLSP().equals("IA10"))
                list2.add(sp);
            if (sp.getMaLSP().equals("IM84"))
                list3.add(sp);
            if (sp.getMaLSP().equals("IW15"))
                list4.add(sp);
        }

        //iphone
        sanPhamAdapter = new SanPhamAdapter(getContext(),list1);
        rcv1.setLayoutManager(new GridLayoutManager(getContext(),2, LinearLayoutManager.HORIZONTAL, false));
        rcv1.setAdapter(sanPhamAdapter);
        //ipad
        sanPhamAdapter = new SanPhamAdapter(getContext(),list2);
        rcv2.setLayoutManager(new GridLayoutManager(getContext(), 2, LinearLayoutManager.HORIZONTAL, false));
        rcv2.setAdapter(sanPhamAdapter);
        //mac
        sanPhamAdapter = new SanPhamAdapter(getContext(),list3);
        rcv3.setLayoutManager(new GridLayoutManager(getContext(), 2, LinearLayoutManager.HORIZONTAL, false));
        rcv3.setAdapter(sanPhamAdapter);
        //apple watch
        sanPhamAdapter = new SanPhamAdapter(getContext(),list4);
        rcv4.setLayoutManager(new GridLayoutManager(getContext(), 2, LinearLayoutManager.HORIZONTAL, false));
        rcv4.setAdapter(sanPhamAdapter);

        if (Utils.listGioHang == null) {
            Utils.listGioHang = new ArrayList<>();
        }
    }


}