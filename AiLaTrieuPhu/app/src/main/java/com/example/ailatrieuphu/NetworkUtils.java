package com.example.ailatrieuphu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    public static final String BASE_URL =  "http://limitless-ocean-16835.herokuapp.com/api/"; // AVD(Genymotion : 10.0.3.2)

    public static String GET = "GET";
    public static String POST = "POST";
    public static final String URI_CAU_HOI = "cau-hoi";
    public static final String URI_LINH_VUC = "linh-vuc";
    public static final String BASE_IMAGE = "http://10.0.2.2:8000/public/upload/images/";



    public static String doRequest(String uri, String method, HashMap<String, String> params, String token) {
        HttpURLConnection urlConnection = null;
        String jsonString = null;
        Uri.Builder builder = Uri.parse(BASE_URL + uri).buildUpon();

        if(params != null) {
            for (Map.Entry<String, String> pa : params.entrySet()) {
                builder.appendQueryParameter(pa.getKey(), pa.getValue());
            }
        }
        Uri builtURI = builder.build();

        try {

            URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod(method);

            if (token != null) {
                // Dua token vao trong Header cua Request voi key Authorization
                urlConnection.setRequestProperty("Authorization", token);
            }

            urlConnection.connect();

            // Get the InputStream.
            InputStream inputStream = urlConnection.getInputStream();
            jsonString = convertToString(inputStream);
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        Log.d(LOG_TAG, jsonString);
        return jsonString;
    }
    //fuction check connect
    public static boolean checkConnect(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= null;
        if (manager != null){
            networkInfo = manager.getActiveNetworkInfo();
        }
        return (networkInfo!= null && networkInfo.isConnected());
    }

    public static String getJSONData(String uri, String method) {
        HttpURLConnection urlConnection = null;
        String jsonString = null;
        Uri builtURI = Uri.parse(BASE_URL + uri).buildUpon().build();

        try {

            URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod(method);
            urlConnection.connect();

            // Get the InputStream.
            InputStream inputStream = urlConnection.getInputStream();
            jsonString = convertToString(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        Log.d("TEST", jsonString);
        return jsonString;
    }

    public static String getJSONData(String uri, String method, Map<String, String> paramets) {
        HttpURLConnection urlConnection = null;
        String jsonString = null;
        Uri.Builder builder =  Uri.parse(BASE_URL + uri).buildUpon();
        if (paramets != null) {
            for (String key: paramets.keySet() ) {
                builder.appendQueryParameter(key, paramets.get(key));
            }
        }
        Uri builtURI = builder.build();

        try {

            URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod(method);
            urlConnection.connect();

            // Get the InputStream.
            InputStream inputStream = urlConnection.getInputStream();
            jsonString = convertToString(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        Log.d(LOG_TAG, jsonString);
        return jsonString;
    }

    static String convertToString(InputStream stream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder builder = new StringBuilder();
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (builder.length() == 0) {
            return null;
        }
        return builder.toString();
    }

    public static AlertDialog showDialogNetWork(String message, final Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Thông Báo").setMessage(message).setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
                dialog.dismiss();
            }
        });
        return builder.create();
    }
    public static ProgressDialog showProress(Context context){
        final ProgressDialog pgWait = new ProgressDialog(context);
        pgWait.setTitle(context.getString(R.string.tb_xin_cho));
        pgWait.setMessage(context.getString(R.string.tb_doi));
        return pgWait;
    }
    public static Toast showToast(String tb, Context context){
        return Toast.makeText(context,tb,Toast.LENGTH_SHORT);
    }
}
