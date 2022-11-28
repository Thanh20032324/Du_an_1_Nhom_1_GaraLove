package com.example.du_an_1.main.ui;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Shader;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.du_an_1.DAO.NhanVienDAO;
import com.example.du_an_1.MainActivity;
import com.example.du_an_1.R;
import com.example.du_an_1.databinding.FragmentMatKhauBinding;
import com.example.du_an_1.model.NhanVien;
import com.google.android.material.textfield.TextInputLayout;

public class MatKhau extends Fragment {
    private FragmentMatKhauBinding binding;
    private EditText MkKu,MkMoi,NhapLaiMk;
    private Button BtnTheem,BtnHuyr;
    NhanVienDAO dao = new NhanVienDAO(getActivity());
    TextInputLayout tilMkKu,tilMkMoi,tilNhapLaiMk;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_mat_khau, container, false);
        binding = FragmentMatKhauBinding.inflate(inflater,container,false);
        View rooView= binding.getRoot();
        MkKu = rooView.findViewById(R.id.pass_oldpass);
        MkMoi = rooView.findViewById(R.id.pass_newpass);
        NhapLaiMk = rooView.findViewById(R.id.pass_newpasscheck);
        BtnTheem = rooView.findViewById(R.id.pass_btnsave);
        BtnHuyr = rooView.findViewById(R.id.pass_btncancel);
        tilMkKu = rooView.findViewById(R.id.pass_oldpass);
        tilMkMoi = rooView.findViewById(R.id.pass_tilnewpass);
        tilNhapLaiMk = rooView.findViewById(R.id.pass_tilnewpasscheck);
        BtnTheem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = preferences.getString("USER_NAME","");
                if(user.length()==0){
                    Intent intent1 = getActivity().getIntent();
                    String user1 = intent1.getStringExtra("user");
                    user = user1;


                }
                if (validate()>0){
                    NhanVien nhanVien = dao.getID(user);
                    nhanVien.matKhau = MkMoi.getText().toString();
                    dao.update(nhanVien);
                if (dao.update(nhanVien)>0){
                    Toast.makeText(getActivity(),"Thay dổi mật khaaurthanhd cọn",Toast.LENGTH_SHORT).show();
                    MkMoi.setText("");
                    MkKu.setText("");
                    NhapLaiMk.setText("");
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getActivity(), "Không Thành Koong", Toast.LENGTH_SHORT).show();
                }
                }

            }


        });
        BtnHuyr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MkKu.setText("");
                MkMoi.setText("");
                NhapLaiMk.setText("");
                tilMkKu.setError("");
                tilMkMoi.setError("");
                tilNhapLaiMk.setError("");
            }
        });
        return rooView;
    }

    private int validate() {
        int check = 1;
        int temp = 0;
        if (MkKu.getText().length() == 0) {
            tilMkKu.setError("Mật khâu cũ không được để trống");
            temp++;
            check = -1;
        } else {
            tilMkKu.setError("");
        }
        if (MkMoi.getText().length() == 0) {
            tilMkMoi.setError("Mật khâu cũ không được để trống");
            temp++;
            check = -1;
        } else {
            tilMkMoi.setError("");
        }
        if (NhapLaiMk.getText().length() == 0) {
            tilNhapLaiMk.setError("Mật khâu cũ không được để trống");
            temp++;
            check = -1;
        } else {
            tilNhapLaiMk.setError("");
        }
        if (temp == 0) {
            SharedPreferences preferences1 = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String KtMKcu = preferences1.getString("PASSWORD", "");
            if (KtMKcu.length() == 0) {
                Intent intent = getActivity().getIntent();
                String pass = intent.getStringExtra("PASS");
                KtMKcu = pass;

            }
            String MkNew = MkMoi.getText().toString();
            String MkNhApLaI = NhapLaiMk.getText().toString();
            if (!KtMKcu.equals(MkKu.getText().toString())) {
                tilMkKu.setError("Mật khẩu cũ sai");
                check = -1;

            }
            if (!MkNew.equals(MkNhApLaI)) {
                tilNhapLaiMk.setError("mật khẩu không trùng khớp ");
            }
        }
        else {
            temp = 0;

        }
        return check;
    }

}