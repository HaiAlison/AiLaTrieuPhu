package com.example.ailatrieuphu.Loader;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;

import com.example.ailatrieuphu.NetworkUtils;

public class NguoiChoiLoader extends AsyncTaskLoader<String> {
    public NguoiChoiLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getJSONData("nguoi-choi",NetworkUtils.GET,null);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
