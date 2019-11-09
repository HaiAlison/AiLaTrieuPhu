package com.example.loginform.DuLieuAiLaTrieuPhu;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseAiLaTrieuPhu extends SQLiteOpenHelper {
    // tên database cần đến
    public static String DB_name="android_ai_la_trieu_phu.db";
    // địa chỉ lưu database
    public static String DB_PATH ="";

    public Context mContext;
    public DatabaseAiLaTrieuPhu(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        if(Build.VERSION.SDK_INT>=17){
            //DB_PATH=context.getApplicationInfo().dataDir
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
