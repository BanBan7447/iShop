package com.example.ishop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ishop.Database.DBHelper;
import com.example.ishop.Model.DonHang;

import java.util.ArrayList;

public class DonHangDAO {
    private DBHelper dbHelper;
    public DonHangDAO(Context context){
        dbHelper = new DBHelper(context);
    }

    //lấy danh sách đơn hàng
    public ArrayList<DonHang> get_DH() {
        ArrayList<DonHang> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM DONHANG", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new DonHang(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    //thêm đơn hàng
    public boolean add_DH(String maDH, String maKH, String ngay, String trangthai, String maNV, long thanhtien){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maDH", maDH);
        values.put("maKH", maKH);
        values.put("ngayDH", ngay);
        values.put("trangthaiDH", trangthai);
        values.put("maNV", maNV);
        values.put("thanhtienDH", thanhtien);
        long check = db.insert("DONHANG", null, values);
        return check > 0;
    }


    //sửa đơn hàng
    public boolean update_DH(String maDH, String maKH, String ngay, String trangthai, String maNV, long thanhtien) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maDH", maDH);
        values.put("maKH", maKH);
        values.put("ngayDH", ngay);
        values.put("trangthaiDH", trangthai);
        values.put("maNV", maNV);
        values.put("thanhtienDH", thanhtien);
        long check = db.update("DONHANG", values, "maDH = ?", new String[]{maDH});
        return check > 0;
    }

    //xóa đơn hàng
    public boolean delete_DH(String ma) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long check = db.delete("DONHANG", "maDH = ?", new String[]{ma});
        return check > 0;
    }
}
