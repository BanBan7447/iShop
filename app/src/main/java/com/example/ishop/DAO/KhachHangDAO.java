package com.example.ishop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ishop.Database.DBHelper;
import com.example.ishop.model.Khachhang;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class KhachHangDAO {
    private DBHelper dbHelper;

    public KhachHangDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    //Kiểm tra khách hàng
    public boolean checkKH(String email, String matkhau) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM KHACHHANG WHERE emailKH = ?  AND matkhauKH = ?", new String[]{email, matkhau});
       if(cursor.getCount() !=0){
           return true;
       }
       return false;
    }

    //thêm khách hàng
    public boolean add_KH(String ten, String sdt, String email, String matkhau, String diachi) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenKH", ten);
        values.put("sdtKH", sdt);
        values.put("emailKH", email);
        values.put("matkhauKH", matkhau);
        values.put("diachiKH", diachi);
        long check = db.insert("KHACHHANG", null, values);
        return check > 0;
    }
    public ArrayList<Khachhang>getDS(){
        ArrayList<Khachhang> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM KHACHHANG", null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
    list.add(new Khachhang(cursor.getString(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }

        return list;
    }
}
