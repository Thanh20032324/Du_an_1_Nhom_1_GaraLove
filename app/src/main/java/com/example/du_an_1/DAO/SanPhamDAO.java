package com.example.du_an_1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.lights.LightsManager;

import com.example.du_an_1.model.SanPham;
import com.example.du_an_1.sql.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {
    private SQLiteDatabase db;
    public SanPhamDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(SanPham sp){
        ContentValues values = new ContentValues();
        values.put("maSP",sp.maSP);
        values.put("tenSP",sp.tenSP);
        values.put("soLuong",sp.soLuong);
        values.put("tonKho",sp.tonKho);
        values.put("giaSP",sp.giaSP);
        values.put("maDV",sp.maDV);
        return db.insert("SanPham",null,values);
    }

    public long updateSP(SanPham sp){
        ContentValues values = new ContentValues();
        values.put("maSP",sp.maSP);
        values.put("tenSP",sp.tenSP);
        values.put("soLuong",sp.soLuong);
        values.put("tonKho",sp.tonKho);
        values.put("giaSP",sp.giaSP);
        values.put("maDV",sp.maDV);
        return db.update("SanPham",values, "maSP=?",new String[]{String.valueOf(sp.maSP)});
    }

    public int deleteSP(String id){
        return db.delete("SanPham","maSP=?",new String[]{id});
    }

    @SuppressLint("Range")
    private List<SanPham> getData(String sql, String...selectionArgs){
        List<SanPham> list = new ArrayList<SanPham>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while(c.moveToNext()){
            SanPham sanPham = new SanPham();
            sanPham.maSP = Integer.parseInt(c.getString(c.getColumnIndex("maSP")));
            sanPham.tenSP = c.getString(c.getColumnIndex("tenSP"));
            sanPham.soLuong = Integer.parseInt(c.getString(c.getColumnIndex("soLuong")));
            sanPham.tonKho = Integer.parseInt(c.getString(c.getColumnIndex("tonKho")));
            sanPham.giaSP = Integer.parseInt(c.getString(c.getColumnIndex("giaSP")));
            sanPham.maDV = Integer.parseInt(c.getString(c.getColumnIndex("maDV")));
            list.add(sanPham);
        }
        return list;
    }
    public List<SanPham> getAll(){
        String sql = "SELECT * FROM SanPham";
        return getData(sql);
    }

}
