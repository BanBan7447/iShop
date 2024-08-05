package com.example.ishop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ishop.Database.DBHelper;
import com.example.ishop.Model.SanPham;

import java.util.ArrayList;

public class SanPhamDAO {
    private DBHelper dbHelper;

    public SanPhamDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    //lấy danh sách sản phẩm
    public ArrayList<SanPham> get_SP() {
        ArrayList<SanPham> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SANPHAM", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new SanPham(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        cursor.getString(6)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    //lấy sanpham theo maSP
    public SanPham get_SPP(String ma) {
        SanPham sp = new SanPham();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SANPHAM WHERE masp = ?", new String[]{ma});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            sp = new SanPham(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getInt(5),
                    cursor.getString(6));
        }
        return sp;
    }

    //thêm sản phẩm
    public boolean add_SP(String anh, String ten, int gia, String mota, int soluong, String maLSP) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maSP", createIdSP(maLSP));
        values.put("anhSP", anh);
        values.put("tenSP", ten);
        values.put("giaSP", gia);
        values.put("motaSP", mota);
        values.put("soluong", soluong);
        values.put("maLSP", maLSP);
        long check = db.insert("SANPHAM", null, values);
        return check > 0;
    }


    //sửa sản phẩm
    public boolean update_SP(String maSP, String anh, String ten, int gia, String mota, int soluong, String maLSP) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        values.put("maSP", maSP);
        values.put("anhSP", anh);
        values.put("tenSP", ten);
        values.put("giaSP", gia);
        values.put("motaSP", mota);
        values.put("soluong", soluong);
        values.put("maLSP", maLSP);
        long check = db.update("SANPHAM", values, "maSP = ?", new String[]{maSP});
        return check > 0;
    }

    //xóa sản phẩm
    public boolean delete_SP(String ma) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long check = db.delete("SANPHAM", "maSP = ?", new String[]{ma});
        return check > 0;
    }

    //create idSP
    public String createIdSP(String maLSP) {
        String s = creatmaLSP(maLSP);
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SANPHAM WHERE maSP = ?", new String[]{s + "*"});
        if (cursor.getCount() > 0) {
            cursor.moveToLast();
            String st = cursor.getString(0);
            cursor.close();
            return upNumber(st);
        } else {

            return s + 1;
        }
    }

    private String creatmaLSP(String maLSP) {
        String s = "";
        if (maLSP.equals("IP27"))
            s = "IPE";
        if (maLSP.equals("IA10"))
            s = "IPA";
        if (maLSP.equals("IM84"))
            s = "IPM";
        if (maLSP.equals("IW15"))
            s = "IPW";
        if (s.isEmpty()) {
            return maLSP;
        } else
            return s;
    }

    private String upNumber(String s) {
        String st = s.replaceAll("[0-9]", "");
        String number = s.replaceAll("[^0-9]", "");
        int n = Integer.parseInt(number) + 1;
        return st + n;
    }
}
