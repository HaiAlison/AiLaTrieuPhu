package com.example.ailatrieuphu;

import android.os.Bundle;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ailatrieuphu.Adapter.TableRankAdapter;
import com.example.ailatrieuphu.Class.NguoiChoi;
import com.example.ailatrieuphu.Loader.NguoiChoiLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ranked_member_table extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private RecyclerView rcvTableScore;
    private ArrayList<NguoiChoi> nguoiChois=new ArrayList<>();
    private TableRankAdapter tableRankAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranked_member_table);
        radiation();
        setAdapterNguoiChoi();
        if(getSupportLoaderManager().getLoader(0)!=null){
            getSupportLoaderManager().initLoader(0,null,this);
        }
        getSupportLoaderManager().restartLoader(0,null,this);
    }

    private void setAdapterNguoiChoi() {
        tableRankAdapter=new TableRankAdapter(this,nguoiChois);
        rcvTableScore.setLayoutManager(new LinearLayoutManager(this));
        rcvTableScore.setAdapter(tableRankAdapter);
    }

    private void radiation() {
        rcvTableScore=findViewById(R.id.rcv_table_ranked_member);
    }
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new NguoiChoiLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        JSONObject objectNguoiChoi =null;
        try {
            objectNguoiChoi=new JSONObject(data);
            JSONArray arrNguoiChoi=objectNguoiChoi.getJSONArray("data");
            for(int i=0;i<arrNguoiChoi.length();i++){
                JSONObject objectItemNguoiChoi=arrNguoiChoi.getJSONObject(i);
                String ten = objectItemNguoiChoi.getString("ten_dang_nhap");
                int score = objectItemNguoiChoi.getInt("diem_cao_nhat");
                NguoiChoi nguoiChoi=new NguoiChoi();
                nguoiChoi.setTenTaiKhoan(ten);
                nguoiChoi.setDiemCaoNhat(score);
                nguoiChois.add(nguoiChoi);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        tableRankAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
