package com.example.du_an_1;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;

public class MainManHinhChao extends AppCompatActivity {
ImageView imglogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_man_hinh_chao);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        },3000);
        imglogo = findViewById(R.id.imageView2);
        AnimatorSet animato = (AnimatorSet) AnimatorInflater.loadAnimator(MainManHinhChao.this,R.animator.img_logo);
        animato.setTarget(imglogo);
        animato.start();
    }
}