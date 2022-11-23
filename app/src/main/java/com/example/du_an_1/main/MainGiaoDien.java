package com.example.du_an_1.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.example.du_an_1.Adapter.AdapterDichVu;
import com.example.du_an_1.DAO.DichVuDAO;
import com.example.du_an_1.DAO.NhanVienDAO;
import com.example.du_an_1.R;
import com.example.du_an_1.databinding.ActivityMainGiaoDienBinding;
import com.example.du_an_1.model.DichVu;
import com.example.du_an_1.model.NhanVien;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainGiaoDien extends AppCompatActivity {
Context context = this;
TextView nameuser;
View view;
NhanVien nhanVien;
NhanVienDAO nhanVienDAO;
List<NhanVien> nhanVienList;

DichVuDAO dichVuDAO;
AdapterDichVu adapterDichVu;
List<DichVu> vuList;
ListView listView;



private AppBarConfiguration mAppBarConfiguration;
private ActivityMainGiaoDienBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        vuList = new ArrayList<>();
        dichVuDAO = new DichVuDAO(getApplicationContext());
        vuList = dichVuDAO.getAll();
        adapterDichVu = new AdapterDichVu(getApplicationContext(),R.layout.item_lv_addnv,vuList);
        binding = ActivityMainGiaoDienBinding.inflate(
                getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMaingiaodien.toolbar);

        nhanVien = new NhanVien();
        nhanVienDAO = new NhanVienDAO(getApplicationContext());
        nhanVienList = new ArrayList<>();

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_qlphieuthu, R.id.nav_qlloaisach,R.id.nav_quanlysach,
                R.id.nav_qlthanhvien,
                R.id.nav_top,
                R.id.nav_doanhthu,
                R.id.nav_them,
                R.id.nav_matkhau,
                R.id.nav_dangxuat).setOpenableLayout(drawer).build();
        NavController navController = Navigation.findNavController(this,
                R.id.nav_host_fragment_content_maingiaodien);
        NavigationUI.setupActionBarWithNavController(this,navController,mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView,navController);
        view = navigationView.getHeaderView(0);
        nameuser = view.findViewById(R.id.login_nameuser);
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        nhanVienList = nhanVienDAO.getAll();
        if(user.equalsIgnoreCase("admin")){
            navigationView.getMenu().findItem(R.id.nav_them).setVisible(true);
        }
        for (int i = 0; i<nhanVienList.size();i++){
            if(nhanVienList.get(i).maNV.equals(user)){
                nameuser.setText("Xin Chào"+nhanVienList.get(i).hoTen);
                return;
            }
        }
    }
    @Override
    public boolean onSupportNavigateUp(){
        NavController navController = Navigation.findNavController(this,
                R.id.nav_host_fragment_content_maingiaodien);
        return NavigationUI.navigateUp(navController,mAppBarConfiguration) || super.onSupportNavigateUp();
    }
}