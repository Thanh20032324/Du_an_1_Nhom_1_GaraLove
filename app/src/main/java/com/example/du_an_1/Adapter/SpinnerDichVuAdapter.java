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
import com.example.du_an_1.model.DichVu;

import java.util.ArrayList;
import java.util.List;

public class SpinnerDichVuAdapter extends ArrayAdapter<DichVu> {
    private Context context;
    private List<DichVu> list;
    TextView tvTenDV;

    public SpinnerDichVuAdapter(@NonNull Context context,List<DichVu> list) {
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
            view = inflater.inflate(R.layout.dich_vu_item_spinner,null);

        }
        final DichVu dichVu = list.get(position);
        if(dichVu!=null){
            tvTenDV = view.findViewById(R.id.tvTenDVSP);
            tvTenDV.setText(dichVu.tenDV);
        }

        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dich_vu_item_spinner,null);

        }
        final DichVu dichVu = list.get(position);
        if(dichVu!=null){
            tvTenDV = view.findViewById(R.id.tvTenDVSP);
            tvTenDV.setText(dichVu.tenDV);
        }

        return view;
    }
}
