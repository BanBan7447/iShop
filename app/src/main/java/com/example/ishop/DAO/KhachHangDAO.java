package com.example.ishop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ishop.Database.DBHelper;
import com.example.ishop.Model.KhachHang;

import java.util.ArrayList;

public class KhachHangDAO {
    private DBHelper dbHelper;

    public KhachHangDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    //lấy danh sách khách hàng
    public ArrayList<KhachHang> get_ListKH() {
        ArrayList<KhachHang> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM KHACHHANG", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new KhachHang(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                        ));
            } while (cursor.moveToNext());
        }
        return list;
    }

    //lấy khách hàng theo maKH
    public KhachHang get_KH(String ma) {
        KhachHang kh = new KhachHang();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM KHACHHANG WHERE maKH = ?", new String[]{ma});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            kh = new KhachHang(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6));
        } else {
            kh = null;
        }
        return kh;
    }

    //laythongtinkhachhang
    public KhachHang gettTKH(String email) {
        KhachHang kh = new KhachHang();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM KHACHHANG WHERE emailKH = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            kh = new KhachHang(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );
        } else {
            kh = null;
        }
        return kh;
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
    public void add_KH(String anh, String ten, String sdt, String email, String matkhau, String diachi) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maKH", createIdKH());
        values.put("anhKH", anh);
        values.put("tenKH", ten);
        values.put("sdtKH", sdt);
        values.put("emailKH", email);
        values.put("matkhauKH", matkhau);
        values.put("diachiKH", diachi);
        long check = db.insert("KHACHHANG", null, values);

    }

    //sửa khách hàng
    public boolean update_KH(String ma, String anh, String ten, String sdt, String email, String matkhau, String diachi) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("anhKH", anh);
        values.put("tenKH", ten);
        values.put("sdtKH", sdt);
        values.put("emailKH", email);
        values.put("matkhauKH", matkhau);
        values.put("diachiKH", diachi);
        long check = db.update("KHACHHANG", values,"emailKH = ?", new String[]{email});
        return check > 0;
    }

    public int updatePass(String email, String matkhau){
        ContentValues values = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        values.put("hoten", email);
        values.put("hoten", matkhau);
        return db.update("KHACHHANG", values, "emailKH = ?", new String[]{email});


    }

    //xóa khách hàng
    public boolean delete_KH(String ma) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long check = db.delete("KHACHHANG", "maKH = ?", new String[]{ma});
        return check > 0;
    }

    //create idKH
    public String createIdKH() {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM KHACHHANG", null);
        if (cursor.getCount() > 0) {
            cursor.moveToLast();
            String s = cursor.getString(0);
            cursor.close();
            return upNumber(s);
        } else {
            return "IC1001";
        }
    }
    private String upNumber(String s) {
        String st = s.replaceAll("[0-9]", "");
        String number = s.replaceAll("[^0-9]", "");
        int n = Integer.parseInt(number) + 1;
        return st + n;
    }
}
