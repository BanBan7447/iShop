package com.example.ishop.Model;

public class KhachHang {
    private String maKH;
    private String anhKH;
    private String tenKH;
    private String sdtKH;
    private String emailKH;
    private String matkhauKH;
    private String diachiKH;

    public KhachHang(String maKH, String anhKH, String tenKH, String sdtKH, String emailKH, String matkhauKH, String diachiKH) {
        this.maKH = maKH;
        this.anhKH = anhKH;
        this.tenKH = tenKH;
        this.sdtKH = sdtKH;
        this.emailKH = emailKH;
        this.matkhauKH = matkhauKH;
        this.diachiKH = diachiKH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getAnhKH() {
        return anhKH;
    }

    public void setAnhKH(String anhKH) {
        this.anhKH = anhKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }

    public String getEmailKH() {
        return emailKH;
    }

    public void setEmailKH(String emailKH) {
        this.emailKH = emailKH;
    }

    public String getMatkhauKH() {
        return matkhauKH;
    }

    public void setMatkhauKH(String matkhauKH) {
        this.matkhauKH = matkhauKH;
    }

    public String getDiachiKH() {
        return diachiKH;
    }

    public void setDiachiKH(String diachiKH) {
        this.diachiKH = diachiKH;
    }
}
