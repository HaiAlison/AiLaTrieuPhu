package com.example.ailatrieuphu.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ailatrieuphu.R;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public class GiaoDienChinh extends AppCompatActivity {
//    private ShareDialog shareDialog;
    //private Button logout;
        // giao diện hiển thị sau khi đăng nhập
    public static final String KEY_PAGE = "page";
    public static final String KEY_LIMIT = "limit";
    public static final int KEY_REQUESTCODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_giao_dien_chinh);
        //gán dữ liệu đã nhận được từ FB từ Main2Activity
        Bundle inBundle=getIntent().getExtras();
        String name=inBundle.get("name").toString();
        String surename=inBundle.get("surename").toString();
        String imageUrl=inBundle.get("imageUrl").toString();
        TextView nameView=(TextView)findViewById(R.id.text_UserName);
        nameView.setText(surename+" "+name+"");
        Button logout=(Button)findViewById(R.id.btn_Exit);
        //thiết lập sự kiện thoát khỏi Account FB và thoát khỏi trang hiện tại về trang đăng nhập
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                Intent back=new Intent(GiaoDienChinh.this,Main2Activity.class);
                startActivity(back);
                finish();
            }
        });
        //Tải lại giao diện để đổi thành ảnh đại diện trên FB
        new GiaoDienChinh.DownloadImage((ImageView)findViewById(R.id.iv_avatar)).execute(imageUrl);

        Button rank=(Button)findViewById(R.id.btnRank);
        rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GiaoDienChinh.this,ranked_member_table.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void ChuyenTrang(View view){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void ranked(View view) {
        Intent intent=new Intent(this,ranked_member_table.class);
        startActivity(intent);
    }

    //Tải dữ liệu ảnh đại diện từ FB về và gán vào lại id imageView
    public class DownloadImage extends AsyncTask<String,Void, Bitmap>{
        ImageView bmImage;
        public DownloadImage(ImageView bmImage) {
            this.bmImage=bmImage;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String urldisplay=urls[0];
            Bitmap mIconll=null;
            try {
                InputStream in=new java.net.URL(urldisplay).openStream();
                mIconll= BitmapFactory.decodeStream(in);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return mIconll;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            bmImage.setImageBitmap(result);
        }
    }
    public void ExitAccount(View view) {

    }
}
