package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ailatrieuphu.Question.AmNhac_PhimActivity;
import com.example.ailatrieuphu.Question.KhoaHocTuNhienActivity;
import com.example.ailatrieuphu.Question.LichSuActivity;
import com.example.ailatrieuphu.Question.TheoThaoActivity;
import com.example.ailatrieuphu.Question.VanHoa_SuKienActivity;
import com.example.ailatrieuphu.Question.YHocActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void TheThao_activity(View view) {
        Intent intent=new Intent(this, TheoThaoActivity.class);
        startActivity(intent);
    }

    public void LichSu_activity(View view) {
        Intent intent=new Intent(this, LichSuActivity.class);
        startActivity(intent);
    }

    public void AmNhac_Phim_activity(View view) {
        Intent intent=new Intent(this, AmNhac_PhimActivity.class);
        startActivity(intent);
    }


    public void VanHoa_Sukien_activity(View view) {
        Intent intent=new Intent(this, VanHoa_SuKienActivity.class);
        startActivity(intent);
    }

    public void YHoc_activity(View view) {
        Intent intent=new Intent(this, YHocActivity.class);
        startActivity(intent);
    }

    public void khoahoctunhien_activity(View view) {
        Intent intent=new Intent(this, KhoaHocTuNhienActivity.class);
        startActivity(intent);
    }
}
