package com.example.du_an_1.model;

import java.util.Date;

public class HoaDon {
    public int MaHD;
    public String MaNV;
    public int MaKH;
    public int MaSP;
    public int tienSP;
    public int soLuongSP;
    public Date ngay;
    public int thanhtoan;

    public HoaDon() {
    }

    public HoaDon(int maHD, String maNV, int maKH, int maSP, int tienSP, int soLuongSP, Date ngay, int thanhtoan) {
        MaHD = maHD;
        MaNV = maNV;
        MaKH = maKH;
        MaSP = maSP;
        this.tienSP = tienSP;
        this.soLuongSP = soLuongSP;
        this.ngay = ngay;
        this.thanhtoan = thanhtoan;
    }

    @Override
    public String toString() {
        return "HoaDon{" +
                "MaHD=" + MaHD +
                ", MaNV='" + MaNV + '\'' +
                ", MaKH=" + MaKH +
                ", MaSP=" + MaSP +
                ", tienSP=" + tienSP +
                ", soLuongSP=" + soLuongSP +
                ", ngay=" + ngay +
                ", thanhtoan=" + thanhtoan +
                '}';
    }
}
