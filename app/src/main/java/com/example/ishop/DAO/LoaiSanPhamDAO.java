package com.example.ishop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ishop.Database.DBHelper;
import com.example.ishop.Model.LoaiSanPham;

import java.util.ArrayList;

public class LoaiSanPhamDAO {
    private DBHelper dbHelper;

    public LoaiSanPhamDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    //lấy danh sách loại sản phẩm
    public ArrayList<LoaiSanPham> get_LSP() {
        ArrayList<LoaiSanPham> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAISANPHAM", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new LoaiSanPham(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    //lấy ten loại sản phẩm
    public String get_nameLSP(String maLSP) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAISANPHAM WHERE maLSP = ? ", new String[]{maLSP});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getString(2);
        }
        return "";
    }


    //thêm loại sản phẩm
    public boolean add_LSP(String maLSP, String anh, String ten) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maLSP", maLSP);
        values.put("anhLSP", anh);
        values.put("tenLSP", ten);
        long check = db.insert("LOAISANPHAM", null, values);
        return check > 0;
    }

    //check maLSP
    public boolean checkLSP(String maLSP) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAISANPHAM WHERE maLSP = ?", new String[]{maLSP});
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    //sửa loại sản phẩm
    public boolean update_LSP(String ma, String anh, String ten) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("anhLSP", anh);
        values.put("tenLSP", ten);
        long check = db.update("LOAISANPHAM", values, "maLSP = ?", new String[]{ma});
        return check > 0;
    }

    //xóa loại sản phẩm
    public boolean delete_LSP(String ma) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long check = db.delete("LOAISANPHAM", "maLSP = ?", new String[]{ma});
        return check > 0;
    }


}

