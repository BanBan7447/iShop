package com.example.ishop.Model;

public class HoaDon {
    private String maHD;
    private String maDH;
    private String ngay;
    private String maNV;
    private long thanhtien;

    public HoaDon(String maHD, String maDH, String ngay, String maNV, long thanhtien) {
        this.maHD = maHD;
        this.maDH = maDH;
        this.ngay = ngay;
        this.maNV = maNV;
        this.thanhtien = thanhtien;
    }

    public HoaDon() {
    }

    public String getMaHD() {
        return maHD;
    }

    public String getMaDH() {
        return maDH;
    }

    public String getNgay() {
        return ngay;
    }

    public String getMaNV() {
        return maNV;
    }

    public long getThanhtien() {
        return thanhtien;
    }
}
