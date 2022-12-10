package com.example.du_an_1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.du_an_1.ListSanPhamActivity;
import com.example.du_an_1.R;
import com.example.du_an_1.main.ui.QLHoaDon;
import com.example.du_an_1.main.ui.QLSanPham;
import com.example.du_an_1.model.HoaDon;
import com.example.du_an_1.model.SanPham;

import java.util.List;

public class ListSanPhamAdapter extends ArrayAdapter<SanPham> {

    Context context;
    ListSanPhamActivity activity;
    List<SanPham> list;

    HoaDon hoaDon;
    TextView tvTenSP;
    Button btnThemSP;
    EditText edSoLuongSP;
    public ListSanPhamAdapter(@NonNull Context context, ListSanPhamActivity activity, @NonNull List<SanPham> list) {
        super(context, 0, list);
        this.context=context;
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if(view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_list_san_pham,null);
        }

        final SanPham item = list.get(position);

        if(item!=null){
            tvTenSP = view.findViewById(R.id.tvTenSP);
            tvTenSP.setText(item.getTenSP());

            edSoLuongSP = view.findViewById(R.id.edSoLuongSP);
            btnThemSP = view.findViewById(R.id.btnAddSP);

        }
        hoaDon  = new HoaDon();
        btnThemSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("tenSP",item.getTenSP());
                intent.putExtra("giaTien",item.getGiaSP());
                hoaDon.MaSP = item.getMaSP();

                Toast.makeText(context, item.getTenSP()+"  "+item.getGiaSP(), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, edSoLuongSP.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}
