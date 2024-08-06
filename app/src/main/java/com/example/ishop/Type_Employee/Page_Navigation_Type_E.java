package com.example.ishop.Type_Employee;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.ishop.DAO.NhanVienDAO;
import com.example.ishop.DAO.QuanLyDAO;
import com.example.ishop.Fragment_Drawer_Menu.FragPage_Manage_Bill;
import com.example.ishop.Fragment_Drawer_Menu.FragPage_Manage_Customers;
import com.example.ishop.Fragment_Drawer_Menu.FragPage_Manage_Orders;
import com.example.ishop.Model.NhanVien;
import com.example.ishop.Model.QuanLy;
import com.example.ishop.Page_Sign_In;
import com.example.ishop.R;
import com.example.ishop.Type_Manager.Page_Navigation_Type_M;
import com.google.android.material.navigation.NavigationView;

public class Page_Navigation_Type_E extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    EditText EdtChange_OldPass,EdtChange_NewPass,EdtChange_SubmitPass;
    Button Btn_ChangePass,Btn_Cancel_ChangePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_page_navigation_type_e);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.statusBarColor));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        drawerLayout = findViewById(R.id.Drawer_Layout);

        Toolbar toolbar = findViewById(R.id.Drawer_Toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_hamburger_menu);

        navigationView = findViewById(R.id.Drawer_TypeE);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId() == R.id.Fragment_Manage_Orders){
                    fragment = new FragPage_Manage_Orders();
                } else if (item.getItemId() == R.id.Fragment_Manage_Bill) {
                    fragment = new FragPage_Manage_Bill();
                } else if (item.getItemId() == R.id.Fragment_Manage_Customers) {
                    fragment = new FragPage_Manage_Customers();
                } else if (item.getItemId() == R.id.Dialog_Change_Password) {
                    DialogChangePass1();
                }else if (item.getItemId() == R.id.Exit) {
                    startActivity(new Intent(Page_Navigation_Type_E.this,Page_Sign_In.class));
                }

                if (fragment != null){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.Layout_Drawer, fragment).commit();
                    toolbar.setTitle(item.getTitle());
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        if (savedInstanceState != null){
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.Layout_Drawer, new FragPage_Manage_Orders()).commit();
            toolbar.setTitle("Quản lý đơn hàng");
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void DialogChangePass1(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View viewChangePass = layoutInflater.inflate(R.layout.ui_dialog_change_pass, null);
        builder.setView(viewChangePass);
        EdtChange_OldPass = viewChangePass.findViewById(R.id.EdtChange_OldPass);
        EdtChange_NewPass = viewChangePass.findViewById(R.id.EdtChange_NewPass);
        EdtChange_SubmitPass = viewChangePass.findViewById(R.id.EdtChange_SubmitPass);
        Btn_ChangePass = viewChangePass.findViewById(R.id.Btn_ChangePass);
        Btn_Cancel_ChangePass = viewChangePass.findViewById(R.id.Btn_Cancel_ChangePass);

        NhanVienDAO nhanVienDAO = new NhanVienDAO(this);
        SharedPreferences pref = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String email = pref.getString("Email","");
        NhanVien nv = nhanVienDAO.gettTNV(email);
        String olPass = nv.getMatkhau();


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.fill_radius_16));
        Btn_Cancel_ChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        Btn_ChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass =  EdtChange_OldPass.getText().toString();
                String newPass =  EdtChange_NewPass.getText().toString();
                String smPass =  EdtChange_SubmitPass.getText().toString();
                if(EdtChange_NewPass.getText().length() == 0 || EdtChange_SubmitPass.getText().length() == 0|| EdtChange_OldPass.getText().length() == 0){
                    Toast.makeText(Page_Navigation_Type_E.this, "Vui lòng nhập đầy đủ thông tin ", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(!oldPass.equals(olPass)){
                        Toast.makeText(Page_Navigation_Type_E.this, "Mật khẩu cũ không đúng   ", Toast.LENGTH_SHORT).show();
                    }
                    if(!newPass.equals(smPass)){
                        Toast.makeText(Page_Navigation_Type_E.this, "Vui lòng nhập lại mật khẩu mới trùng khớp  ", Toast.LENGTH_SHORT).show();
                    }
                    if(oldPass.equals(olPass)){
                        boolean check = nhanVienDAO.update_NV(nv.getMa(),nv.getAnh(),nv.getTen(),nv.getSdt(),email,newPass,nv.getDiachi());
                        if(check){
                            EdtChange_OldPass.setText("");
                            EdtChange_SubmitPass.setText("");
                            EdtChange_NewPass.setText("");
                            Toast.makeText(Page_Navigation_Type_E.this, " thay đổi mật khẩu thành công  ", Toast.LENGTH_SHORT).show();
                            outAPP();
                            // startActivity(new Intent(getContext().this,Page_Sign_In.class));
                        } else{
                            Toast.makeText(Page_Navigation_Type_E.this, " thay đổi mật khẩu thất bại  ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

    }
    public void outAPP(){
        startActivity(new Intent(Page_Navigation_Type_E.this, Page_Sign_In.class));
    }
}