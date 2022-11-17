package com.example.du_an_1.model;

public class KhachHang {
    private int maKH;
    private String tenKH;
    private String gioiTinh;
    private String loaixe;
    private String diachi;

    public KhachHang() {
    }

    public KhachHang(int maKH, String tenKH, String gioiTinh, String loaixe, String diachi) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.gioiTinh = gioiTinh;
        this.loaixe = loaixe;
        this.diachi = diachi;
    }
}
