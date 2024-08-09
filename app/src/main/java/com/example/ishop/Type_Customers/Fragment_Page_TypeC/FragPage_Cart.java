package com.example.ishop.Type_Customers.Fragment_Page_TypeC;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ishop.Activity_Manage.Page_Detail_History_Orders;
import com.example.ishop.Activity_Manage.Page_History_Orders;
import com.example.ishop.Adapter.CartAdapter;
import com.example.ishop.R;
import com.example.ishop.Type_Customers.Page_Payment;
import com.example.ishop.Utils.Utils;

public class FragPage_Cart extends Fragment {
    private RecyclerView rcv;
    private CartAdapter cartAdapter;
    private Button btn_order;
    private TextView Quantity_Cart_Product;
    private Runnable runnable;
    private Handler handler;
    private ImageButton icon_history;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.ui_frag_page_cart, container, false);

        Quantity_Cart_Product = view.findViewById(R.id.Quantity_Cart_Product);
        btn_order = view.findViewById(R.id.btn_order);
        icon_history = view.findViewById(R.id.icon_history);
        rcv = view.findViewById(R.id.rcv_list_product_cart);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                updateCartData();
                handler.postDelayed(this, 500);
            }
        };

        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.listGioHang.isEmpty()) {
                    Toast.makeText(getContext(), "Không có sản phẩm", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(getContext(), Page_Payment.class));
                }
            }
        });

        icon_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Page_History_Orders.class));
            }
        });
        return view;
    }

    private void updateCartData() {
        //
        if (cartAdapter == null) {
            cartAdapter = new CartAdapter(getContext(), Utils.listGioHang);
            rcv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            rcv.setAdapter(cartAdapter);
        } else {
            // Cập nhật dữ liệu nếu adapter đã được khởi tạo
            cartAdapter.notifyDataSetChanged();
        }
        rcv.scrollToPosition(0);
        Quantity_Cart_Product.setText(String.valueOf(cartAdapter.getItemCount()));
    }

    @Override
    public void onResume() {
        super.onResume();
        // Bắt đầu chạy Runnable khi fragment hiển thị
        handler.post(runnable);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Dừng chạy Runnable khi fragment không còn hiển thị
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Dọn dẹp handler và runnable để tránh rò rỉ bộ nhớ
        handler.removeCallbacks(runnable);
    }
}