package com.example.ishop.Type_Customers.Fragment_Page_TypeC;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ishop.Database.DBHelper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ishop.DAO.KhachHangDAO;
import com.example.ishop.Database.DBHelper;
import com.example.ishop.Model.KhachHang;
import com.example.ishop.Page_Sign_In;
import com.example.ishop.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class FragPage_Profile extends Fragment {
    private DBHelper dbHelper;
    ShapeableImageView Data_Image_Customer;
   // private KhachHang khachHang;
    private ArrayList<KhachHang> list;
    Dialog dialog;
    TextView tv_Change_Pass;
    TextView Data_Name_Customer,Data_SDT_Customer,Data_Email_Customer,Data_Location_Customer,Data_Quantity_Cart;
    EditText EdtChange_OldPass,EdtChange_NewPass,EdtChange_SubmitPass;
    Button Btn_ChangePass,Btn_Cancel_ChangePass;
    private  KhachHangDAO khachHangDAO;
    private int tKhachHang;
    private int sKhachHang;
    private int eKhachHang;
    private int lcKhachHang;
    private int pKhachHang;
    public FragPage_Profile() {
        dbHelper = new DBHelper(getContext());
    }
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.ui_frag_page_profile, container, false);
        Data_Name_Customer =  view.findViewById(R.id.Data_Name_Customer);
        Data_SDT_Customer =  view.findViewById(R.id.Data_SDT_Customer);
        Data_Email_Customer =  view.findViewById(R.id.Data_Email_Customer);
        Data_Location_Customer =  view.findViewById(R.id.Data_Location_Customer);
        Data_Quantity_Cart =  view.findViewById(R.id.Data_Quantity_Cart);
        tv_Change_Pass = view.findViewById(R.id.tv_Change_Pass);


        //hien thong tin user
         khachHangDAO = new KhachHangDAO(this.getContext());
        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String email = pref.getString("Email","");
        ArrayList<KhachHang> list = khachHangDAO.gettTKH(email);

        Data_Name_Customer.setText(list.get(tKhachHang).getTenKH());
        Data_SDT_Customer.setText(list.get(sKhachHang).getSdtKH());
        Data_Email_Customer.setText(list.get(eKhachHang).getEmailKH());
        Data_Location_Customer.setText(list.get(lcKhachHang).getDiachiKH());

        //dialog doi mat khau
        String olPass = list.get(pKhachHang).getMatkhauKH();
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



        return view;
    }

    public void outAPP(){
        startActivity(new Intent(getContext(),Page_Sign_In.class));
    }
}