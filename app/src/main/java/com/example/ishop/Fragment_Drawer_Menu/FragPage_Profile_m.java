package com.example.ishop.Fragment_Drawer_Menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ishop.DAO.QuanLyDAO;
import com.example.ishop.Model.KhachHang;
import com.example.ishop.Model.QuanLy;
import com.example.ishop.R;

import java.util.ArrayList;

public class FragPage_Profile_m extends Fragment {
    private ImageButton Btn_MovePage_Fix_Profile;
    private ImageView Data_Image_m_e;
    private ArrayList<KhachHang> list;
    private TextView Data_Name_m_e, Data_SDT_m_e, Data_Email_m_e, Data_Location_m_e;
    private QuanLy ql;
    private QuanLyDAO QuanLyDAO;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.ui_frag_page_profile_m_e, container, false);
        Data_Image_m_e = view.findViewById(R.id.Data_Image_m_e);
        Data_Name_m_e = view.findViewById(R.id.Data_Name_m_e);
        Data_SDT_m_e = view.findViewById(R.id.Data_SDT_m_e);
        Data_Email_m_e = view.findViewById(R.id.Data_Email_m_e);
        Data_Location_m_e = view.findViewById(R.id.Data_Location_m_e);
        QuanLyDAO = new QuanLyDAO(this.getContext());

        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String email = pref.getString("Email", "");
        ql = QuanLyDAO.gettTQL(email);
        Data_Name_m_e.setText(ql.getTen());
        Data_SDT_m_e.setText(ql.getSdt());
        Data_Email_m_e.setText(ql.getEmail());
        Data_Location_m_e.setText(ql.getDiachi());
        int id = getContext().getResources().getIdentifier(ql.getAnh(), "mipmap", getContext().getPackageName());
        Data_Image_m_e.setImageResource(id);




        return view;
    }



}