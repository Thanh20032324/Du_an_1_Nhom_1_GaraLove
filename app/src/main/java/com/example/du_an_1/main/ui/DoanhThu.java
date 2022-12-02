package com.example.du_an_1.main.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an_1.DAO.DoanhThuDAO;
import com.example.du_an_1.R;
import com.example.du_an_1.databinding.FragmentDoanhThuBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class DoanhThu extends Fragment {
    private FragmentDoanhThuBinding binding;
    Button btnDoanhThu;
    ImageView btnTuNgay, btnDenNgay;
    EditText edTuNgay, edDenNgay;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    int mYear, mMonth, mDay;
    TextView tv;
    int temp=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDoanhThuBinding.inflate(inflater, container , false);
        View root = binding.getRoot();
        btnTuNgay = root.findViewById(R.id.doanhthu_img_tungay);
        btnDenNgay = root.findViewById(R.id.doanhthu_img_denngay);
        btnDoanhThu = root.findViewById(R.id.doanhthu_btn_tinh);

        edTuNgay = root.findViewById(R.id.doanhthu_edt_tungay);
        edDenNgay = root.findViewById(R.id.doanhthu_edt_denngay);

        tv =root.findViewById(R.id.doanhthu_tv);
        btnTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(),0,mDateTuNgay,mYear,mMonth,mDay);
                d.show();
            }
        });
        btnDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(),0,mDateDenNDen,mYear,mMonth,mDay);
                d.show();
            }
        });
        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tuNgay = edTuNgay.getText().toString();
                String denNgay = edDenNgay.getText().toString();
                if (tuNgay.isEmpty()||denNgay.isEmpty()){
                    Toast.makeText(getActivity(), "Không được để trống", Toast.LENGTH_SHORT).show();
                    temp++;
                }else {
                    String[] temptungay = tuNgay.split("-");
                    String[] tempdenngay = denNgay.split("-");

                    String newTungay = "";
                    String newdenngay = "";

                    int inttungay = Integer.parseInt(newTungay.concat(temptungay[0]).concat(temptungay[1]).concat(temptungay[2]));
                    int intdenngay = Integer.parseInt(newdenngay.concat(tempdenngay[0]).concat(tempdenngay[1]).concat(tempdenngay[2]));

                    if (inttungay > intdenngay) {
                        Toast.makeText(getActivity(), "Lỗi, từ ngày phải bé hơn đến ngày", Toast.LENGTH_SHORT).show();
                        temp++;
                    }
                }

                if (temp==0){
                    DoanhThuDAO dao = new DoanhThuDAO(getActivity());
                    tv.setText("Doanh thu: "+dao.getDoanhThu(tuNgay,denNgay)+" VNĐ");
                }else {
                    temp=0;
                }
            }
        });
        return root;
   }
   DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
       @Override
       public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
           mYear = year;
           mMonth = monthOfYear;
           mDay = dayOfMonth;
           GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
           edTuNgay.setText(sdf.format(c.getTime()));
       }
   };
    DatePickerDialog.OnDateSetListener mDateDenNDen = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar c =new GregorianCalendar(mYear,mMonth,mDay);
            edDenNgay.setText(sdf.format(c.getTime()));
        }
    };
}