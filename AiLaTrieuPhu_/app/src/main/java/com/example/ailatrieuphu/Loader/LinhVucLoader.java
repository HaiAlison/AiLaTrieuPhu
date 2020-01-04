package com.example.ailatrieuphu.Loader;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.ailatrieuphu.NetworkUtils;

public class LinhVucLoader extends AsyncTaskLoader<String> {
    public LinhVucLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getJSONData("linh-vuc",NetworkUtils.GET,null);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
