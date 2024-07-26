package com.example.ishop.model;

public class Khachhang {
    private String maKH;
    private String tenKH;

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public Khachhang(String maKH, String tenKH) {
        this.maKH = maKH;
        this.tenKH = tenKH;
    }
}
