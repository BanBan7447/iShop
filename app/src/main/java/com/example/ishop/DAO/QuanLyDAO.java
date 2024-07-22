package com.example.ishop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ishop.Database.DBHelper;

public class QuanLyDAO {
    private DBHelper dbHelper;

    public QuanLyDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    //Kiểm tra quản lý
    public boolean check_QL(String email, String sdt, String matkhau) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM QUANLY WHERE emailQL = ? OR sdtQL = ? AND matkhauQL = ?", new String[]{email, sdt, matkhau});
        return cursor.getCount() > 0;
    }

    //thêm quản lý
    public boolean add_QL(String ten, String sdt, String email, String matkhau, String diachi) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenQL", ten);
        values.put("sdtQL", sdt);
        values.put("emailQL", email);
        values.put("matkhauQL", matkhau);
        values.put("diachiQL", diachi);
        long check = db.insert("QUANLY", null, values);
        return check > 0;
    }
}
