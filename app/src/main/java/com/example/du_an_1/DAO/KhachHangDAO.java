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
        values.put("hoTen",kh.tenKH);
        values.put("gioiTinh",kh.gioiTinh);
        values.put("soDienThoai",kh.sdt);
        values.put("diaChi",kh.diachi);
        values.put("loaiXe",kh.loaixe);
        Log.d("XXXXX",kh.toString());
        return db.insert("KhachHang",null,values);
    }

    public long update(KhachHang kh){
        ContentValues values = new ContentValues();
        values.put("hoTen",kh.tenKH);
        values.put("gioiTinh",kh.gioiTinh);
        values.put("soDienThoai",kh.sdt);
        values.put("diaChi",kh.diachi);
        values.put("loaiXe",kh.loaixe);
        return db.update("KhachHang",values,"maKH=?", new String[]{String.valueOf(kh.maKH)});
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
           khachHang.maKH = Integer.parseInt(c.getString(0));
           khachHang.tenKH = c.getString(1);
           khachHang.gioiTinh = c.getString(2);
           khachHang.sdt = c.getString(3);

           khachHang.diachi = c.getString(4);
            khachHang.loaixe = c.getString(5);
            list.add(khachHang);
        }
        return list;
    }

    public List<KhachHang> getAll(){
        String sql = "SELECT * FROM KhachHang";
        return getData(sql);
    }
    public KhachHang getID(String id){
        String sql = "SELECT * FROM KhachHang WHERE maKH=?";
        List<KhachHang> list = getData(sql, id);
        return list.get(0);
    }
}
