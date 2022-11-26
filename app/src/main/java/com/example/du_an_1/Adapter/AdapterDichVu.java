package com.example.du_an_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.R;
import com.example.du_an_1.model.DichVu;
import com.example.du_an_1.model.NhanVien;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdapterDichVu extends ArrayAdapter<DichVu> {
    private Context context;
    private int resource;
    private List<DichVu> objects;
    private LayoutInflater inflater;
    private ArrayList<DichVu> arrayList;
    public AdapterDichVu( Context context, int resource, List<DichVu> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        this.arrayList = new ArrayList<DichVu>();
        this.arrayList.addAll(objects);
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = new ViewHolder();
        if(convertView==null){
            convertView = inflater.inflate(R.layout.item_lv_addnv,null);
            holder.tvmanv = (TextView)convertView.findViewById(R.id.item_lv_username);
            holder.tvtennv = (TextView)convertView.findViewById(R.id.item_lv_name);


            holder.temp1 = (TextView)convertView.findViewById(R.id.temp_1);
            holder.temp2 = (TextView)convertView.findViewById(R.id.temp_2);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        DichVu dv = objects.get(position);
        holder.tvmanv.setText(String.valueOf(dv.maDV));
        holder.tvtennv.setText(dv.tenDV);

        holder.temp1.setText("Mã Dịch Vụ: ");
        holder.temp2.setText("Tên Dịch Vụ: ");
        return convertView;
    }
//    public void filter(String charText){
//        charText= charText.toLowerCase(Locale.getDefault());
//        objects.clear();
//        if(charText.length()==0){
//            objects.addAll(arrayList);
//        }else {
//            for (DichVu dv : arrayList){
//                if(dv.)
//            }
//        }
//    }
    public class ViewHolder{
          TextView tvmanv,tvtennv,temp1,temp2;
    }
}
