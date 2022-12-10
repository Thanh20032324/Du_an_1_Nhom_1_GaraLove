package com.example.du_an_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.du_an_1.Adapter.ListSanPhamAdapter;
import com.example.du_an_1.DAO.SanPhamDAO;
import com.example.du_an_1.model.SanPham;

import java.util.List;

public class ListSanPhamActivity extends AppCompatActivity {

    ListView lvSP;
    SanPhamDAO dao;
    List<SanPham> list;
    ListSanPhamAdapter adapter;
    SanPham sanPham;

    Button btnThemSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_san_pham);

        lvSP = findViewById(R.id.lvSP);

        btnThemSP = findViewById(R.id.btnSaveSP);
        sanPham = new SanPham();
        dao = new SanPhamDAO(getApplicationContext());

        list = (List<SanPham>) dao.getAll();
        Log.d("ZZZZZZzz", sanPham.toString());
        adapter = new ListSanPhamAdapter(getApplicationContext(),this,list);
        lvSP.setAdapter(adapter);


    }
}