package com.example.ishop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ishop.Database.DBHelper;
import com.example.ishop.Model.KhachHang;
import com.example.ishop.Model.NhanVien;

import java.util.ArrayList;

public class NhanVienDAO {
    private DBHelper dbHelper;

    public NhanVienDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    //lấy danh sách nhân viên
    public ArrayList<NhanVien> get_ListNV() {
        ArrayList<NhanVien> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NHANVIEN", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new NhanVien(cursor.getString(0),
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

    //lay thong tin NV theo email
    public NhanVien get_NV(String email) {
        NhanVien nv = new NhanVien();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NHANVIEN WHERE emailNV = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            nv = new NhanVien(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );
        } else {
            nv = null;
        }
        return nv;
    }

    //lay thong tin NV theo ma
    public NhanVien get_NV2(String ma) {
        NhanVien nv = new NhanVien();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NHANVIEN WHERE maNV = ?", new String[]{ma});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            nv = new NhanVien(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );
        }
        return nv;
    }

    //Kiểm tra nhân viên
    public boolean check_NV(String email, String sdt, String matkhau) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NHANVIEN WHERE emailNV = ? OR sdtNV = ? AND matkhauNV = ?", new String[]{email, sdt, matkhau});
        return cursor.getCount() > 0;
    }

    //thêm nhân viên
    public boolean add_NV(String anh, String ten, String sdt, String email, String matkhau, String diachi) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maNV", createIdNV());
        values.put("anhNV", anh);
        values.put("tenNV", ten);
        values.put("sdtNV", sdt);
        values.put("emailNV", email);
        values.put("matkhauNV", matkhau);
        values.put("diachiNV", diachi);
        long check = db.insert("NHANVIEN", null, values);
        return check > 0;
    }

    //sửa nhân viên
    public boolean update_NV(String ma, String anh, String ten, String sdt, String email, String matkhau, String diachi) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("anhNV", anh);
        values.put("tenNV", ten);
        values.put("sdtNV", sdt);
        values.put("emailNV", email);
        values.put("matkhauNV", matkhau);
        values.put("diachiNV", diachi);
        long check = db.update("NHANVIEN", values,"maNV = ?", new String[]{ma});
        return check > 0;
    }

    //xóa nhân viên
    public boolean delete_NV(String ma) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long check = db.delete("NHANVIEN", "maNV = ?", new String[]{ma});
        return check > 0;
    }

    //create idNV
    public String createIdNV() {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NHANVIEN", null);
        if (cursor.getCount() > 0) {
            cursor.moveToLast();
            String s = cursor.getString(0);
            cursor.close();
            return upNumber(s);
        } else {
            return "IE101";
        }
    }

    private String upNumber(String s) {
        String st = s.replaceAll("[0-9]", "");
        String number = s.replaceAll("[^0-9]", "");
        int n = Integer.parseInt(number) + 1;
        return st + n;
    }
    public NhanVien gettTNV(String email) {
        NhanVien nv = new NhanVien();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NHANVIEN WHERE emailNV = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            nv = new NhanVien(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );
        } else {
            nv = null;
        }
        return nv;
    }
}
