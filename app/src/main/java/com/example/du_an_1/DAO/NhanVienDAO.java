package com.example.du_an_1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1.model.NhanVien;
import com.example.du_an_1.sql.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    private SQLiteDatabase db;
    public NhanVienDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(NhanVien odj){
        ContentValues values = new ContentValues();
        values.put("maNV",odj.maNV);
        values.put("hoTen",odj.hoTen);
        values.put("matKhau",odj.matKhau);
        return db.insert("NhanVien", null, values);
    }
    public long insertadmin(){
        ContentValues values = new ContentValues();
        values.put("maNV","admin");
        values.put("hoTen","ADMIN");
        values.put("matKhau","admin");
        return db.insert("NhanVien",null,values);
    }
    public long update(NhanVien odj){
        ContentValues values = new ContentValues();
        values.put("maNV",odj.maNV);
        values.put("hoTen",odj.hoTen);
        values.put("matKhau",odj.matKhau);
        return db.update("NhanVien",values,"maNV=?", new String[]{String.valueOf(odj.maNV)});
    }
    public int delete(String id){
        return db.delete("NhanVien","maNV=?",new String[]{id});
    }
    public List<NhanVien> getAll(){
        String sql = "SELECT * FROM NhanVien";
        return getData(sql);
    }

    @SuppressLint("Range")
    private List<NhanVien> getData(String sql, String...selecttionArgs) {
        List<NhanVien> list = new ArrayList<NhanVien>();
        Cursor c = db.rawQuery(sql,selecttionArgs);
        while (c.moveToNext()){
            NhanVien obj = new NhanVien();
            obj.maNV = c.getString(c.getColumnIndex("maNV"));
            obj.hoTen = c.getString(c.getColumnIndex("hoTen"));
            obj.matKhau = c.getString(c.getColumnIndex("matKhau"));
            list.add(obj);
        }
        return list;
    }
    public int checkLogin(String id, String password){
        String sql = "SELECT * FROM NhanVien WHERE maNV=? AND matKhau=?";
        List<NhanVien> list = getData(sql,id,password);
        if(list.size()==0){
            return  -1;
        }
        return 1;
    }
}
