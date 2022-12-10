package com.example.du_an_1.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    static final String dbName="GARALOVE";
    static final int dbVersion=1;

    public DbHelper( Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String createTableNhanVien="create table NhanVien(" +
               "maNV text PRIMARY KEY, " +
               "hoTen text NOT NULL," +
               "matKhau text NOT NULL)";
       db.execSQL(createTableNhanVien);

       String createTableKhachHang="create table KhachHang(" +
               "maKH INTEGER PRIMARY KEY AUTOINCREMENT, " +
               "hoTen text NOT NULL, " +
               "gioiTinh text NOT NULL," +
               "soDienThoai INTEGER NOT NULL," +
               "diaChi text NOT NULL, " +
               "loaiXe text NOT NULL)";

       db.execSQL(createTableKhachHang);

     String createTableDichVu="create table DichVu(" +
             "maDV INTEGER PRIMARY KEY AUTOINCREMENT, " +
             "tenDV text NOT NULL)";
     db.execSQL(createTableDichVu);


     String createTableSanPham="create table SanPham(" +
             "maSP INTEGER PRIMARY KEY AUTOINCREMENT, "+
             "tenSP text NOT NULL, " +
             "soLuong INTEGER NOT NULL, " +
             "tonKho INTEGER NOT NULL, " +
             "giaSP INTEGER NOT NULL, " +

             "maDV INTERGER REFERENCES DichVu(maDV))";
     db.execSQL(createTableSanPham);

     String createTableHoaDon="create table HoaDon(" +
             "maHD INTEGER PRIMARY KEY AUTOINCREMENT, " +
             "maNV text REFERENCES NhanVien(maNV), " +
             "maKH INTEGER REFERENCES KhachHang(maKH), "   +
             "maSP INTEGER REFERENCES SanPham(maSP), " +
             "ngay DATE NOT NULL, " +
             "tienSP INTEGER NOT NULL, " +
             "thanhToan INTEGER NOT NULL)";
     db.execSQL(createTableHoaDon);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          String dropTableNhanVien="drop table if exists NhanVien";
          db.execSQL(dropTableNhanVien);
        String dropTableKhachHang="drop table if exists KhachHang";
        db.execSQL(dropTableKhachHang);
        String dropTableDichVu="drop table if exists DichVu";
        db.execSQL(dropTableDichVu);
        String dropTableSanPham="drop table if exists SanPham";
        db.execSQL(dropTableSanPham);
        String dropTableHoaDon="drop table if exists HoaDon";
        db.execSQL(dropTableHoaDon);
    }
}
