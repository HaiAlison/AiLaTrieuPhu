package com.example.ailatrieuphu.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ailatrieuphu.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity {
    //giao diện đăng nhập
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    LoginButton loginButton;
    private CallbackManager callbackManager;
    private ProfileTracker profileTracker;
    private AccessTokenTracker accessTokenTracker;
    private SharedPreferences mPref;
    private String sharePrefFile = "com.example.ailatrieuphu";
    //note lỗi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main2);
        mPref = getSharedPreferences(sharePrefFile, MODE_PRIVATE);

        //Kiểm tra Token trong Shared Preferences
        //Nếu có Token thì chuyển qua màn hình Chính

        String token = mPref.getString("TOKEN",null);
        if(token != null)
        {
            //Mở activity màn hình chính
            Intent intent = new Intent(this,GiaoDienChinh.class);
            startActivity(intent);
        }


        //Đăng ký gọi lại để xử lý các phản hồi đăng nhập
        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker=new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };
        profileTracker=new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                nextActivity(currentProfile);
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        FacebookCallback<LoginResult> callback=new FacebookCallback<LoginResult>() {
            @Override
            //thực hiện các hàm, câu lệnh khi đăng nhập thành công
            public void onSuccess(LoginResult loginResult) {
                Profile profile=Profile.getCurrentProfile();
                nextActivity(profile);
                Toast.makeText(getApplicationContext(),"Loggin in...",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        };
        //Đăng ký gọi lại để nhận thông tin public trên FB
        loginButton.setReadPermissions("public_profile");
        loginButton.registerCallback(callbackManager,callback);
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
                            Intent intent = new Intent(Main2Activity.this, GiaoDienChinh.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(Main2Activity.this, message, Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onResume() {
        super.onResume();
        Profile profile=Profile.getCurrentProfile();
        nextActivity(profile);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }
    //Gọi callbackManager.onActivityResult
    //để chuyển kết quả đăng nhập đến LoginManager qua callbackManager
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void launchResignActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent=new Intent(this,ResignActivity.class);
        startActivity(intent);
    }

    public void launchForgotPActivity(View view) {
        Log.d(LOG_TAG,"Button Clicked");
        Intent intent=new Intent(this,ForgotPasswordActivity.class);
        startActivity(intent);
    }
     @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
     }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void dangNhapGiaoDien(View view) {
        Intent intent=new Intent(this,GiaoDienChinh.class);
        startActivity(intent);
    }
    private void nextActivity(Profile profile)
    {
        //nếu đăng nhập thành công thì sẽ truyền dữ liệu qua bên Activity khác bao gồm tên và avata
        if(profile!=null)
        {
            Intent intent=new Intent(this,GiaoDienChinh.class);
            //yêu cầu FB cấp thông tin tên, và ảnh đại diện
            intent.putExtra("name",profile.getFirstName());
            intent.putExtra("surename",profile.getLastName());
            intent.putExtra("imageUrl",profile.getProfilePictureUri(162,181).toString());
            startActivity(intent);
        }

    }
}





