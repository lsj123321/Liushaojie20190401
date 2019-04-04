package com.bawei.titleactivitydemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MyView extends ViewGroup {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //View view=View.inflate(getContext(),R.layout.title,null);
        TextView back=findViewById(R.id.back);
        TextView back_text=findViewById(R.id.back_text);
        TextView back_ti=findViewById(R.id.back_ti);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击返回,可在此处调用finish()",Toast.LENGTH_SHORT).show();
            }
        });
        back_text.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击返回,可在此处调用finish()",Toast.LENGTH_SHORT).show();
            }
        });
        back_ti.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"我是提交按钮",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
