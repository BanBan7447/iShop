package com.example.ishop;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Demo_ToolBar_Search extends AppCompatActivity {
    ListView listView;
    String[] name = {"Hồng Hạnh", "Bảo Trân", "Ngọc Thuận", "Giáng Sinh", "Ngọc Ánh",
                    "Mộc Thảo", "Thu Hà", "Linh Sim", "Phương Anh", "Minh Thư"};

    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_tool_bar_search);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.statusBarColor));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        listView = findViewById(R.id.ListSearch);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, name);

        listView.setAdapter(arrayAdapter);

        Toolbar toolbar = findViewById(R.id.Toolbar_Page);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tool_bar, menu);
        MenuItem menuItem = menu.findItem(R.id.active_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Tìm kiếm sản phẩm");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter().filter(newText);


                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}