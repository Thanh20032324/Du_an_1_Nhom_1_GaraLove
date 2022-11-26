package com.example.du_an_1.main.ui;

import android.app.Dialog;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an_1.Adapter.AdapterKhachHang;
import com.example.du_an_1.DAO.HoaDonDAO;
import com.example.du_an_1.DAO.KhachHangDAO;
import com.example.du_an_1.DAO.NhanVienDAO;
import com.example.du_an_1.R;
import com.example.du_an_1.databinding.FragmentQLKhachHangBinding;
import com.example.du_an_1.model.HoaDon;
import com.example.du_an_1.model.KhachHang;
import com.example.du_an_1.model.NhanVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class QLKhachHang extends Fragment {
   private FragmentQLKhachHangBinding binding;
   FloatingActionButton fab;
   KhachHangDAO dao;
   ListView listView;
   List<KhachHang> list;
   KhachHang khachHang;
   AdapterKhachHang adapterKhachHang;
   int a;
   int temp=0;
   EditText txtmakh,txttenkh,txtgioitinh,txtsdt,txtloaixe,txtdiachi;
//   TextInputLayout tilmakh,tiltenkh,tilgioitinh,tilsdt,tilloaixe,tildiachi;
   List<HoaDon> hoaDonList;
   HoaDonDAO hoaDonDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQLKhachHangBinding.inflate(inflater, container,false);
        View root = binding.getRoot();
        fab = root.findViewById(R.id.qlthanhvien_fab);
        listView = root.findViewById(R.id.qlthanhvien_listview);
        hoaDonList = new ArrayList<>();
        hoaDonDAO = new HoaDonDAO(getActivity());
        loadTable();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=-1;
                openDialog(Gravity.CENTER);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                a = i;
                openDialog(Gravity.CENTER);
            }
        });
        return root;
    }

    private void openDialog(int gravity) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_themkhachhang);
        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams
                .MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);
        if(Gravity.CENTER == gravity){
            dialog.setCancelable(true);
        }else {
            dialog.setCancelable(false);
        }
        TextView tvTile = (TextView) dialog.findViewById(R.id.item_tvtilekh);
        txtmakh = dialog.findViewById(R.id.item_txtmakh);
        txttenkh = dialog.findViewById(R.id.item_txtname);
        txtgioitinh = dialog.findViewById(R.id.item_txtgioitinh);
        txtsdt = dialog.findViewById(R.id.item_txtsdt);
        txtdiachi = dialog.findViewById(R.id.item_txtdiachi);
        txtloaixe = dialog.findViewById(R.id.item_txtloaixe);
//
//        tilmakh = dialog.findViewById(R.id.add_til_makh);
//        tiltenkh = dialog.findViewById(R.id.add_til_name);
//        tilgioitinh = dialog.findViewById(R.id.add_til_gioitinh);
//        tilsdt = dialog.findViewById(R.id.add_til_sdt);
//        tilloaixe = dialog.findViewById(R.id.add_til_loaixe);
//        tildiachi = dialog.findViewById(R.id.add_til_diachi);

        Button btnadd = dialog.findViewById(R.id.dialog_add_add);
        Button btncancel = dialog.findViewById(R.id.dialog_add_cancel);

        dao = new KhachHangDAO(getActivity());
        if(a==-1){
            txtmakh.setEnabled(false);
            if(list.size()==0){
                txtmakh.setText("1");
            }else {
                khachHang = dao.getAll().get(list.size() - 1);
                txtmakh.setText(String.valueOf(khachHang.maKH +1 ));
            }
            btnadd.setOnClickListener(new View.OnClickListener() {
               KhachHang khachHang = new KhachHang();
                @Override
                public void onClick(View v) {
                    validate();
                    if (temp==0){
                        khachHang.tenKH = txttenkh.getText().toString();
                        khachHang.gioiTinh = txtgioitinh.getText().toString();
                        khachHang.sdt = txtsdt.getText().toString();
                        khachHang.diachi = txtdiachi.getText().toString();
                        khachHang.loaixe = txtloaixe.getText().toString();
                        if (dao.insertKH(khachHang)>0){
                            Toast.makeText(getActivity(), "Thêm Thành Công",
                                    Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            loadTable();
                        }else{
                            Toast.makeText(getActivity(), "Thêm Thất Bại",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        temp=0;
                    }
                }
            });
            btncancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Hủy Thêm",
                            Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        }else {
            tvTile.setText("Sửa/Xóa Thành Viên");
            btnadd.setText("Sửa");
            btncancel.setText("Xóa");
            khachHang = dao.getAll().get(a);
            txtmakh.setText(String.valueOf(khachHang.maKH));
            txtmakh.setEnabled(false);
            txttenkh.setText(khachHang.tenKH);
            txtgioitinh.setText(khachHang.gioiTinh);
            txtsdt.setText(khachHang.sdt);
            txtdiachi.setText(khachHang.diachi);
            txtloaixe.setText(khachHang.loaixe);
            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    validate();
                    if(temp==0){
                        khachHang = new KhachHang();
                        khachHang.maKH = Integer.parseInt(txtmakh.getText().toString());
                        khachHang.tenKH = txttenkh.getText().toString();
                        khachHang.gioiTinh = txtgioitinh.getText().toString();
                        khachHang.sdt = txtsdt.getText().toString();
                        khachHang.diachi = txtdiachi.getText().toString();
                        khachHang.loaixe = txtloaixe.getText().toString();
                        if(dao.update(khachHang)<0){
                            Toast.makeText(getActivity(), "Sửa thất bại",
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), "Sửa thành công",
                                    Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            loadTable();
                        }
                    }else {
                        temp=0;
                    }
                }
            });
            btncancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hoaDonList = hoaDonDAO.getAll();
                    for(int i = 0; i < hoaDonList.size(); i++){
                        if (hoaDonList.get(i).MaKH == khachHang.maKH){
                            temp++;
                            Toast.makeText(getActivity(), "Không thể xóa khách hàng có trong hóa đơn",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    if (temp==0){
                        if(dao.delete(String.valueOf(khachHang.maKH))<0){
                            Toast.makeText(getActivity(), "Xóa thất bại",
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), "Xóa thành công",
                                    Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            loadTable();
                        }
                    }
                }
            });
        }
        dialog.show();
    }

    private void validate() {
        if (txttenkh.getText().length() == 0) {
            txttenkh.setError("Tên khách hàng không được bỏ trống");
            temp++;
        } else {
            txttenkh.setError("");
        }
        if (txtgioitinh.getText().length() == 0) {
            txtgioitinh.setError("Giới tính khách hàng không được bỏ trống");
            temp++;
        } else {
            txtgioitinh.setError("");
        }
        if (txtsdt.getText().length() == 0) {
            txtsdt.setError("Số điện thoại khách hàng không được bỏ trống");
            temp++;
        } else {
            txtsdt.setError("");
        }
        if (txtloaixe.getText().length() == 0) {
            txtloaixe.setError("Giới tính khách hàng không được bỏ trống");
            temp++;
        } else {
            txtloaixe.setError("");
        }
        if (txtdiachi.getText().length() == 0) {
            txtdiachi.setError("Giới tính khách hàng không được bỏ trống");
            temp++;
        } else {
            txtdiachi.setError("");
        }
    }

    private void loadTable() {
       dao = new KhachHangDAO(getActivity());
        list = dao.getAll();
        adapterKhachHang = new AdapterKhachHang(getActivity(),
                R.layout.item_lv_addkh,list);
        listView.setAdapter(adapterKhachHang);
    }
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }
}