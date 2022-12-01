package com.example.du_an_1.Adapter;

import android.content.Context;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.du_an_1.R;
import com.example.du_an_1.model.SanPham;

import java.util.List;

public class SpinnerSanPham extends ArrayAdapter<SanPham> {
    Context context;
    List<SanPham> list;
    TextView tvTen;
    public SpinnerSanPham(@NonNull Context context, List<SanPham> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view= inflater.inflate(R.layout.san_pham_spinner,null);

        }

        final SanPham item = list.get(position);

        if(item!=null){
            tvTen = view.findViewById(R.id.tvSPNSP);

            tvTen.setText(item.getTenSP());
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view= inflater.inflate(R.layout.san_pham_spinner,null);

        }

        final SanPham item = list.get(position);

        if(item!=null){
            tvTen = view.findViewById(R.id.tvSPNSP);

            tvTen.setText(item.getTenSP());
        }
        return view;
    }
}
