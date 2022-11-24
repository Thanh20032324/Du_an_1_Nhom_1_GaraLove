package com.example.du_an_1.main.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.du_an_1.Adapter.SanPhamAdapter;
import com.example.du_an_1.Adapter.SpinnerDichVuAdapter;
import com.example.du_an_1.DAO.DichVuDAO;
import com.example.du_an_1.DAO.SanPhamDAO;
import com.example.du_an_1.R;
import com.example.du_an_1.model.DichVu;
import com.example.du_an_1.model.SanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class QLSanPham extends Fragment {


    ListView lv_sp;
    FloatingActionButton fabSP;
    List<SanPham> list;

    Dialog dialog;

    SpinnerDichVuAdapter dvAdapter;
    DichVu dichVu;
    DichVuDAO  DVdao;
    List<DichVu> listDV;

    SanPhamDAO dao;
    SanPhamAdapter adapter;
    SanPham sanPham;

    EditText edTenSP, edSoLuong, edTonKho, edGiaSP,edMaSP;
    Spinner spnDV;
    Button btnSave, btnCancle;
    int maDV,positonDV;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_q_l_san_pham, container, false);

        lv_sp = view.findViewById(R.id.lv_SanPham);
        dao = new SanPhamDAO(getContext());


        fabSP = view.findViewById(R.id.fab_sp);
        fabSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });

        lv_sp.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                sanPham = new SanPham();
                sanPham = list.get(position);
                openDialog(getActivity(),1);
                return false;
            }
        });
        updateLV();
        return view;
    }

    void updateLV(){
        list = (List<SanPham>) dao.getAll();
        adapter = new SanPhamAdapter(getActivity(),this,list);
        lv_sp.setAdapter(adapter);
    }

    public void xoaLS(final String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không");
        builder.setCancelable(true);

        builder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }
        );
        builder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao.deleteSP(id);
                        updateLV();
                        dialog.cancel();
                    }
                }
        );
        AlertDialog alertDialog = builder.create();
        builder.show();
    }

    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_san_pham);

        edMaSP = dialog.findViewById(R.id.edMaSP);
        edTenSP  =dialog.findViewById(R.id.edTenSP);
        edSoLuong = dialog.findViewById(R.id.edSoLuong);
        edTonKho = dialog.findViewById(R.id.edTonKho);
        spnDV =dialog.findViewById(R.id.spnDV);
        btnSave = dialog.findViewById(R.id.btnSaveSP);
        btnCancle = dialog.findViewById(R.id.btnCancleSP);

        edMaSP.setEnabled(false);


        DVdao = new DichVuDAO(context);
        listDV = new ArrayList<DichVu>();
        listDV = (ArrayList<DichVu>) DVdao.getAll();
        dvAdapter = new SpinnerDichVuAdapter(context,listDV);
        spnDV.setAdapter(dvAdapter);
        spnDV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maDV = listDV.get(position).maDV;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (type!=0){
            edMaSP.setText(String.valueOf(sanPham.getMaSP()));
            edTenSP.setText(sanPham.getTenSP());
            edSoLuong.setText(String.valueOf(sanPham.getSoLuong()));
            edTonKho.setText(String.valueOf(sanPham.getTonKho()));
            edGiaSP.setText(String.valueOf(sanPham.getGiaSP()));
            for (int i=0;i<listDV.size();i++){
                    if(sanPham.getMaDV()==(listDV.get(i).maDV)){
                        positonDV=i;
                    }
                }
            spnDV.setSelection(positonDV);

        }

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sanPham = new SanPham();
                sanPham.setTenSP(edTenSP.getText().toString());
                sanPham.setSoLuong(Integer.parseInt(edSoLuong.getText().toString()));
                sanPham.setTonKho(Integer.parseInt(edTonKho.getText().toString()));
                sanPham.setGiaSP(Integer.parseInt(edGiaSP.getText().toString()));
                sanPham.setMaDV(maDV);

                if(validate()>0){
                    if(type==0){
                        if(dao.insert(sanPham)>0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        sanPham.setMaSP(Integer.parseInt(edMaSP.getText().toString()));
                        if(dao.updateSP(sanPham)>0){
                            Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    updateLV();
                    dialog.dismiss();
                }
            }
        });
    }
    public int validate(){
        int check =1;
        if(edTenSP.getText().length()==0||edSoLuong.getText().length()==0||edTonKho.getText().length()==0||edGiaSP.getText().length()==0){
            Toast.makeText(getContext(), "Phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}