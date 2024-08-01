package com.example.ishop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ishop.Database.DBHelper;
import com.example.ishop.Model.CTDH;

import java.util.ArrayList;

public class CTDHDAO {
    private DBHelper dbHelper;

    public CTDHDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    //lấy danh sách CTDH
    public ArrayList<CTDH> get_CTDH(String maDH) {
        ArrayList<CTDH> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM CTDH", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                if (cursor.getString(0).equals(maDH)) {
                    list.add(new CTDH(cursor.getString(0),
                            cursor.getString(1),
                            cursor.getInt(2),
                            cursor.getInt(3)));
                }
            } while (cursor.moveToNext());
        }
        return list;
    }

    //thêm CTDH
    public boolean add_CTDH(String maDH, String maSP, int soluong, int dongia) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maDH", maDH);
        values.put("maSP", maSP);
        values.put("soluong", soluong);
        values.put("dongia", dongia);
        long check = db.insert("CTDH", null, values);
        return check > 0;
    }

    //sửa CTDH
    public boolean update_CTDH(String maDH, String maSP, int soluong, int dongia) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maDH", maDH);
        values.put("maSP", maSP);
        values.put("soluong", soluong);
        values.put("dongia", dongia);
        long check = db.update("CTDH", values, "maCTDH = ?", new String[]{maDH});
        return check > 0;
    }

    //xóa CTDH
    public boolean delete_CTDH(String maDH, String maSP) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long check = db.delete("CTDH", "maDH = ? AND maSP = ?", new String[]{maDH, maSP});
        return check > 0;
    }
}
