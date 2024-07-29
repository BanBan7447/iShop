package com.example.ishop.Model;

public class DonHang {
    private String maDH;
    private String maKH;
    private String ngay;
    private String trangthai;
    private String maNV;
    private long thanhtien;

    public DonHang(String maDH, String maKH, String ngay, String trangthai, String maNV, long thanhtien) {
        this.maDH = maDH;
        this.maKH = maKH;
        this.ngay = ngay;
        this.trangthai = trangthai;
        this.maNV = maNV;
        this.thanhtien = thanhtien;
    }

    public String getMaDH() {
        return maDH;
    }

    public String getMaKH() {
        return maKH;
    }

    public String getNgay() {
        return ngay;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public String getMaNV() {
        return maNV;
    }

    public long getThanhtien() {
        return thanhtien;
    }
}
