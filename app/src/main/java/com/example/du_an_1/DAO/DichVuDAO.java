package com.example.du_an_1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.displayhash.DisplayHashResultCallback;

import com.example.du_an_1.model.DichVu;
import com.example.du_an_1.model.NhanVien;
import com.example.du_an_1.sql.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class DichVuDAO {
    private SQLiteDatabase db;

    public DichVuDAO(Context context){
        DbHelper dbHelper =new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(DichVu dv){
        ContentValues values = new ContentValues();
        values.put("tenDV",dv.tenDV);
        return db.insert("DichVu",null,values);
    }

    public long update(DichVu dv){
        ContentValues values = new ContentValues();
        values.put("tenDV",dv.tenDV);
        return db.update("DichVu",values,"maDV=?", new String[]{String.valueOf(dv.maDV)});
    }
    public int delete(String id){
        return db.delete("DichVu","maDV=?",new String[]{id});
    }

//    @SuppressLint("Range")
//    private List<DichVu> getData(String sql, String...selectionArgs){
//        List<DichVu> list = new ArrayList<DichVu>();
//        Cursor c = db.rawQuery(sql,selectionArgs);
//        while (c.moveToNext()){
//            DichVu dv = new DichVu();
//            dv.maDV = Integer.parseInt(c.getString(c.getColumnIndex("maDV")));
//            dv.tenDV = c.getString(c.getColumnIndex("tenDV"));
//            list.add(dv);
//        }
//        return list;
//    }

    public List<DichVu> getAll(){
        String sql = "SELECT * FROM DichVu";
        return getData(sql);
    }
    public DichVu getID(String id){
        String sql = "SELECT * FROM DichVu WHERE maDV=?";
        List<DichVu> list = getData(sql,id);
        return list.get(0);
    }
private List<DichVu> getData(String sql, String...selectionArgs){
        List<DichVu> list = new ArrayList<DichVu>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            DichVu obj = new DichVu();
            obj.maDV = Integer.parseInt(c.getString(0));
            obj.tenDV = c.getString(1);
            list.add(obj);
        }
        return list;
}
public List<Integer> getAllMaDV(){
        List<Integer> list = new ArrayList<>();
        Cursor cursor = db.query("DichVu",null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            list.add(cursor.getInt(0));
            cursor.moveToNext();
        }cursor.close();
        return list;
}
}
