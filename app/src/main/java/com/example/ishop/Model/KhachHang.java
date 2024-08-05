package com.example.ishop.Model;

public class KhachHang {
    private String ma;
    private String anh;
    private String ten;
    private String sdt;
    private String email;
    private String matkhau;
    private String diachi;

    public KhachHang(String ma, String anh, String ten, String sdt, String email, String matkhau, String diachi) {
        this.ma = ma;
        this.anh = anh;
        this.ten = ten;
        this.sdt = sdt;
        this.email = email;
        this.matkhau = matkhau;
        this.diachi = diachi;
    }

    public KhachHang() {
    }

    public String getMa() {
        return ma;
    }

    public String getAnh() {
        return anh;
    }

    public String getTen() {
        return ten;
    }

    public String getSdt() {
        return sdt;
    }

    public String getEmail() {
        return email;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public String getDiachi() {
        return diachi;
    }
}
