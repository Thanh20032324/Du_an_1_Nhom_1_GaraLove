package com.example.du_an_1.main.ui;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
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
import com.example.du_an_1.DAO.HoaDonDAO;
import com.example.du_an_1.DAO.KhachHangDAO;
import com.example.du_an_1.DAO.NhanVienDAO;
import com.example.du_an_1.R;
import com.example.du_an_1.databinding.FragmentQLKhachHangBinding;
import com.example.du_an_1.databinding.FragmentThemBinding;
import com.example.du_an_1.model.HoaDon;
import com.example.du_an_1.model.KhachHang;
import com.example.du_an_1.model.NhanVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
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
    EditText edTenNV,edtendn,edMatKhau;
    TextInputLayout  tilTenNV, tilTenDN, tilMatKhau;
    AdapterNhanVien adapterNhanVien;
    List<HoaDon> hoaDonList;
    HoaDonDAO hoaDonDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         binding = FragmentThemBinding.inflate(inflater,container,false);
         View root = binding.getRoot();

         fab = root.findViewById(R.id.add_fab);
         listView = root.findViewById(R.id.add_listview);

         hoaDonList = new ArrayList<>();
         hoaDonDAO = new HoaDonDAO(getActivity());

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
                if (a!=0) {
                    openDialog(Gravity.CENTER);
                }else {
                    Toast.makeText(getActivity(), "Không thể sửa admin",
                            Toast.LENGTH_SHORT).show();
                }
             }
         });
        return root;
    }

    private void openDialog(int gravity){
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_nv);
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
        edtendn = dialog.findViewById(R.id.item_txttendn);
        edTenNV = dialog.findViewById(R.id.item_txttennv);
        edMatKhau = dialog.findViewById(R.id.item_txtpasss);

        tilTenDN = dialog.findViewById(R.id.add_til_tendn);
        tilTenNV = dialog.findViewById(R.id.add_til_tennv);
        tilMatKhau = dialog.findViewById(R.id.add_til_passs);

        Button btnadd = dialog.findViewById(R.id.dialog_add_add);
        Button btncancel = dialog.findViewById(R.id.dialog_add_cancel);

        dao = new NhanVienDAO(getActivity());
        if(a==-1){
            btnadd.setOnClickListener(new View.OnClickListener() {
                NhanVien vien = new NhanVien();
                @Override
                public void onClick(View v) {
                    validate();
                    for (int i = 0; i < list.size(); i++){
                        if (list.get(i).maNV.equals(edtendn.getText().toString())){
                            tilTenDN.setError("Tên Đăng Nhập Đã Tồn Tại");
                            temp++;
                            break;
                        }
                    }
                    if(temp==0){
                       vien.maNV = edtendn.getText().toString();
                       vien.hoTen = edTenNV.getText().toString();
                       vien.matKhau = edMatKhau.getText().toString();
                        if(dao.insert(vien)>0){
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
            nhanVien = dao.getAll().get(a);
            TextView tvTile = (TextView)dialog.findViewById(R.id.item_tvtile1);
            tvTile.setText("Sửa/Xóa nhân viên");
            btnadd.setText("Sửa");
            btncancel.setText("Xóa");

            edMatKhau.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            tilMatKhau.setPasswordVisibilityToggleEnabled(true);

            edtendn.setText(nhanVien.maNV);
            edtendn.setEnabled(false);
            edTenNV.setText(nhanVien.hoTen);
            edMatKhau.setText(nhanVien.matKhau);

            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    validate();
                    if(temp==0){
                        nhanVien = new NhanVien();
                        nhanVien.maNV = edtendn.getText().toString();
                        nhanVien.hoTen = edTenNV.getText().toString();
                        nhanVien.matKhau = edMatKhau.getText().toString();

                        if(dao.update(nhanVien)<0){
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
                    hoaDonList = hoaDonDAO.getAll();
                    for (int i = 0; i < hoaDonList.size(); i++){
                        if (hoaDonList.get(i).MaNV.equals(nhanVien.maNV)){
                            temp++;
                            Toast.makeText(getActivity(), "Không thể xóa nhân viên có trong hóa đơn",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    if (temp==0){
                        if(dao.delete(String.valueOf(nhanVien.maNV))<0){
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
        dao = new NhanVienDAO(getActivity());
        list = dao.getAll();
        adapterNhanVien = new AdapterNhanVien(getActivity(),R.layout.item_lv_addnv,list);
        listView.setAdapter(adapterNhanVien);
    }

    private void validate(){
        if(edTenNV.getText().length()==0){
            tilTenNV.setError("Tên nhân viên không được để trống");
            temp++;
        }else{
            tilTenNV.setError("");
        }

        if(edtendn.getText().length()==0){
            tilTenDN.setError("Tên đăng nhập không được để trống");
            temp++;
        }else{
            tilTenDN.setError("");
        }

        if(edMatKhau.getText().length()==0){
            tilMatKhau.setError("Mật khẩu không được để trống");
            temp++;
        }else{
            tilMatKhau.setError("");
        }
    }
}