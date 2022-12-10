package com.example.du_an_1.model;

public class SanPham {
    public int maSP;
    public String tenSP;
    public int soLuong,tonKho,giaSP,maDV;

    public SanPham(int maSP, String tenSP, int soLuong, int tonKho, int giaSP, int maDV) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.tonKho = tonKho;
        this.giaSP = giaSP;
        this.maDV = maDV;
    }

    public SanPham() {
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTonKho() {
        return tonKho;
    }

    public void setTonKho(int tonKho) {
        this.tonKho = tonKho;
    }

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }

    public int getMaDV() {
        return maDV;
    }

    public void setMaDV(int maDV) {
        this.maDV = maDV;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "maSP=" + maSP +
                ", tenSP='" + tenSP + '\'' +
                ", soLuong=" + soLuong +
                ", tonKho=" + tonKho +
                ", giaSP=" + giaSP +
                ", maDV=" + maDV +
                '}';
    }
}
