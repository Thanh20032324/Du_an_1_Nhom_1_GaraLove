package com.example.du_an_1.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.du_an_1.DAO.DichVuDAO;
import com.example.du_an_1.DAO.KhachHangDAO;
import com.example.du_an_1.DAO.SanPhamDAO;
import com.example.du_an_1.R;
import com.example.du_an_1.model.HoaDon;
import com.example.du_an_1.model.KhachHang;
import com.example.du_an_1.model.SanPham;

import java.text.SimpleDateFormat;
import java.util.List;

public class AdapterHoaDon extends ArrayAdapter<HoaDon> {
    private Context context;
    private int resource;
    private List<HoaDon> objects;
    private LayoutInflater inflater;
    KhachHangDAO khachHangDAO;
    SanPhamDAO sanPhamDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public AdapterHoaDon(Context context, int resource, List objects){
        super(context, resource , objects);
        this.context = context;
        this.resource =resource;
        this.objects= objects;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
@Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = new ViewHolder();
        if(convertView==null){
            convertView = inflater.inflate(R.layout.item_lv_hoadon, null);
            holder.tvmahd = (TextView)convertView.findViewById(R.id.item_hoadon_mahd);
            holder.tvtenkh = (TextView) convertView.findViewById(R.id.item_hoadon_tenkh);
            holder.tvtensp = (TextView) convertView.findViewById(R.id.item_hoadon_tensanpham);
            holder.tvtiensp = (TextView) convertView.findViewById(R.id.item_hoadon_tien);
            holder.tvngay = (TextView) convertView.findViewById(R.id.item_hoadon_ngaymuon);
            holder.tvthanhtoan = (TextView) convertView.findViewById(R.id.item_hoadon_trangthai);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        HoaDon obj = objects.get(position);
        holder.tvmahd.setText("Mã Hóa Đơn: "+obj.MaHD);

        khachHangDAO = new KhachHangDAO(context);
    KhachHang khachHang = khachHangDAO.getID(String.valueOf(obj.MaKH));
        holder.tvtenkh.setText(khachHang.tenKH);

        sanPhamDAO = new SanPhamDAO(context);
    SanPham sanPham = sanPhamDAO.getID(String.valueOf(obj.MaSP));
    holder.tvtensp.setText("Sản Phẩm: "+sanPham.tenSP);

    holder.tvtiensp.setText("Giá: "+obj.tienSP);
    holder.tvngay.setText(sdf.format(obj.ngay));
    if (obj.thanhtoan==1){
        holder.tvthanhtoan.setTextColor(Color.BLUE);
        holder.tvthanhtoan.setText("Đã Thanh Toán");
    }else{
        holder.tvthanhtoan.setTextColor(Color.RED);
        holder.tvthanhtoan.setText("Chưa Thanh Toán");
    }
    return convertView;
}
public class ViewHolder{
        TextView tvmahd,tvtenkh,tvtensp,tvtiensp,tvngay,tvthanhtoan;
}
}
