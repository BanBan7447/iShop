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
    public ArrayList<KhachHang> get_KH() {
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
    public void add_KH(String ma, String anh, String ten, String sdt, String email, String matkhau, String diachi) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maKH", ma);
        values.put("anhKH", anh);
        values.put("tenKH", ten);
        values.put("sdtKH", sdt);
        values.put("emailKH", email);
        values.put("matkhauKH", matkhau);
        values.put("diachiKH", diachi);
//        long check =
        db.insert("KHACHHANG", null, values);
        db.close();
//        return check > 0;
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
        long check = db.update("KHACHHANG", values,"maKH = ?", new String[]{ma});
        return check > 0;
    }
    //laythongtinkhachhang

        public ArrayList<KhachHang> gettTKH(String email){
            ArrayList<KhachHang> list = new ArrayList<>();
            SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM KHACHHANG WHERE emailKH = ?", new String[]{email});
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

    //xóa khách hàng
    public boolean delete_KH(String ma) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long check = db.delete("KHACHHANG", "maKH = ?", new String[]{ma});
        return check > 0;

}
}

