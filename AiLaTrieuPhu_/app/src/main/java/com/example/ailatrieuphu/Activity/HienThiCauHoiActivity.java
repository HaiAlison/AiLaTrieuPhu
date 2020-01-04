package com.example.ailatrieuphu.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ailatrieuphu.Adapter.CauHoiAdapter;
import com.example.ailatrieuphu.Adapter.LinhVucAdapter;
import com.example.ailatrieuphu.Class.CauHoi;
import com.example.ailatrieuphu.Class.LinhVuc;
import com.example.ailatrieuphu.Class.NguoiChoi;
import com.example.ailatrieuphu.Loader.CauHoiLoader;
import com.example.ailatrieuphu.NetworkUtils;
import com.example.ailatrieuphu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.ailatrieuphu.NetworkUtils.BASE_URL;
import static com.example.ailatrieuphu.NetworkUtils.URI_CAU_HOI;

public class HienThiCauHoiActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private Intent intent;
    TextView tvCauHoi;
    Button paA_btn,paB_btn,paC_btn,paD_btn;
    private static TextView tv_count_down;
    public int counter = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_cau_hoi);
        intent = getIntent();
        tvCauHoi = findViewById(R.id.vQuestion);
        paA_btn = findViewById(R.id.btn_DapAn_A);
        paB_btn = findViewById(R.id.btn_DapAn_B);
        paC_btn = findViewById(R.id.btn_DapAn_C);
        paD_btn = findViewById(R.id.btn_DapAn_D);
        tv_count_down = findViewById(R.id.textCountTime);
        new CountDownTimer(10000,1000){
            @Override
            public void onTick(long millisUntilFinished){
                tv_count_down.setText(String.valueOf(counter));
                counter--;
            }
            @Override
            public void onFinish(){
                tv_count_down.setText("Game over");
            }
        }.start();
        if(getSupportLoaderManager().getLoader(0)!=null){
            getSupportLoaderManager().initLoader(0,null,this);
        }
        getSupportLoaderManager().restartLoader(0,null,this);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        int id_linh_vuc = intent.getIntExtra("linh_vuc_id",0);
        return new CauHoiLoader(this,id_linh_vuc);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        JSONObject jsonObject =null;
        try {
            jsonObject = new JSONObject(data);
            JSONArray cauHoi_jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < cauHoi_jsonArray.length(); i++) {
                JSONObject objectItemCauHoi=cauHoi_jsonArray.getJSONObject(i);
                String noiDungCauHoi = objectItemCauHoi.getString("noi_dung");
                String phuongAnA = objectItemCauHoi.getString("phuong_an_a");
                String phuongAnB = objectItemCauHoi.getString("phuong_an_b");
                String phuongAnC = objectItemCauHoi.getString("phuong_an_c");
                String phuongAnD = objectItemCauHoi.getString("phuong_an_d");
                tvCauHoi.setText(noiDungCauHoi);
                paA_btn.setText(phuongAnA);
                paB_btn.setText(phuongAnB);
                paC_btn.setText(phuongAnC);
                paD_btn.setText(phuongAnD);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }


}
