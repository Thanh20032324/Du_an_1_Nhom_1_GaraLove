package com.example.du_an_1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.du_an_1.model.KhachHang;
import com.example.du_an_1.sql.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {
    private SQLiteDatabase db;

    public KhachHangDAO(Context context){
        DbHelper dbHelper =new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertKH(KhachHang kh){
        ContentValues values = new ContentValues();
        values.put("hoTen",kh.getTenKH());
        values.put("gioiTinh",kh.getGioiTinh());
        values.put("soDienThoai",kh.getSdt());
        values.put("diaChi",kh.getDiachi());
        values.put("loaiXe",kh.getLoaixe());
        Log.d("XXXXX",kh.toString());
        return db.insert("KhachHang",null,values);
    }

    public long update(KhachHang kh){
        ContentValues values = new ContentValues();
        values.put("hoTen",kh.getTenKH());
        values.put("gioiTinh",kh.getGioiTinh());
        values.put("soDienThoai",kh.getSdt());
        values.put("diaChi",kh.getDiachi());
        values.put("loaiXe",kh.getLoaixe());
        return db.update("KhachHang",values,"maKH=?", new String[]{String.valueOf(kh.getMaKH())});
    }
    public int delete(String id){
        return db.delete("KhachHang","maKH=?",new String[]{id});
    }

    @SuppressLint("Range")
    private List<KhachHang> getData(String sql, String...selectionArgs){
        List<KhachHang> list = new ArrayList<KhachHang>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            KhachHang khachHang = new KhachHang();
            khachHang.setMaKH(Integer.parseInt(c.getString(c.getColumnIndex("maKH"))));
            khachHang.setTenKH(c.getString(c.getColumnIndex("hoTen")));
            khachHang.setGioiTinh(c.getString(c.getColumnIndex("gioiTinh")));
            khachHang.setSdt(c.getString(c.getColumnIndex("soDienThoai")));
            khachHang.setDiachi(c.getString(c.getColumnIndex("diaChi")));
            khachHang.setLoaixe(c.getString(c.getColumnIndex("loaiXe")));
            list.add(khachHang);
        }
        return list;
    }

    public List<KhachHang> getAll(){
        String sql = "SELECT * FROM KhachHang";
        return getData(sql);
    }
}
