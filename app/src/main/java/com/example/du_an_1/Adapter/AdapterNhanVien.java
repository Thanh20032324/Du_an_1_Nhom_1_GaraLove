package com.example.du_an_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.du_an_1.R;
import com.example.du_an_1.model.NhanVien;

import java.util.List;

public class AdapterNhanVien extends ArrayAdapter<NhanVien> {
    private Context context;
    private int resource;
    private List<NhanVien> objects;
    private LayoutInflater inflater;
    String a="*";
    public AdapterNhanVien(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = new ViewHolder();
        if (convertView==null){
            convertView = inflater.inflate(R.layout.item_lv_nv,null);
            holder.tvtennv = (TextView)convertView.findViewById(R.id.item_lv_username);
            holder.tvtenDN = (TextView)convertView.findViewById(R.id.item_lv_name);
            holder.tvmk = (TextView)convertView.findViewById(R.id.item_lv_pass);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        NhanVien nv = objects.get(position);
        holder.tvtennv.setText(nv.maNV);
        holder.tvtenDN.setText(nv.hoTen);
        String temp = nv.matKhau;
        for (int i = 0; i< temp.length(); i++){
            a=a.concat("*");
        }
        holder.tvmk.setText(a);
        a="";
        return convertView;
    }
    public class ViewHolder{
        TextView  tvtennv, tvtenDN, tvmk;
    }
}
