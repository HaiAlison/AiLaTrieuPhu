package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ailatrieuphu.Question.TheoThaoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button KhoaHocKyThuat=(Button)findViewById(R.id.btn_DapAn_D);
        KhoaHocKyThuat.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
//                KhoaHocKyThuatFragment khoaHocKyThuatFragment=new KhoaHocKyThuatFragment();
//                FragmentManager manager=getSupportFragmentManager();
//                manager.beginTransaction().replace(R.id.content_linhvuc,khoaHocKyThuatFragment,khoaHocKyThuatFragment.getTag()).commit();
            }
        });
    }

    public void TheThaoActivity(View view) {
        Intent intent=new Intent(this, TheoThaoActivity.class);
        startActivity(intent);
    }
}
