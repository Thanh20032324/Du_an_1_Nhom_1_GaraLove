package com.example.du_an_1.main.ui;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.Toolbar;

import com.example.du_an_1.Adapter.AdapterDichVu;
import com.example.du_an_1.DAO.DichVuDAO;
import com.example.du_an_1.DAO.SanPhamDAO;
import com.example.du_an_1.R;
import com.example.du_an_1.databinding.FragmentQLDichVuBinding;
import com.example.du_an_1.model.DichVu;
import com.example.du_an_1.model.SanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;


public class QLDichVu extends Fragment {
    private FragmentQLDichVuBinding binding;
    FloatingActionButton fab;
    DichVuDAO dao;
    ListView listView;
    List<DichVu> list;
    DichVu dichVu;
    AdapterDichVu adapterDichVu;
    int a;
    int temp=0;
    EditText txtnameuser, txtname;
    TextInputLayout tilusername, tilname;
    List<SanPham> sanPhamList;
    SanPhamDAO sanPhamDAO;
    Toolbar toolbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQLDichVuBinding.inflate(inflater,
                container,false);
        View root = binding.getRoot();
        toolbar = root.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        activity.getSupportActionBar();
        fab = root.findViewById(R.id.qlloaisach_fab);
        listView = root.findViewById(R.id.qlloaisach_listview);
        sanPhamList = new ArrayList<>();
        sanPhamDAO = new SanPhamDAO(getActivity());
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
        loadTable();
        return root;
    }
    public void loadTable(){
        dao = new DichVuDAO(getActivity());
        list = dao.getAll();
        adapterDichVu = new AdapterDichVu(getActivity(),
                R.layout.item_lv_addnv,list);
        listView.setAdapter(adapterDichVu);
    }
    private void openDialog(int gravity){
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_themnv);
        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);
        if(Gravity.CENTER == gravity){
            dialog.setCancelable(true);
        }else {
            dialog.setCancelable(false);
        }
        TextView tvTile = (TextView) dialog.findViewById(R.id.item_tvtile);
        txtnameuser = dialog.findViewById(R.id.item_txtnameuser);

        txtname = dialog.findViewById(R.id.item_txtpass);


        tilusername = dialog.findViewById(R.id.add_til_username);
        tilname = dialog.findViewById(R.id.add_til_pass);
        Button btnadd = dialog.findViewById(R.id.dialog_add_add);
        Button btncanel = dialog.findViewById(R.id.dialog_add_cancel);
        dao = new DichVuDAO(getActivity());
        if(a==-1){
            tvTile.setText("Thêm Dịch Vụ");
            tilusername.setHint("Mã Dịch Vụ ");
            tilname.setHint("Tên Dịch Vụ ");
            txtnameuser.setEnabled(false);
            if (list.size()==0){
                txtnameuser.setText("1");
            }else {
                dichVu = dao.getAll().get(list.size() -1);
                txtnameuser.setText(String.valueOf(dichVu.maDV +1));
            }
            btnadd.setOnClickListener(new View.OnClickListener() {
                DichVu dichVu = new DichVu();
                @Override
                public void onClick(View v) {
                    validate();
                    if (temp==0){
                        dichVu.tenDV = txtname.getText().toString();
                        if (dao.insert(dichVu)>0){
                            Toast.makeText(getActivity(), "Thêm Thành Công",
                                    Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            loadTable();
                        }else {
                            Toast.makeText(getActivity(), "Thêm Thất Bại",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        temp=0;
                    }
                }
            });
            btncanel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Hủy Thêm",
                            Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        }else {
            tvTile.setText("Sửa/Xóa Dịch Vu");
            tilusername.setHint("Mã Dịch Vụ");
            tilname.setHint("Tên Dịch Vụ");
            btnadd.setText("Sửa");
            btncanel.setText("Xóa");
            dichVu = list.get(a);
            txtnameuser.setText(String.valueOf(dichVu.maDV));
            txtnameuser.setEnabled(false);
            txtname.setText(dichVu.tenDV);
            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(temp==0){
                        dichVu = new DichVu();
                        dichVu.maDV = Integer.parseInt(txtnameuser.getText().toString());
                        dichVu.tenDV = txtname.getText().toString();
                        if(dao.update(dichVu)<0){
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
            btncanel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sanPhamList = sanPhamDAO.getAll();
                    for (int i = 0; i < sanPhamList.size(); i++) {
                        if (sanPhamList.get(i).maDV == dichVu.maDV){
                            Toast.makeText(getActivity(),
                                    "Không thể xoá loại sách có trong sách",
                                    Toast.LENGTH_SHORT).show();
                            temp++;
                            break;
                        }
                    }
                    if (temp==0){
                        if (dao.delete(String.valueOf(dichVu.maDV))<0){
                            Toast.makeText(getActivity(), "Xóa Thất Bại",
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), "Xóa Thành Công",
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
        if (txtname.getText().length()==0){
            tilname.setError("Tên dịch vụ không được để trống");
            temp++;
        }else {
            tilname.setError("");
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }
}