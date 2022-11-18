package com.example.du_an_1.model;

public class DichVu {
    public int maDV;
    public String tenDV;

    public DichVu(int maDV, String tenDV) {
        this.maDV = maDV;
        this.tenDV = tenDV;
    }

    public DichVu() {
    }

    public int getMaDV() {
        return maDV;
    }

    public void setMaDV(int maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    @Override
    public String toString() {
        return "DichVu{" +
                "maDV=" + maDV +
                ", tenDV='" + tenDV + '\'' +
                '}';
    }
}
