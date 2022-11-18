package com.example.du_an_1.model;

import java.util.Date;

public class HoaDon {
    public int MaHD;
    public String MaNV;
    public int MaKH;
    public int MaSP;
    public int tienSP;
    public Date ngay;
    public int thanhtoan;

    public HoaDon() {
    }

    public HoaDon(int maHD, String maNV, int maKH, int maSP, int tienSP, Date ngay, int thanhtoan) {
        MaHD = maHD;
        MaNV = maNV;
        MaKH = maKH;
        MaSP = maSP;
        this.tienSP = tienSP;
        this.ngay = ngay;
        this.thanhtoan = thanhtoan;
    }
}
