package com.bawei.liushaojie20190404.veiw.activity;

import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bawei.liushaojie20190404.R;
import com.bawei.liushaojie20190404.veiw.fragment.Mine_Frag;
import com.bawei.liushaojie20190404.veiw.fragment.Show_Frag;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button show;
    private Button mine;
    private Show_Frag show_frag;
    private Mine_Frag mine_frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //查找资源id
        show = findViewById(R.id.show);
        show.setOnClickListener(this);
        mine = findViewById(R.id.mine);
        mine.setOnClickListener(this);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        show_frag = new Show_Frag();
        mine_frag = new Mine_Frag();
        transaction.add(R.id.frag,show_frag);
        show.setBackgroundColor(Color.RED);
        transaction.commit();

    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()){
            case R.id.show:
                transaction.replace(R.id.frag,show_frag);
                show.setBackgroundColor(Color.RED);
                mine.setBackgroundColor(Color.WHITE);
                break;
            case R.id.mine:
                transaction.replace(R.id.frag,mine_frag);
                show.setBackgroundColor(Color.WHITE);
                mine.setBackgroundColor(Color.RED);
                break;
        }
        transaction.commit();
    }
}
