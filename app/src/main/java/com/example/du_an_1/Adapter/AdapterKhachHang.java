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

import java.util.List;

public class AdapterKhachHang extends ArrayAdapter<KhachHang> {
    private Context context;
    private int resource;
    private List<KhachHang> object;
    private LayoutInflater inflater;
    public AdapterKhachHang(Context context, int resource, List object) {
        super(context, resource, object);
        this.context = context;
        this.resource = resource;
        this.object = object;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = new ViewHolder();
        if (convertView==null){
            convertView = inflater.inflate(R.layout.item_lv_addkh, null);
            holder.tvmakh = (TextView)convertView.findViewById(R.id.item_lv_makh);
            holder.tvtenkh = (TextView)convertView.findViewById(R.id.item_lv_tenkh);
            holder.tvgioitinh = (TextView)convertView.findViewById(R.id.item_lv_gioitinh);
            holder.tvsdt = (TextView)convertView.findViewById(R.id.item_lv_sdt);
            holder.tvdiachi = (TextView)convertView.findViewById(R.id.item_lv_diachi);
            holder.tvloaixe = (TextView)convertView.findViewById(R.id.item_lv_loaixe);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        KhachHang kh = object.get(position);
        holder.tvmakh.setText(String.valueOf(kh.maKH));
        holder.tvtenkh.setText(kh.tenKH);
        holder.tvgioitinh.setText(kh.gioiTinh);
        holder.tvsdt.setText(kh.sdt);
        holder.tvdiachi.setText(kh.diachi);
        holder.tvloaixe.setText(kh.loaixe);
        return convertView;
    }
    public class ViewHolder{
        TextView tvmakh,tvtenkh,tvgioitinh,tvsdt,tvloaixe,tvdiachi;
    }
}
