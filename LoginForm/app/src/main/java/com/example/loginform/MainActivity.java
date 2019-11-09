package com.example.loginform;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    //note lỗi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.loginform",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures)
                {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                }
            } catch (PackageManager.NameNotFoundException e) {

            } catch (NoSuchAlgorithmException e) {

        }

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
}





