package com.example.ishop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ishop.Database.DBHelper;
import com.example.ishop.Model.HoaDon;

import java.util.ArrayList;

public class HoaDonDAO {
    private DBHelper dbHelper;
    public HoaDonDAO(Context context){
        dbHelper = new DBHelper(context);
    }

    //lấy danh sách hóa đơn
    public ArrayList<HoaDon> get_HD() {
        ArrayList<HoaDon> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM HOADON", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new HoaDon(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    //thêm hóa đơn
    public boolean add_HD(String maHD, String maDH, String ngay, String maNV, long thanhtien){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maHD", maHD);
        values.put("maDH", maDH);
        values.put("ngayHD", ngay);
        values.put("maNV", maNV);
        values.put("thanhtienDH", thanhtien);
        long check = db.insert("HOADON", null, values);
        return check > 0;
    }


    //sửa hóa đơn
    public boolean update_HD(String maHD, String maDH, String ngay, String maNV, long thanhtien) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maHD", maHD);
        values.put("maDH", maDH);
        values.put("ngayHD", ngay);
        values.put("maNV", maNV);
        values.put("thanhtienDH", thanhtien);
        long check = db.update("HOADON", values, "maHD = ?", new String[]{maHD});
        return check > 0;
    }

    //xóa hóa đơn
    public boolean delete_HD(String ma) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long check = db.delete("HOADON", "maHD = ?", new String[]{ma});
        return check > 0;
    }
}
