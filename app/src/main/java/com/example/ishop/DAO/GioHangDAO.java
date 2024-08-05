package com.example.ishop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ishop.Database.DBHelper;
import com.example.ishop.Model.GioHang;
import com.example.ishop.Model.SanPham;

import java.util.ArrayList;

public class GioHangDAO {
    private DBHelper dbHelper;

    public GioHangDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    //lấy danh sách sản phẩm của khách
    public ArrayList<GioHang> get_listSP(String maKH) {
        ArrayList<GioHang> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM GIOHANG WHERE maKH = ? ", new String[]{maKH});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new GioHang(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getInt(5)));
            } while (cursor.moveToNext());
        }
        return list;
    }


    //thêm sản phẩm vao gio hàng
    public boolean add_SP(String maKH, String anh, String tensp, String tenlsp, int gia, int sl) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maKH", maKH);
        values.put("anhSP", anh);
        values.put("tenSP", tensp);
        values.put("tenLSP", tenlsp);
        values.put("gia", gia);
        values.put("soluong", sl);
        long check = db.insert("GIOHANG", null, values);
        return check > 0;
    }
}
