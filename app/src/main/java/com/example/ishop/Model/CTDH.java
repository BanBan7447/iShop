package com.example.ishop.Model;

public class CTDH {
    private String maDH;
    private String maSP;
    private int soluong;
    private int dongia;

    public CTDH(String maDH, String maSP, int soluong, int dongia) {
        this.maDH = maDH;
        this.maSP = maSP;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    public String getMaDH() {

        return maDH;
    }

    public String getMaSP() {
        return maSP;
    }

    public int getSoluong() {
        return soluong;
    }

    public int getDongia() {
        return dongia;
    }
}
