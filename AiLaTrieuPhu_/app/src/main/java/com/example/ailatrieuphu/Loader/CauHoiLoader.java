package com.example.ailatrieuphu.Loader;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.ailatrieuphu.Activity.HienThiCauHoiActivity;
import com.example.ailatrieuphu.NetworkUtils;

import static android.content.Intent.getIntent;

public class CauHoiLoader extends AsyncTaskLoader<String> {
    private int id;
    public CauHoiLoader(@NonNull Context context,int id_linh_vuc) {
        super(context);
        this.id=id_linh_vuc;
    }
    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getJSONData("cau-hoi?linh_vuc="+this.id,NetworkUtils.GET,null);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
