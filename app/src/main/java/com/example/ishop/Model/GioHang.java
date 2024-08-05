package com.example.ishop.Model;

public class GioHang {
    private String maKH;
    private String anhSP;
    private String tenSP;
    private String tenLSP;
    private int gia;
    private int sl;

    public GioHang(String maKH, String anhSP, String tenSP, String tenLSP, int gia, int sl) {
        this.maKH = maKH;
        this.anhSP = anhSP;
        this.tenSP = tenSP;
        this.tenLSP = tenLSP;
        this.gia = gia;
        this.sl = sl;
    }

    public GioHang() {
    }

    public String getMaKH() {
        return maKH;
    }

    public String getAnhSP() {
        return anhSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public String getTenLSP() {
        return tenLSP;
    }

    public int getGia() {
        return gia;
    }

    public int getSl() {
        return sl;
    }
}
