package com.example.du_an_1.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1.model.SanPham;
import com.example.du_an_1.model.Top;
import com.example.du_an_1.sql.DbHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DoanhThuDAO {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public DoanhThuDAO(Context context){
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public List<Top> getTops() {
        String sqlTop = "SELECT maSP, count(maSP)" +
                "as soLuong FROM HoaDon GROUP BY MaSP ORDER BY " +
                "soLuong DESC LIMIT 10";
        List<Top> list = new ArrayList<>();
        SanPhamDAO sanPhamDAO = new SanPhamDAO(context);
        Cursor c = db.rawQuery(sqlTop, null);
        while (c.moveToNext()) {
            Top top = new Top();
            SanPham sanPham = sanPhamDAO.getID(c.getString(c.getColumnIndex("soLuong")));
            top.tenSP = sanPham.tenSP;
            top.soLuong = Integer.parseInt(c.getString(c.getColumnIndex("soLuong")));
            list.add(top);
        }
        return list;
    }
    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay){
        String sqlDoanhThu = "SELECT SUM(tienSua)" +
                "as doanhThu FROM HoaDon WHERE ngay BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<>();
        Cursor c = db.rawQuery(sqlDoanhThu, new String[]{tuNgay,denNgay});
        while (c.moveToNext()){
            try {
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }
}
