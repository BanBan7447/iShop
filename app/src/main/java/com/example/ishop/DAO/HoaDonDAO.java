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
    public ArrayList<HoaDon> get_ListHD() {
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
                        cursor.getLong(4)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    //lấy hóa đơn theo ma
    public HoaDon get_HD(String maHD) {
        HoaDon hd = new HoaDon();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM HOADON WHERE maHD = ? ", new String[]{maHD});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
                hd = new HoaDon(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getLong(4));
        } else {
            hd = null;
        }
        return hd;
    }

    //thêm hóa đơn
    public boolean add_HD(String maDH, String ngay, String maNV, long thanhtien){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maHD", createIdHD());
        values.put("maDH", maDH);
        values.put("ngayHD", ngay);
        values.put("maNV", maNV);
        values.put("thanhtien", thanhtien);
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
        values.put("thanhtien", thanhtien);
        long check = db.update("HOADON", values, "maHD = ?", new String[]{maHD});
        return check > 0;
    }

    //xóa hóa đơn
    public boolean delete_HD(String ma) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long check = db.delete("HOADON", "maHD = ?", new String[]{ma});
        return check > 0;
    }

    //create idHD
    public String createIdHD(){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM HOADON", null);
        if (cursor.getCount()>0) {
            cursor.moveToLast();
            String s = cursor.getString(0);
            cursor.close();
            return upNumber(s);
        } else {
            return "IB";
        }
    }

    private String upNumber(String s) {
        String st = s.replaceAll("[0-9]", "");
        String number = s.replaceAll("[^0-9]", "");
        if (number.isEmpty()){
            number = "0";
        }
        int n = Integer.parseInt(number) + 1;
        return st + n;
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
