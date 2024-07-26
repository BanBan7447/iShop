package com.example.ishop.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.ishop.DAO.KhachHangDAO;
import com.example.ishop.DAO.NhanVienDAO;
import com.example.ishop.DAO.QuanLyDAO;

import java.security.Provider;

public class LoginService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        String email = bundle.getString("email");
        String matkhau = bundle.getString("matkhau");
        //khachhang
        KhachHangDAO khachHangDAO =  new KhachHangDAO(this);
        boolean check = khachHangDAO.checkKH(email,matkhau);
        Intent intentBR = new Intent();
        Bundle bundleBR = new Bundle();
        bundleBR.putBoolean("check", check);
        intentBR.putExtras(bundleBR);
        intentBR.setAction("login");
        sendBroadcast(intentBR);
        //nhanvien
        NhanVienDAO nhanVienDAO = new NhanVienDAO(this);
        boolean checkNV = nhanVienDAO.check_NV(email,"",matkhau);
        Intent intentBR1 = new Intent();
        Bundle bundleBR1 = new Bundle();
        bundleBR1.putBoolean("checkNV", checkNV);
        intentBR1.putExtras(bundleBR1);
        intentBR1.setAction("loginNV");
        sendBroadcast(intentBR1);
        //QuanLy
        QuanLyDAO quanLyDAO = new QuanLyDAO(this);
        boolean checkQL = quanLyDAO.check_QL(email,"",matkhau);
        Intent intentBR2 = new Intent();
        Bundle bundleBR2 = new Bundle();
        bundleBR2.putBoolean("checkQL", checkQL);
        intentBR2.putExtras(bundleBR1);
        intentBR2.setAction("loginQL");
        sendBroadcast(intentBR2);




        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
