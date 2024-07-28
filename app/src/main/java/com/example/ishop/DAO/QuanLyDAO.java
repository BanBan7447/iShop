package com.example.ishop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ishop.Database.DBHelper;
import com.example.ishop.Model.QuanLy;

import java.util.ArrayList;

public class QuanLyDAO {
    private DBHelper dbHelper;

    public QuanLyDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    //lấy danh sách quản lý
    public ArrayList<QuanLy> get_QL() {
        ArrayList<QuanLy> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM QUANLY", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new QuanLy(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    //Kiểm tra quản lý
    public boolean check_QL(String email, String sdt, String matkhau) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM QUANLY WHERE emailQL = ? OR sdtQL = ? AND matkhauQL = ?", new String[]{email, sdt, matkhau});
        return cursor.getCount() > 0;
    }

    //thêm quản lý
    public boolean add_QL(String ma, String ten, String sdt, String email, String matkhau, String diachi) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maQL", ma);
        values.put("tenQL", ten);
        values.put("sdtQL", sdt);
        values.put("emailQL", email);
        values.put("matkhauQL", matkhau);
        values.put("diachiQL", diachi);
        long check = db.insert("QUANLY", null, values);
        return check > 0;
    }

    //sửa quản lý
    public boolean update_QL(String ma, String anh, String ten, String sdt, String email, String matkhau, String diachi) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("anhQL", anh);
        values.put("tenQL", ten);
        values.put("sdtQL", sdt);
        values.put("emailQL", email);
        values.put("matkhauQL", matkhau);
        values.put("diachiQL", diachi);
        long check = db.update("QUANLY", values,"maQL = ?", new String[]{ma});
        return check > 0;
    }

    //xóa quản lý
    public boolean delete_QL(String ma) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long check = db.delete("QUANLY", "maQL = ?", new String[]{ma});
        return check > 0;
    }
}
