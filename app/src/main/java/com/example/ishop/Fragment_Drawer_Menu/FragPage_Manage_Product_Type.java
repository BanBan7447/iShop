package com.example.ishop.Fragment_Drawer_Menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishop.Adapter.LoaiSanPhamAdapter;
import com.example.ishop.DAO.LoaiSanPhamDAO;
import com.example.ishop.Model.LoaiSanPham;
import com.example.ishop.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragPage_Manage_Product_Type extends Fragment {
    private RecyclerView rcv;
    private LoaiSanPhamDAO loaiSanPhamDAO;
    private LoaiSanPhamAdapter loaiSanPhamAdapter;
    private ArrayList<LoaiSanPham> listlsp;
    private FloatingActionButton FloatBtn_AddProductType;
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.ui_frag_page_manage_product_type, container, false);

        FloatBtn_AddProductType = view.findViewById(R.id.FloatBtn_Add_ProductType);

        loadData();

        addLSP();


        return view;
    }


    private void loadData() {
        LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO(getContext());
        listlsp = loaiSanPhamDAO.get_LSP();
        loaiSanPhamAdapter = new LoaiSanPhamAdapter(getContext(), listlsp);
        rcv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rcv.setAdapter(loaiSanPhamAdapter);
    }

    private void addLSP() {
        FloatBtn_AddProductType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdd_ProductType();
            }
        });
    }

    private void DialogAdd_ProductType(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = getLayoutInflater();
        View viewChangePass = layoutInflater.inflate(R.layout.ui_dialog_add_product_type, null);
        builder.setView(viewChangePass);

        EditText EdtAdd_Code_ProductType = viewChangePass.findViewById(R.id.EdtAdd_Code_ProductType);
        EditText EdtAdd_Name_ProductType = viewChangePass.findViewById(R.id.EdtAdd_Name_ProductType);
        Button Btn_Add_ProductType = viewChangePass.findViewById(R.id.Btn_Add_ProductType);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.fill_radius_16));

        Btn_Add_ProductType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String malsp = EdtAdd_Code_ProductType.getText().toString();
                String tenlsp = EdtAdd_Name_ProductType.getText().toString();
                if (!loaiSanPhamDAO.checkLSP(malsp)){
                    loaiSanPhamDAO.update_LSP(malsp, "", tenlsp);
                    alertDialog.dismiss();
                    loadData();
                } else {
                    Toast.makeText(getContext(), "Mã này đã tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}