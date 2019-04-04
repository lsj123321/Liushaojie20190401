package com.bawei.liushaojie.view.activity;

import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bawei.liushaojie.R;
import com.bawei.liushaojie.view.fragment.Frag_biaoqian;
import com.bawei.liushaojie.view.fragment.Frag_faxian;
import com.bawei.liushaojie.view.fragment.Frag_geren;
import com.bawei.liushaojie.view.fragment.Frag_shop;
import com.bawei.liushaojie.view.fragment.Frag_show;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Frag_show frag_show;
    private Frag_faxian frag_faxian;
    private Frag_shop frag_shop;
    private Frag_biaoqian frag_biaoqian;
    private Frag_geren frag_geren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.show).setOnClickListener(this);
        findViewById(R.id.faxian).setOnClickListener(this);
        findViewById(R.id.shop).setOnClickListener(this);
        findViewById(R.id.biaoqian).setOnClickListener(this);
        findViewById(R.id.geren).setOnClickListener(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        frag_show = new Frag_show();
        frag_faxian = new Frag_faxian();
        frag_shop = new Frag_shop();
        frag_biaoqian = new Frag_biaoqian();
        frag_geren = new Frag_geren();
        //设置背景色
        findViewById(R.id.show).setBackgroundColor(Color.RED);
        transaction.add(R.id.frag,frag_show);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()){
            case R.id.show:
                transaction.replace(R.id.frag,frag_show);
                //设置背景色
                findViewById(R.id.show).setBackgroundColor(Color.RED);
                findViewById(R.id.faxian).setBackgroundColor(Color.WHITE);
                findViewById(R.id.shop).setBackgroundColor(Color.WHITE);
                findViewById(R.id.biaoqian).setBackgroundColor(Color.WHITE);
                findViewById(R.id.geren).setBackgroundColor(Color.WHITE);
                break;
            case R.id.faxian:
                transaction.replace(R.id.frag,frag_faxian);
                findViewById(R.id.show).setBackgroundColor(Color.WHITE);
                findViewById(R.id.faxian).setBackgroundColor(Color.RED);
                findViewById(R.id.shop).setBackgroundColor(Color.WHITE);
                findViewById(R.id.biaoqian).setBackgroundColor(Color.WHITE);
                findViewById(R.id.geren).setBackgroundColor(Color.WHITE);
                break;
            case R.id.shop:
                transaction.replace(R.id.frag,frag_shop);
                findViewById(R.id.show).setBackgroundColor(Color.WHITE);
                findViewById(R.id.faxian).setBackgroundColor(Color.WHITE);
                findViewById(R.id.shop).setBackgroundColor(Color.RED);
                findViewById(R.id.biaoqian).setBackgroundColor(Color.WHITE);
                findViewById(R.id.geren).setBackgroundColor(Color.WHITE);
                break;
            case R.id.biaoqian:
                transaction.replace(R.id.frag,frag_biaoqian);
                findViewById(R.id.show).setBackgroundColor(Color.WHITE);
                findViewById(R.id.faxian).setBackgroundColor(Color.WHITE);
                findViewById(R.id.shop).setBackgroundColor(Color.WHITE);
                findViewById(R.id.biaoqian).setBackgroundColor(Color.RED);
                findViewById(R.id.geren).setBackgroundColor(Color.WHITE);
                break;
            case R.id.geren:
                transaction.replace(R.id.frag,frag_geren);
                findViewById(R.id.show).setBackgroundColor(Color.WHITE);
                findViewById(R.id.faxian).setBackgroundColor(Color.WHITE);
                findViewById(R.id.shop).setBackgroundColor(Color.WHITE);
                findViewById(R.id.biaoqian).setBackgroundColor(Color.WHITE);
                findViewById(R.id.geren).setBackgroundColor(Color.RED);
                break;
        }
        transaction.commit();
    }
}
