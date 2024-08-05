package com.example.ishop.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ishop.Database.DBHelper;
import com.example.ishop.Model.DonHang;
import com.example.ishop.Model.KhachHang;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DonHangDAO {
    private DBHelper dbHelper;
    public DonHangDAO(Context context){
        dbHelper = new DBHelper(context);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    }

    //lấy danh sách đơn hàng
    public ArrayList<DonHang> get_ListDH() {
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

    //lấy lay don hàng theo maDH
    public DonHang get_DH(String ma) {
        DonHang dh = new DonHang();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM DONHANG WHERE maDH = ?", new String[]{ma});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            dh = new DonHang(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getLong(5));
        }
        cursor.close();
        return dh;
    }

    //thêm đơn hàng
    public boolean add_DH( String maKH, String ngay, String trangthai, String maNV, long thanhtien){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maDH", createIdDH());
        values.put("maKH", maKH);
        values.put("ngayDH", ngay);
        values.put("trangthaiDH", trangthai);
        values.put("maNV", maNV);
        values.put("thanhtien", thanhtien);
        long check = db.insert("DONHANG", null, values);
        return check > 0;
    }


    //sửa đơn hàng
    public boolean update_DH(String maDH,String maKH, String ngay, String trangthai, String maNV, long thanhtien) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maKH", maKH);
        values.put("ngayDH", ngay);
        values.put("trangthaiDH", trangthai);
        values.put("maNV", maNV);
        values.put("thanhtien", thanhtien);
        long check = db.update("DONHANG", values, "maDH = ?", new String[]{maDH});
        return check > 0;
    }

    //xóa đơn hàng
    public boolean delete_DH(String ma) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long check = db.delete("DONHANG", "maDH = ?", new String[]{ma});
        return check > 0;
    }

    //create idDH
    public String createIdDH(){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM DONHANG", null);
        if (cursor.getCount()>0) {
            cursor.moveToLast();
            String s = cursor.getString(0);
            cursor.close();
            return upNumber(s);
        } else {
            return "IO101";
        }
    }

    private String upNumber(String s) {
        String st = s.replaceAll("[0-9]", "");
        String number = s.replaceAll("[^0-9]", "");
        int n = Integer.parseInt(number) + 1;
        return st + n;
    }
    public  int get_sDH() {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM DONHANG", null);
        int a = cursor.getCount();
        return a;
    }

    public  int get_sDH1(String ngay) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM DONHANG WHERE ngayDH=?", new String[]{ngay});
        int a = cursor.getCount();
        return a;
    }

    //tong doanh thu don hang` tron 1ngay`
    @SuppressLint("Range")
    public int getDoanhthu(String ngay){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sqlDoangThu1 = "SELECT  SUM(thanhtien) as doanhthu1 FROM DONHANG WHERE ngayDH = ?";
        List<Integer> list = new ArrayList<Integer>();
        Cursor c = db.rawQuery(sqlDoangThu1, new String[]{ngay});
        while(c.moveToNext()){
            try{
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhthu1"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }

}
