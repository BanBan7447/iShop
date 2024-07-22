package com.example.ishop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ishop.Database.DBHelper;

public class NhanVienDAO {
    private DBHelper dbHelper;

    public NhanVienDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    //Kiểm tra nhân viên
    public boolean check_NV(String email, String sdt, String matkhau) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NHANVIEN WHERE emailNV = ? OR sdtNV = ? AND matkhauNV = ?", new String[]{email, sdt, matkhau});
        return cursor.getCount() > 0;
    }

    //thêm nhân viên
    public boolean add_NV(String ten, String sdt, String email, String matkhau, String diachi) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenNV", ten);
        values.put("sdtNV", sdt);
        values.put("emailNV", email);
        values.put("matkhauNV", matkhau);
        values.put("diachiNV", diachi);
        long check = db.insert("NHANVIEN", null, values);
        return check > 0;
    }
}
