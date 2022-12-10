package com.example.du_an_1.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.du_an_1.DAO.DichVuDAO;

import com.example.du_an_1.R;
import com.example.du_an_1.main.ui.QLSanPham;
import com.example.du_an_1.model.DichVu;
import com.example.du_an_1.model.SanPham;

import java.util.List;
import java.util.zip.Inflater;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {

    Context context;
    QLSanPham fraSanPham;
    List<SanPham> list;

    TextView tvTenSP,tvSoLuong,tvTonKho,tvGiaTien,tvDichVu;

    public SanPhamAdapter(@NonNull Context context, QLSanPham fraSanPham, @NonNull List<SanPham> list) {
        super(context, 0, list);
        this.context=context;
        this.fraSanPham = fraSanPham;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if(view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_san_pham,null);
        }

        final SanPham item = list.get(position);

        if(item!=null){
            tvTenSP = view.findViewById(R.id.tvTenSP);
            tvTenSP.setText("Tên sản phẩm: "+item.getTenSP());

            tvSoLuong = view.findViewById(R.id.tvSoLuong);
            tvSoLuong.setText("Số lượng: "+item.getSoLuong());

            tvTonKho = view.findViewById(R.id.tvTonKho);
            tvTonKho.setText("Tồn kho: "+item.getTonKho());

            tvGiaTien = view.findViewById(R.id.tvGiaTien);

            tvGiaTien.setText("Giá tiền: "+item.getGiaSP()+" .VND");


            DichVu dichVu= new DichVu();
            DichVuDAO DVdao = new DichVuDAO(getContext());
            dichVu = DVdao.getID(String.valueOf(item.getMaDV()));

            tvDichVu= view.findViewById(R.id.tvDichVu);

            tvDichVu.setText("Tên dịch vụ: "+dichVu.tenDV);


        }


        return view;
    }
}
