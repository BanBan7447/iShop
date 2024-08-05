package com.example.ishop.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ishop.Database.DBHelper;
import com.example.ishop.Model.HoaDon;
import com.example.ishop.Model.SanPham;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    private DBHelper dbHelper;
    public HoaDonDAO(Context context){
        dbHelper = new DBHelper(context);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
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
    //tong doanh thu
    @SuppressLint("Range")
    public int getDoanhthu(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sqlDoanhThu= "SELECT  SUM(thanhtien) as doanhthu FROM HOADON ";
        List<Integer> list = new ArrayList<Integer>();
        Cursor c = db.rawQuery(sqlDoanhThu, null);
        while(c.moveToNext()){
            try{
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhthu"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }
    //Doanhthu theo ngay

    @SuppressLint("Range")
    public int getDoanhthuD(String tuNgay, String denNgay){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sqlDoanhThu2 = "SELECT  SUM(thanhtien) as doanhthu2 FROM HOADON WHERE ngayHD BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<Integer>();
        Cursor c = db.rawQuery(sqlDoanhThu2, new String[]{tuNgay,denNgay});
        while(c.moveToNext()){
            try{
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhthu2"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }

}
