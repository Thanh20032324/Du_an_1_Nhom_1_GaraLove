package com.example.du_an_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.du_an_1.R;
import com.example.du_an_1.model.NhanVien;

import java.util.List;

public class AdapterNhanVien extends ArrayAdapter<NhanVien> {
    private Context context;
    private List<NhanVien> list;
    private int resource;
    private LayoutInflater inflater;
    TextView tvMaNV,tvTenNV;

    public AdapterNhanVien(@NonNull Context context, int resource, @NonNull List<NhanVien> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.list = objects;
        this.inflater  = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if(view==null){
            view = inflater.inflate(R.layout.item_lv_addnv,null);

        }
        final NhanVien nhanVien = new NhanVien();
        if(nhanVien!=null){
            tvMaNV = view.findViewById(R.id.tvMaNV);
            tvMaNV.setText(nhanVien.maNV);

            tvTenNV = view.findViewById(R.id.tvTenNV);
            tvTenNV.setText(nhanVien.hoTen);

        }
        return view;
    }
}


