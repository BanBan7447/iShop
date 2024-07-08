package com.example.ishop.Type_Customers;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.ishop.R;
import com.example.ishop.Type_Customers.Fragment_Page_TypeC.FragPage_Cart;
import com.example.ishop.Type_Customers.Fragment_Page_TypeC.FragPage_Home;
import com.example.ishop.Type_Customers.Fragment_Page_TypeC.FragPage_Notification;
import com.example.ishop.Type_Customers.Fragment_Page_TypeC.FragPage_Profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Page_Navigation_Type_C extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_page_navigation_type_c);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.statusBarColor));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        BottomNavigationView bottomNavigationView = findViewById(R.id.Bottom_Navigation_Type_C);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.Layout_Bottom, new FragPage_Home()).commit();

        //bottomNavigationView.setSelectedItemId(R.id.Fragment_Home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId() == R.id.Fragment_Home){
                    fragment = new FragPage_Home();
                } else if (item.getItemId() == R.id.Fragment_Cart) {
                    fragment = new FragPage_Cart();
                } else if (item.getItemId() == R.id.Fragment_Notification) {
                    fragment = new FragPage_Notification();
                }else {
                    fragment = new FragPage_Profile();
                }

                if (fragment != null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.Layout_Bottom, fragment).commit();
                }

                //bottomNavigationView.getMenu().findItem(item.getItemId()).setChecked(true);
                return true;
            }
        });

    }
}