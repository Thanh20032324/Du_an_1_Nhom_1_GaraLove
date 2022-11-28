package com.example.du_an_1.model;

public class NhanVien {
    public int id;
    public String maNV;
    public String hoTen;
    public String matKhau;

    public NhanVien() {
    }

    public NhanVien(int id, String maNV, String hoTen, String matKhau) {
        this.id = id;
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
    }
}
