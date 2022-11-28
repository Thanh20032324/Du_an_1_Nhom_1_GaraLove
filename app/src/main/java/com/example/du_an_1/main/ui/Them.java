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
import com.example.du_an_1.Adapter.AdapterNhanVien;
import com.example.du_an_1.DAO.KhachHangDAO;
import com.example.du_an_1.DAO.NhanVienDAO;
import com.example.du_an_1.R;
import com.example.du_an_1.databinding.FragmentQLKhachHangBinding;
import com.example.du_an_1.databinding.FragmentThemBinding;
import com.example.du_an_1.model.KhachHang;
import com.example.du_an_1.model.NhanVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Them extends Fragment {
    private FragmentThemBinding binding;
    FloatingActionButton fab;
    NhanVienDAO dao;
    ListView listView;
    List<NhanVien> list;
    NhanVien nhanVien;

    int a;
    int temp=0;
    EditText edMaNV,edTenNV,edMatKhau;
    AdapterNhanVien adapterNhanVien;
    TextView tvTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         binding = FragmentThemBinding.inflate(inflater,container,false);
         View view = binding.getRoot();

         fab = view.findViewById(R.id.add_fab);
         listView = view.findViewById(R.id.add_listview);

         dao = new NhanVienDAO(getContext());
         updateLV();
         fab.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                a=-1;
                openDialog(Gravity.CENTER);
             }
         });

         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                a=position;
                openDialog(Gravity.CENTER);
             }
         });
        return view;
    }
    private void openDialog(int gravity){
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_themnv);
        Window window = dialog.getWindow();
        if(window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams wLayoutParams = window.getAttributes();
        wLayoutParams.gravity =gravity;
        window.setAttributes(wLayoutParams);
        if(Gravity.CENTER==gravity){
            dialog.setCancelable(true);
        }else{
            dialog.setCancelable(false);
        }
        edTenNV = dialog.findViewById(R.id.item_txtname);
        edMaNV = dialog.findViewById(R.id.item_txtnameuser);
        edMatKhau = dialog.findViewById(R.id.item_txtpass);
        tvTitle = dialog.findViewById(R.id.item_tvtile);

        Button btnadd = dialog.findViewById(R.id.dialog_add_add);
        Button btncancel = dialog.findViewById(R.id.dialog_add_cancel);

        dao = new NhanVienDAO(getContext());
        if(a==-1){
            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    validate();
                    if(temp==0){
                        nhanVien = new NhanVien();
                        nhanVien.hoTen = edTenNV.getText().toString();
                        nhanVien.maNV = edMaNV.getText().toString();
                        nhanVien.matKhau = edMatKhau.getText().toString();

                        if(dao.insert(nhanVien)>0){
                            Toast.makeText(getActivity(), "Thêm Thành Công",
                                    Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            updateLV();
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
                    Toast.makeText(getActivity(), "Hủy Thêm", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        }else{
            tvTitle.setText("Sửa/Xóa nhân viên");
            btnadd.setText("Sửa");
            btncancel.setText("Xóa");

            nhanVien = dao.getAll().get(a);

            edTenNV.setText(nhanVien.hoTen);
            edMaNV.setText(nhanVien.maNV);
            edMatKhau.setText(nhanVien.matKhau);

            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    validate();
                    if(temp==0){
                        nhanVien = new NhanVien();
                        nhanVien.hoTen = edTenNV.getText().toString();
                        nhanVien.maNV =edMaNV.getText().toString();
                        nhanVien.matKhau = edMatKhau.getText().toString();

                        if(dao.update(nhanVien)>0){
                            Toast.makeText(getActivity(), "Sửa thất bại",
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), "Sửa thành công",
                                    Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            updateLV();
                        }
                    }else{
                        temp=0;
                    }
                }
            });
            btncancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (temp==0){
                        if(dao.delete(String.valueOf(nhanVien.id))<0){
                            Toast.makeText(getActivity(), "Xóa thất bại",
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), "Xóa thành công",
                                    Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            updateLV();
                        }
                    }
                }
            });
        }
        dialog.show();
    }

    private void updateLV(){
        dao = new NhanVienDAO(getContext());
        list = dao.getAll();
        adapterNhanVien = new AdapterNhanVien(getActivity(),R.layout.item_lv_addnv,list);
        listView.setAdapter(adapterNhanVien);
    }

    private void validate(){
        if(edTenNV.getText().length()==0){
            edTenNV.setError("Tên nhân viên không được để trống");
            temp++;
        }else{
            edTenNV.setError("");
        }

        if(edMaNV.getText().length()==0){
            edMaNV.setError("Mã nhân viên không được để trống");
            temp++;
        }else{
            edMaNV.setError("");
        }

        if(edMatKhau.getText().length()==0){
            edMatKhau.setError("Mật khẩu không được để trống");
            temp++;
        }else{
            edMatKhau.setError("");
        }

    }
}