package com.example.ailatrieuphu.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ailatrieuphu.Adapter.LinhVucAdapter;
import com.example.ailatrieuphu.Class.LinhVuc;
import com.example.ailatrieuphu.Class.NguoiChoi;
import com.example.ailatrieuphu.Loader.LinhVucLoader;
import com.example.ailatrieuphu.Loader.NguoiChoiLoader;
import com.example.ailatrieuphu.NetworkUtils;
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


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{
    // giao diện lựa chọn lĩnh vực
    private SharedPreferences mPref;
    private String sharePrefFile = "com.example.ailatrieuphu";
    private RecyclerView rcvLinhVucs;
    private LinhVucAdapter adapter;
    private List<LinhVuc> linhVucs = new ArrayList<>();
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

    public void login(View view)
    {
        EditText mTenDangNhap = findViewById(R.id.editTextAccount);
        EditText mMatKhau = findViewById(R.id.editTextPassword);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;

        if (connMgr != null)
        {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected())
        {
            new FetchDangNhap(){
                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        // Lấy giá trị của key "success"
                        boolean success = jsonObject.getBoolean("status");
                        String message = jsonObject.getString("message");
                        if(success) {
                            // Lưu token vao Shared Preferences
                            String token = jsonObject.getString("token");

                            SharedPreferences.Editor editor = mPref.edit();
                            editor.putString("TOKEN", token);
                            editor.apply();

                            // Mở activity Màn Hình Chính
                            Intent intent = new Intent(MainActivity.this, GiaoDienChinh.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                        }

                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            }.execute("dang-nhap", "POST", mTenDangNhap.getText().toString(), mMatKhau.getText().toString());

        }
        else
        {
            Toast.makeText(this, "Khong the ket noi den server", Toast.LENGTH_SHORT).show();
        }
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
                int idLinhVuc=objectItemLinhVuc.getInt("id");
                String ten = objectItemLinhVuc.getString("ten_linh_vuc");
                LinhVuc linhVuc= new LinhVuc();
                linhVuc.setTenLinhVuc(ten);
                linhVuc.setId(idLinhVuc);
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




}
