package com.example.ishop.Model;

public class SanPham {
    private String maSP;
    private String anh;
    private String ten;
    private int gia;
    private String mota;
    private int soluong;
    private String maLSP;

    public SanPham(String maSP, String anh, String ten, int gia, String mota, int soluong, String maLSP) {
        this.maSP = maSP;
        this.anh = anh;
        this.ten = ten;
        this.gia = gia;
        this.mota = mota;
        this.soluong = soluong;
        this.maLSP = maLSP;
    }

    public SanPham() {
    }

    public String getMaSP() {
        return maSP;
    }

    public String getAnh() {
        return anh;
    }

    public String getTen() {
        return ten;
    }

    public int getGia() {
        return gia;
    }

    public String getMota() {
        return mota;
    }

    public int getSoluong() {
        return soluong;
    }

    public String getMaLSP() {
        return maLSP;
    }
}
