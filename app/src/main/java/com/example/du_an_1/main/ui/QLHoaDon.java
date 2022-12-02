package com.example.du_an_1.main.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an_1.Adapter.AdapterHoaDon;
import com.example.du_an_1.Adapter.SpinnerAdapterKhachHang;
import com.example.du_an_1.Adapter.SpinnerSanPham;
import com.example.du_an_1.DAO.HoaDonDAO;
import com.example.du_an_1.DAO.KhachHangDAO;
import com.example.du_an_1.DAO.SanPhamDAO;
import com.example.du_an_1.R;
import com.example.du_an_1.databinding.FragmentQLHoaDonBinding;
import com.example.du_an_1.model.HoaDon;
import com.example.du_an_1.model.KhachHang;
import com.example.du_an_1.model.SanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class QLHoaDon extends Fragment {
    private FragmentQLHoaDonBinding binding;
    FloatingActionButton fab;
    ListView listView;
    HoaDonDAO dao;
    List<HoaDon> list;
    AdapterHoaDon adapterHoaDon;
    HoaDon hoaDon;

    int a;
    List<String> loi;
    String maKhachHang,maSP;

    List<KhachHang> khachHangList;
    KhachHangDAO khachHangDAO;
    SpinnerAdapterKhachHang adapterKhachHang;

    List<SanPham> sanPhamList;
    SanPhamDAO sanPhamDAO;
    SpinnerSanPham adapterSanPham;
    SanPham sanPham;

    Calendar c = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     binding = FragmentQLHoaDonBinding.inflate(inflater, container, false);
     View root = binding.getRoot();
     fab = root.findViewById(R.id.hoadon_fab);
     listView = root.findViewById(R.id.hoadon_listview);


     khachHangList = new ArrayList<>();
     khachHangDAO = new KhachHangDAO(getActivity());
     khachHangList = khachHangDAO.getAll();

     sanPham = new SanPham();
     sanPhamList = new ArrayList<>();
     sanPhamDAO = new SanPhamDAO(getActivity());
     sanPhamList = sanPhamDAO.getAll();

     loi = new ArrayList<>();

     loadTable();
     fab.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             a=-1;
             if(sanPhamList.size()==0){
                 loi.add("Sản Phẩm");
             }else {
                 list.remove("Sản Phẩm");
             }
             if (khachHangList.size()==0){
                 loi.add("Khách Hàng");
             }else {
                 list.remove("Khách Hàng");
             }
             if (loi.isEmpty()){
                 openDialog(Gravity.BOTTOM);
             }else {
                 Toast.makeText(getActivity(), "Bạn chưa thêm: " +loi,
                         Toast.LENGTH_SHORT).show();
                 loi = new ArrayList<>();
             }
         }
     });
     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
             a = i;
             openDialog(Gravity.BOTTOM);
         }
     });
     return root;
    }

    private void openDialog(int gravity) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_hoadon);

        Window window = dialog.getWindow();
        if (window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);
        if (Gravity.BOTTOM == gravity){
            dialog.setCancelable(true);
        }
        TextView tvTile = (TextView) dialog.findViewById(R.id.dialog_hoadon_tile);
        TextView ngaySua =(TextView) dialog.findViewById(R.id.dialog_hoadon_ngaysua);
        TextView giaSua = (TextView) dialog.findViewById(R.id.dialog_hoadon_tiensua);
        EditText maHD = (EditText) dialog.findViewById(R.id.dialog_hoadon_txtmahoadon);
        TextInputLayout tilmahoadon = (TextInputLayout) dialog.findViewById(R.id.dialog_hoadon_til_mahoadon);
        Spinner spnKhachHang = (Spinner) dialog.findViewById(R.id.dialog_spn_tenkhachhang);
        Spinner spnSanPham = (Spinner) dialog.findViewById(R.id.dialog_spn_sanpham);

        Button btnadd = dialog.findViewById(R.id.dialog_sp_add);
        Button btncall = dialog.findViewById(R.id.dialog_sp_cancel);

        CheckBox checkBox = dialog.findViewById(R.id.dialog_hoadon_checkBox);
        adapterKhachHang = new SpinnerAdapterKhachHang(getContext(),(ArrayList<KhachHang>)
                khachHangDAO.getAll());
        spnKhachHang.setAdapter(adapterKhachHang);
        spnKhachHang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                maKhachHang = String.valueOf(khachHangList.get(i).maKH);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adapterSanPham = new SpinnerSanPham(getContext(),(ArrayList<SanPham>)sanPhamDAO.getAll());
        spnSanPham.setAdapter(adapterSanPham);
        spnSanPham.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                maSP = String.valueOf(sanPhamList.get(i).maSP);
                sanPham = sanPhamDAO.getAll().get(i);
                giaSua.setText(String.valueOf(sanPham.giaSP));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dao = new HoaDonDAO(getActivity());
        tilmahoadon.setEnabled(false);
        String datetime = sdf.format(c.getTime());
        Intent intent = getActivity().getIntent();
        String user = intent.getStringExtra("user");
        if (a==-1){
            ngaySua.setText(datetime);
            checkBox.setEnabled(false);
            list = dao.getAll();
            if (list.size()==0){
                maHD.setText("1");
            }else {
                hoaDon = dao.getAll().get(list.size()-1);
                maHD.setText(String.valueOf(hoaDon.MaHD+1));
            }
            btnadd.setOnClickListener(new View.OnClickListener() {
                HoaDon hoaDon = new HoaDon();
                @Override
                public void onClick(View v) {
                    hoaDon.MaNV = user;
                    hoaDon.MaKH = Integer.parseInt(maKhachHang);
                    hoaDon.MaSP = Integer.parseInt(maSP);
                    hoaDon.ngay = java.sql.Date.valueOf(datetime);
                    hoaDon.tienSP = Integer.parseInt(giaSua.getText().toString());
                    hoaDon.thanhtoan = 0;

                    if (dao.insert(hoaDon)>0){
                        Toast.makeText(getActivity(), "Thêm Thành Công",
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        loadTable();
                    }else {
                        Toast.makeText(getActivity(), "Thêm Thất Bại",
                                 Toast.LENGTH_SHORT).show();
                    }
                }
            });
            btncall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Hủy Thêm",
                            Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        }
        else {
            hoaDon = dao.getAll().get(a);
            tvTile.setText("Sửa/Xóa Hóa Đơn");
            btnadd.setText("Sửa");
            btncall.setText("Xóa");

            maHD.setText(String.valueOf(hoaDon.MaHD));
            ngaySua.setText(String.valueOf(hoaDon.ngay));
            if(hoaDon.thanhtoan == 1){
                checkBox.setChecked(true);
            }else {
                checkBox.setChecked(false);
            }
            for(int i = 0; i<spnKhachHang.getCount();i++){
                if (list.get(a).MaKH == khachHangList.get(i).maKH){
                    spnSanPham.setSelection(i);
                }
            }
            for (int i = 0; i<spnSanPham.getCount();i++){
                if (list.get(a).MaSP == sanPhamList.get(i).maSP){
                    spnSanPham.setSelection(i);
                }
            }
            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hoaDon =new HoaDon();
                    hoaDon.MaNV = user;
                    hoaDon.MaHD = Integer.parseInt(maHD.getText().toString());
                    hoaDon.MaKH = Integer.parseInt(maKhachHang);
                    hoaDon.MaSP = Integer.parseInt(maSP);
                    hoaDon.ngay = java.sql.Date.valueOf(ngaySua.getText().toString());
                    hoaDon.tienSP = Integer.parseInt(giaSua.getText().toString());
                    if(checkBox.isChecked()){
                        hoaDon.thanhtoan = 1;
                    }else {
                        hoaDon.thanhtoan = 0;
                    }
                    if (dao.update(hoaDon)<0){
                        Toast.makeText(getActivity(), "Sửa thất bại",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(), "Sửa thành công",
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        loadTable();
                    }
                }
            });
            btncall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dao.delete(String.valueOf(hoaDon.MaHD))<0){
                        Toast.makeText(getActivity(), "Xóa thất bại",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(), "Xóa thành công",
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        loadTable();
                    }
                }
            });
        }
        dialog.show();
    }

    private void loadTable() {
        dao = new HoaDonDAO(getActivity());
        list = dao.getAll();
        adapterHoaDon = new AdapterHoaDon(getActivity(),R.layout.item_lv_nv,list);
        listView.setAdapter(adapterHoaDon);
    }
}