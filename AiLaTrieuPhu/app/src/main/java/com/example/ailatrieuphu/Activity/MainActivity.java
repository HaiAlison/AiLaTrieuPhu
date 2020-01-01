package com.example.ailatrieuphu.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ailatrieuphu.Adapter.LinhVucAdapter;
import com.example.ailatrieuphu.Class.LinhVuc;
import com.example.ailatrieuphu.Class.NguoiChoi;
import com.example.ailatrieuphu.Loader.LinhVucLoader;
import com.example.ailatrieuphu.Loader.NguoiChoiLoader;
import com.example.ailatrieuphu.NetworkUtils;
import com.example.ailatrieuphu.Question.AmNhac_PhimActivity;
import com.example.ailatrieuphu.Question.KhoaHocTuNhienActivity;
import com.example.ailatrieuphu.Question.LichSuActivity;
import com.example.ailatrieuphu.Question.TheoThaoActivity;
import com.example.ailatrieuphu.Question.VanHoa_SuKienActivity;
import com.example.ailatrieuphu.Question.YHocActivity;
import com.example.ailatrieuphu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.ailatrieuphu.Activity.GiaoDienChinh.KEY_REQUESTCODE;
import static com.example.ailatrieuphu.NetworkUtils.BASE_URL;
import static com.example.ailatrieuphu.NetworkUtils.URI_LINH_VUC;
import static com.example.ailatrieuphu.Other.GlobalVariables.KEY_DANGNHAP;
import static com.example.ailatrieuphu.Other.GlobalVariables.KEY_LIMIT;
import static com.example.ailatrieuphu.Other.GlobalVariables.KEY_PAGE;
import static com.example.ailatrieuphu.Other.GlobalVariables.LIMIT_KHOI_TAO;
import static com.example.ailatrieuphu.Other.GlobalVariables.PAGE_KHOI_TAO;
import static com.example.ailatrieuphu.Other.GlobalVariables.PAGE_SIZE;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{
    // giao diện lựa chọn lĩnh vực
    private TextView tvTen,tvTinDung;
    private RecyclerView rcvLinhVucs;
    private LinhVucAdapter adapter;
    private List<LinhVuc> linhVucs = new ArrayList<>();
    private NguoiChoi nguoiChoi;
    private boolean checkLoading = false;
    private boolean checkLastPage = false;
    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radiation();
        createAdapter();
        if(getSupportLoaderManager().getLoader(0)!=null){
            getSupportLoaderManager().initLoader(0,null,this);
        }
        getSupportLoaderManager().restartLoader(0,null,this);
    }

    private void radiation() {
        rcvLinhVucs=findViewById(R.id.rcvLinhVuc);
    }
    private void createAdapter()
    {
        adapter=new LinhVucAdapter(this,linhVucs);
        rcvLinhVucs.setLayoutManager(new LinearLayoutManager(this));
        rcvLinhVucs.setAdapter(adapter);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new LinhVucLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        JSONObject objectLinhVuc =null;
        try {
            objectLinhVuc=new JSONObject(data);
            JSONArray arrNguoiChoi=objectLinhVuc.getJSONArray("data");
            for(int i=0;i<arrNguoiChoi.length();i++){
                JSONObject objectItemLinhVuc=arrNguoiChoi.getJSONObject(i);
                String ten = objectItemLinhVuc.getString("ten_linh_vuc");
                LinhVuc linhVuc=new LinhVuc();
                linhVuc.setTenLinhVuc(ten);
                linhVucs.add(linhVuc);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }


//    private void checkScroll() {
//        rcvLinhVuc.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                LinearLayoutManager manager = (LinearLayoutManager) rcvLinhVuc.getLayoutManager();
//                int countItem = manager.getItemCount();
//                int countChild = manager.getChildCount();
//                int findNextChild = manager.findFirstVisibleItemPosition();
//                if ((findNextChild+countChild) >= countItem && findNextChild >=0 && countItem >= PAGE_SIZE){
//                    if(!checkLoading && !checkLastPage){
//                        checkLoading = true;
//                        currentPage++;
//
//                        linhVucs.add(null);
//                        rcvLinhVuc.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                adapter.notifyItemInserted(linhVucs.size()-1);
//
//                            }
//                        });
//
//                        Bundle data = new Bundle();
//                        data.putInt(KEY_PAGE,currentPage);
//                        data.putInt(KEY_LIMIT,PAGE_SIZE);
//                        loadData(data);
//                    }
//                }
//            }
//        });
//    }
//
//    private void loadData(Bundle data) {
//        if (NetworkUtils.checkConnect(this)){
//            startVolley(data);
//        }else{
//            NetworkUtils.showDialogNetWork(getString(R.string.tb_connect_internet),this);
//        }
//    }
//
//    private void startVolley(Bundle data) {
//        final Map<String,String> startMap =new HashMap<>();
//        startMap.put(GiaoDienChinh.KEY_PAGE,String.valueOf(data == null ? PAGE_KHOI_TAO : data.getInt(GiaoDienChinh.KEY_PAGE)));
//        startMap.put(GiaoDienChinh.KEY_LIMIT,String.valueOf(data == null ? LIMIT_KHOI_TAO : data.getInt(GiaoDienChinh.KEY_LIMIT)));
//        StringRequest request = new StringRequest(Request.Method.POST,BASE_URL + URI_LINH_VUC, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject objLinhVuc = new JSONObject(response);
//                    int total = objLinhVuc.getInt("total");
//                    int totalPage = total/PAGE_SIZE;
//                    if (totalPage % PAGE_SIZE != 0){
//                        totalPage++;
//                    }
//                    if (linhVucs.size() > 0){
//                        linhVucs.remove(linhVucs.size()-1);
//                        adapter.notifyItemRemoved(linhVucs.size());
//                    }
//
//                    JSONArray arrNguoiChoi = objLinhVuc.getJSONArray("linh_vuc");
//                    for (int i = 0; i <arrNguoiChoi.length() ; i++) {
//                        JSONObject objItem = arrNguoiChoi.getJSONObject(i);
//                        int id = objItem.getInt("id");
//                        String tenLinhVuc = objItem.getString("ten_linh_vuc");
//                        LinhVuc linhVuc = new LinhVuc();
//                        linhVuc.setId(id);
//                        linhVuc.setTenLinhVuc(tenLinhVuc);
//                        linhVucs.add(linhVuc);
//                    }
//                    adapter.notifyDataSetChanged();
//                    checkFinishLoading(totalPage);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(),"Server Offline",Toast.LENGTH_SHORT).show();
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                return startMap;
//            }
//        };
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//        queue.add(request);
//    }
//
//    private void checkFinishLoading(int totalPage) {
//        checkLoading = false;
//        checkLastPage=(currentPage == totalPage);
//    }
//
//    private void createAdapter() {
//        adapter = new LinhVucAdapter(this,linhVucs,this.nguoiChoi);
//        rcvLinhVuc.setLayoutManager(new LinearLayoutManager(this));
//        rcvLinhVuc.setHasFixedSize(true);
//        rcvLinhVuc.setAdapter(adapter);
//    }
//
//    private void showNameAndCredit() {
//        //Muốn chạy thì bật chỗ này nhé
//        this.nguoiChoi = (NguoiChoi) getIntent().getSerializableExtra(KEY_DANGNHAP);
//        tvTen.setText(this.nguoiChoi.getTenTaiKhoan());
//        tvTinDung.setText(this.nguoiChoi.getCredit()+"");
//    }
//
//    private void radiation() {
////        View vHeader = findViewById(R.id.vHeader);
////        tvTen =  vHeader.findViewById(R.id.textTenNguoiChoi);
////        tvTinDung = vHeader.findViewById(R.id.tvCredit);
//        rcvLinhVuc = findViewById(R.id.rcvLinhVuc);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (requestCode == KEY_REQUESTCODE && resultCode == RESULT_OK && data!=null){
//            this.nguoiChoi = (NguoiChoi) data.getSerializableExtra(KEY_DANGNHAP);
//            tvTinDung.setText(this.nguoiChoi.getCredit()+"");
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent();
//        intent.putExtra(KEY_DANGNHAP, (Serializable) nguoiChoi);
//        setResult(RESULT_OK,intent);
//        finish();
//    }


}
