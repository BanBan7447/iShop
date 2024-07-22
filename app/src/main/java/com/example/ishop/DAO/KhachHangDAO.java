package com.example.ishop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ishop.Database.DBHelper;

public class KhachHangDAO {
    private DBHelper dbHelper;

    public KhachHangDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    //Kiểm tra khách hàng
    public boolean check_KH(String email, String sdt, String matkhau) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM KHACHHANG WHERE emailKH = ? OR sdtKH = ? AND matkhauKH = ?", new String[]{email, sdt, matkhau});
        return cursor.getCount() > 0;
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
}
