package com.example.ailatrieuphu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.widget.ShareDialog;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public class GiaoDienChinh extends AppCompatActivity {
//    private ShareDialog shareDialog;
    //private Button logout;

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
    }
    public void ChuyenTrang(View view){
        Intent intent=new Intent(this,MainActivity.class);
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
