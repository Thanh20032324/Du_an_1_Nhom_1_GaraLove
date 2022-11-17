package com.example.du_an_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.du_an_1.DAO.NhanVienDAO;
import com.example.du_an_1.main.MainGiaoDien;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
EditText edusername,edpass;
Button login;
CheckBox cb;
NhanVienDAO dao;
String strUser,strPass;
TextInputLayout tillpass,tilluser;
int temp=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setTitle("Đăng Nhập");
        tillpass = findViewById(R.id.login_tilpassword);
        tilluser = findViewById(R.id.login_tilusername);

        edpass = findViewById(R.id.login_edpassword);
        edusername = findViewById(R.id.login_edusername);
        cb =findViewById(R.id.login_checkBox);
        login = findViewById(R.id.login_btnlogin);

        dao = new NhanVienDAO(this);
//        dao.insertadmin();
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        edusername.setText(pref.getString("USERNAME",""));
        edpass.setText(pref.getString("PASSWORD",""));
        cb.setChecked(pref.getBoolean("REMEMBER", false));

       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               checkLogin();
           }
       });
    }

    private void checkLogin() {
        strUser = edusername.getText().toString();
        strPass = edpass.getText().toString();
        if (strUser.isEmpty()){
            tilluser.setError("Tên đăng nhập không được bỏ trống");
            temp++;
        }else{
            tilluser.setError("");
        }
        if(strPass.isEmpty()){
            tillpass.setError("Mật khẩu không được bỏ trống");
            temp++;
        }else {
            tillpass.setError("");
        }
        if(temp==0){
            if(dao.checkLogin(strUser,strPass)>0){
                tilluser.setError("");
                tillpass.setError("");
                Toast.makeText(this, "Login thành công",
                        Toast.LENGTH_SHORT).show();
                rememberUser(strUser,strPass,cb.isChecked());
                Intent intent = new Intent(MainActivity.this, MainGiaoDien.class);
                intent.putExtra("user",strUser);
                intent.putExtra("pass",strPass);
                startActivity(intent);
                finish();
            }else {
                tilluser.setError("Tên đăng nhập hoặc mật khẩu không đúng");
                tillpass.setError("Tên đăng nhập hoặc mật khẩu không đúng");
            }
            }
        else {
            temp=0;
        }
    }

    private void rememberUser(String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if(!status){
            editor.clear();
        }else {
            editor.putString("USERNAME",u);
            editor.putString("PASSWORD",p);
            editor.putBoolean("REMEMBER",status);
        }
        editor.commit();
    }
}