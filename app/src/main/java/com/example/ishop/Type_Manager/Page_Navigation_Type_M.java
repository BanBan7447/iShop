package com.example.ishop.Type_Manager;

import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import com.example.ishop.DAO.QuanLyDAO;
import com.example.ishop.Fragment_Drawer_Menu.FragPage_Manage_Bill;
import com.example.ishop.Fragment_Drawer_Menu.FragPage_Manage_Customers;
import com.example.ishop.Fragment_Drawer_Menu.FragPage_Manage_Orders;
import com.example.ishop.Fragment_Drawer_Menu.FragPage_Manage_Product;
import com.example.ishop.Fragment_Drawer_Menu.FragPage_Profile_m;
import com.example.ishop.Fragment_Drawer_Menu.FragPage_Statistical;
import com.example.ishop.Model.QuanLy;
import com.example.ishop.Page_Sign_In;
import com.example.ishop.R;
import com.google.android.material.navigation.NavigationView;

public class Page_Navigation_Type_M extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    EditText EdtChange_OldPass,EdtChange_NewPass,EdtChange_SubmitPass;
    Button Btn_ChangePass,Btn_Cancel_ChangePass;
    private int pQL;
    TextView Data_Code_Manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_page_navigation_type_m);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.statusBarColor));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


        drawerLayout = findViewById(R.id.Drawer_Layout);
        Toolbar toolbar = findViewById(R.id.Drawer_Toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_hamburger_menu);

        navigationView = findViewById(R.id.Drawer_TypeM);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;
                if (item.getItemId() == R.id.Fragment_Manage_Orders){
                    fragment = new FragPage_Manage_Orders();
                } else if (item.getItemId() == R.id.Fragment_Manage_Bill) {
                    fragment = new FragPage_Manage_Bill();
                } else if (item.getItemId() == R.id.Fragment_Manage_Product) {
                    fragment = new FragPage_Manage_Product();
                } else if (item.getItemId() == R.id.Fragment_Manage_Customers) {
                    fragment = new FragPage_Manage_Customers();
                } else if (item.getItemId() == R.id.Fragment_Statistical) {
                    fragment = new FragPage_Statistical();
                } else if (item.getItemId() == R.id.Dialog_Change_Password) {
                        DialogChangePass();
                }else if(item.getItemId() == R.id.Exit){
                    startActivity(new Intent(Page_Navigation_Type_M.this,Page_Sign_In.class));
                }else if(item.getItemId() == R.id.proFile){
                    fragment = new FragPage_Profile_m();
                }

                if (fragment != null){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.Layout_DrawerM, fragment).commit();
                    toolbar.setTitle(item.getTitle());
                }
                drawerLayout.closeDrawer(GravityCompat.START);

                return false;
            }
        });


        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.Layout_DrawerM, new FragPage_Manage_Orders()).commit();
            toolbar.setTitle("Quản lý đơn hàng");
            //navigationView.setCheckedItem(R.id.Fragment_Manage_Orders);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void DialogChangePass(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View viewChangePass = layoutInflater.inflate(R.layout.ui_dialog_change_pass, null);
        builder.setView(viewChangePass);
        EdtChange_OldPass = viewChangePass.findViewById(R.id.EdtChange_OldPass);
        EdtChange_NewPass = viewChangePass.findViewById(R.id.EdtChange_NewPass);
        EdtChange_SubmitPass = viewChangePass.findViewById(R.id.EdtChange_SubmitPass);
        Btn_ChangePass = viewChangePass.findViewById(R.id.Btn_ChangePass);
        Btn_Cancel_ChangePass = viewChangePass.findViewById(R.id.Btn_Cancel_ChangePass);

        QuanLyDAO quanLyDAO = new QuanLyDAO(this);
        SharedPreferences pref = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String email = pref.getString("Email","");
        QuanLy ql = quanLyDAO.gettTQL(email);

        String olPass = ql.getMatkhau();


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
                    Toast.makeText(Page_Navigation_Type_M.this, "Vui lòng nhập đầy đủ thông tin ", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(!oldPass.equals(olPass)){
                        Toast.makeText(Page_Navigation_Type_M.this, "Mật khẩu cũ không đúng   ", Toast.LENGTH_SHORT).show();
                    }
                    if(!newPass.equals(smPass)){
                        Toast.makeText(Page_Navigation_Type_M.this, "Vui lòng nhập lại mật khẩu mới trùng khớp  ", Toast.LENGTH_SHORT).show();
                    }
                    if(oldPass.equals(olPass)){
                        boolean check = quanLyDAO.update_QL(ql.getMa(),ql.getAnh(),ql.getTen(),ql.getSdt(),email,newPass,ql.getDiachi());
                        if(check){
                            EdtChange_OldPass.setText("");
                            EdtChange_SubmitPass.setText("");
                            EdtChange_NewPass.setText("");
                            Toast.makeText(Page_Navigation_Type_M.this, " thay đổi mật khẩu thành công  ", Toast.LENGTH_SHORT).show();
                            outAPP();
                            // startActivity(new Intent(getContext().this,Page_Sign_In.class));
                        } else{
                        Toast.makeText(Page_Navigation_Type_M.this, " thay đổi mật khẩu thất bại  ", Toast.LENGTH_SHORT).show();
                    }
                    }
                }
            }
        });

    }
    public void outAPP(){
        startActivity(new Intent(Page_Navigation_Type_M.this, Page_Sign_In.class));
    }
    public View ttQL(){
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.ui_header_drawer_type_m, null);
        Data_Code_Manager = view.findViewById(R.id.Data_Code_Manager);
        QuanLyDAO quanLyDAO = new QuanLyDAO(this);
        SharedPreferences pref = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String email = pref.getString("Email","");
        QuanLy ql = quanLyDAO.gettTQL(email);
        Data_Code_Manager.setText("hihiihihi");
        return view;
    }

}