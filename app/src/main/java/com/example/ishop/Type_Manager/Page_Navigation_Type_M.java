package com.example.ishop.Type_Manager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.ishop.Fragment_Drawer_Menu.FragPage_Manage_Bill;
import com.example.ishop.Fragment_Drawer_Menu.FragPage_Manage_Customers;
import com.example.ishop.Fragment_Drawer_Menu.FragPage_Manage_Orders;
import com.example.ishop.Fragment_Drawer_Menu.FragPage_Manage_Product;
import com.example.ishop.Fragment_Drawer_Menu.FragPage_Manage_Product_Type;
import com.example.ishop.Fragment_Drawer_Menu.FragPage_Statistical;
import com.example.ishop.R;
import com.google.android.material.navigation.NavigationView;

public class Page_Navigation_Type_M extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

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
                } else if (item.getItemId() == R.id.Fragment_Manage_ProductType) {
                    fragment = new FragPage_Manage_Product_Type();
                } else if (item.getItemId() == R.id.Fragment_Manage_Product) {
                    fragment = new FragPage_Manage_Product();
                } else if (item.getItemId() == R.id.Fragment_Manage_Customers) {
                    fragment = new FragPage_Manage_Customers();
                } else if (item.getItemId() == R.id.Fragment_Statistical) {
                    fragment = new FragPage_Statistical();
                } else if (item.getItemId() == R.id.Dialog_Change_Password) {
                    DialogChangePass();
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

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.Layout_Drawer, new FragPage_Manage_Orders()).commit();
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

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.fill_radius_16));
    }
}