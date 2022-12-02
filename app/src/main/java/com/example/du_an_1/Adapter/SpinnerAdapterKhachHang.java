package com.example.du_an_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.du_an_1.R;
import com.example.du_an_1.model.KhachHang;
import com.example.du_an_1.model.SanPham;

import java.util.ArrayList;

public class SpinnerAdapterKhachHang extends ArrayAdapter<KhachHang> {
    private Context context;
    private ArrayList<KhachHang> objects;
    TextView tvmakh,tvtenkh;


    public SpinnerAdapterKhachHang(@NonNull Context context, ArrayList<KhachHang> objects) {
        super(context, 0,objects);
        this.context = context;
        this.objects = objects;
    }
    @Override
    public View getView(int position, View converView, ViewGroup parent){
        View holder = converView;
        if(holder==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder = inflater.inflate(R.layout.spinner_adpter_khachhang,null);

        }
        final KhachHang obj = objects.get(position);
        if (obj != null){
            tvmakh = holder.findViewById(R.id.item_spn_khachhang_ma);
            tvmakh.setText(String.valueOf(obj.maKH));
            tvtenkh = holder.findViewById(R.id.item_spn_khachhang_nsx);
            tvtenkh.setText(obj.tenKH);
        }
        return holder;
    }
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent){
        View holder = convertView;
        if (holder==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder = inflater.inflate(R.layout.spinner_adpter_khachhang,null);
        }
        final KhachHang obj = objects.get(position);
        if(obj !=null){
            tvmakh = holder.findViewById(R.id.item_spn_khachhang_ma);
            tvmakh.setText(String.valueOf(obj.maKH));
            tvtenkh = holder.findViewById(R.id.item_spn_khachhang_nsx);
            tvtenkh.setText(String.valueOf(obj.tenKH));
        }
        return holder;
    }
}
