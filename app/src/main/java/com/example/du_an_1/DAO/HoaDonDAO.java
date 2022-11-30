package com.example.du_an_1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1.model.HoaDon;
import com.example.du_an_1.sql.DbHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    public SQLiteDatabase db;
    public HoaDonDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(HoaDon odj){
        ContentValues values = new ContentValues();
        values.put("maNV",odj.MaNV);
        values.put("maKH",odj.MaKH);
        values.put("maSP",odj.MaSP);
        values.put("tienSP",odj.tienSP);
        values.put("ngay",String.valueOf(odj.ngay));
        values.put("soLuongSP",odj.soLuongSP);
        values.put("thanhToan",odj.thanhtoan);
        return db.insert("HoaDon",null,values);
    }

    public  long update(HoaDon odj){
        ContentValues values = new ContentValues();
        values.put("maNV",odj.MaNV);
        values.put("maKH",odj.MaKH);
        values.put("maSP",odj.MaSP);
        values.put("tienSP",odj.tienSP);
        values.put("ngay",String.valueOf(odj.ngay));
        values.put("soLuongSP",odj.soLuongSP);
        values.put("thanhToan",odj.thanhtoan);
        return db.update("HoaDon",values,"maHD=?",
                new String[]{String.valueOf(odj.MaHD)});
    }
    public int delete(String id){
        return db.delete("HoaDon","maPM=?",new String[]{id});
    }
    public List<HoaDon> getAll(){
        String sql = "SELECT * FROM HoaDon";
        return getData(sql);
    }
    public HoaDon getID(String id){
        String sql = "SELECT * FROM HoaDon WHERE maHD=?";
        List<HoaDon> list = getData(sql,id);
        return list.get(0);
}

    @SuppressLint("Range")
    private List<HoaDon> getData(String sql, String...selectionArgs) {
        List<HoaDon> list = new ArrayList<HoaDon>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            HoaDon obj = new HoaDon();
            obj.MaHD = Integer.parseInt(c.getString(c.getColumnIndex("maHD")));
            obj.MaNV = c.getString(c.getColumnIndex("maNv"));
            obj.MaKH = Integer.parseInt(c.getString(c.getColumnIndex("maKH")));
            obj.MaSP = Integer.parseInt(c.getString(c.getColumnIndex("maSP")));
            obj.tienSP = Integer.parseInt(c.getString(c.getColumnIndex("tienSP")));
            obj.ngay = Date.valueOf(c.getString(c.getColumnIndex("ngay")));
            obj.soLuongSP = Integer.parseInt(c.getString(c.getColumnIndex("soLuongSP")));
            obj.thanhtoan = Integer.parseInt(c.getString(c.getColumnIndex("thanhToan")));
            list.add(obj);
        }
        return list;
    }

}
