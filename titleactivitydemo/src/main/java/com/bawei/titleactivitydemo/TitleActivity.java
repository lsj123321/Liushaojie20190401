package com.bawei.titleactivitydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TitleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        TextView back=findViewById(R.id.back);
        TextView back_text=findViewById(R.id.back_text);
        TextView back_ti=findViewById(R.id.back_ti);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TitleActivity.this,"点击返回,可在此处调用finish()",Toast.LENGTH_SHORT).show();
            }
        });
        back_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        back_ti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TitleActivity.this,"我是提交按钮",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
