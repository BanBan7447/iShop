package com.example.ishop.Model;

public class LoaiSanPham {
    private String maLSP;
    private String anh;
    private String ten;

    public LoaiSanPham(String ma, String anh, String ten) {
        this.maLSP = ma;
        this.anh = anh;
        this.ten = ten;
    }

    public String getMaLSP() {
        return maLSP;
    }

    public String getAnh() {
        return anh;
    }

    public String getTen() {
        return ten;
    }
}
