package com.example.ishop.Type_Customers.Fragment_Page_TypeC;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ishop.Activity_Manage.Page_History_Orders;
import com.example.ishop.DAO.DonHangDAO;
import android.widget.Toast;

import com.example.ishop.Database.DBHelper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ishop.DAO.KhachHangDAO;
import com.example.ishop.Database.DBHelper;
import com.example.ishop.Model.DonHang;
import com.example.ishop.Model.KhachHang;
import com.example.ishop.Page_Sign_In;
import com.example.ishop.R;
import com.example.ishop.Type_Customers.Page_Fix_Profile;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class FragPage_Profile extends Fragment {
    private ImageButton Btn_MovePage_Fix_Profile;
    private ImageView Data_Image_Customer, Page_Show_History_Orders;
    private ArrayList<KhachHang> list;
    private TextView Data_Name_Customer,Data_SDT_Customer,Data_Email_Customer,Data_Location_Customer,Data_Quantity_Cart;
    private KhachHang kh;
    private Dialog dialog;
    private TextView tv_Change_Pass;
    private EditText EdtChange_OldPass,EdtChange_NewPass,EdtChange_SubmitPass;
    private Button Btn_ChangePass,Btn_Cancel_ChangePass;
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.ui_frag_page_profile, container, false);
        Data_Name_Customer =  view.findViewById(R.id.Data_Name_Customer);
        Data_SDT_Customer =  view.findViewById(R.id.Data_SDT_Customer);
        Data_Email_Customer =  view.findViewById(R.id.Data_Email_Customer);
        Data_Location_Customer =  view.findViewById(R.id.Data_Location_Customer);
        Data_Quantity_Cart =  view.findViewById(R.id.Data_Quantity_Cart);
        Data_Image_Customer =  view.findViewById(R.id.Data_Image_Customer);
        Page_Show_History_Orders =  view.findViewById(R.id.Page_Show_History_Orders);
        Btn_MovePage_Fix_Profile =  view.findViewById(R.id.Btn_MovePage_Fix_Profile);

        tv_Change_Pass = view.findViewById(R.id.tv_Change_Pass);

        KhachHangDAO khachHangDAO = new KhachHangDAO(this.getContext());

        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String email = pref.getString("Email","duytham96@gmail.com");
        kh = khachHangDAO.gettTKH(email);
        Data_Name_Customer.setText(kh.getTen());
        Data_SDT_Customer.setText(kh.getSdt());
        Data_Email_Customer.setText(kh.getEmail());
        Data_Location_Customer.setText(kh.getDiachi());

        int id = getContext().getResources().getIdentifier(kh.getAnh(), "mipmap", getContext().getPackageName());
        Data_Image_Customer.setImageResource(id);
        String olPass = kh.getMatkhau();

        //so don da dat
        showDonebill();

        //show lich su dat hang
        Page_Show_History_Orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Page_History_Orders.class));
            }
        });

        //dialog doi mat khau
        String olPass = kh.getMatkhauKH();
        tv_Change_Pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        dialog= new Dialog(getContext());
        dialog.setContentView(R.layout.ui_dialog_change_pass);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getContext().getDrawable(R.drawable.fill_radius_16));


        EdtChange_NewPass = dialog.findViewById(R.id.EdtChange_NewPass);
        EdtChange_SubmitPass = dialog.findViewById(R.id.EdtChange_SubmitPass);

        Btn_Cancel_ChangePass = dialog.findViewById(R.id.Btn_Cancel_ChangePass);
        Btn_ChangePass = dialog.findViewById(R.id.Btn_ChangePass);
        Btn_Cancel_ChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Btn_ChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EdtChange_OldPass = dialog.findViewById(R.id.EdtChange_OldPass);
                String oldPass =  EdtChange_OldPass.getText().toString();
                String newPass =  EdtChange_NewPass.getText().toString();
                String smPass =  EdtChange_SubmitPass.getText().toString();
                if(EdtChange_NewPass.getText().length() == 0 || EdtChange_SubmitPass.getText().length() == 0|| EdtChange_OldPass.getText().length() == 0){
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin ", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(!oldPass.equals(olPass)){
                        Toast.makeText(getContext(), "Mật khẩu cũ không đúng   ", Toast.LENGTH_SHORT).show();
                    }
                    if(!newPass.equals(smPass)){
                        Toast.makeText(getContext(), "Vui lòng nhập lại mật khẩu mới trùng khớp  ", Toast.LENGTH_SHORT).show();
                    }
                    if(oldPass.equals(olPass)){
                        boolean check = khachHangDAO.update_KH("","","","",email,newPass,"");
                        if(check){
                            EdtChange_OldPass.setText("");
                            EdtChange_SubmitPass.setText("");
                            EdtChange_NewPass.setText("");
                            Toast.makeText(getContext(), " thay đổi mật khẩu thành công  ", Toast.LENGTH_SHORT).show();
                            outAPP();
                            // startActivity(new Intent(getContext().this,Page_Sign_In.class));
                        }


                        Toast.makeText(getContext(), " thay đổi mật khẩu thất bại  ", Toast.LENGTH_SHORT).show();
                    }


                }



            }


        });

        //fix profile
        Btn_MovePage_Fix_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Page_Fix_Profile.class));
            }
        });
        return view;
    }

    private void showDonebill() {
        int dem = 0;
        DonHangDAO donHangDAO = new DonHangDAO(getContext());
        ArrayList<DonHang> list = donHangDAO.get_ListDH();
        for (DonHang dh : list) {
            if (dh.getMaKH().equals(kh.getMa()) && dh.getTrangthai().equals("Đã xử lý")){
                dem++;
            }
        }
        Data_Quantity_Cart.setText(String.valueOf(dem));
    }
    public void outAPP(){
        startActivity(new Intent(getContext(),Page_Sign_In.class));
    }
}