package com.example.ailatrieuphu.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ailatrieuphu.Adapter.CauHoiAdapter;
import com.example.ailatrieuphu.Class.CauHoi;
import com.example.ailatrieuphu.Class.LinhVuc;
import com.example.ailatrieuphu.Class.NguoiChoi;
import com.example.ailatrieuphu.NetworkUtils;
import com.example.ailatrieuphu.Question.AmNhac_PhimActivity;
import com.example.ailatrieuphu.Question.TheThaoQuest;
import com.example.ailatrieuphu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.ailatrieuphu.Controller.CauHoiController.COLUMN_LINH_VUC_ID;
import static com.example.ailatrieuphu.Controller.LuotChoiController.COLUMN_LUOT_CHOI_DIEM;
import static com.example.ailatrieuphu.Controller.LuotChoiController.COLUMN_LUOT_CHOI_NGAY_GIO;
import static com.example.ailatrieuphu.Controller.LuotChoiController.COLUMN_LUOT_CHOI_SO_CAU;
import static com.example.ailatrieuphu.Controller.LuotChoiController.COLUMN_NGUOI_CHOI_ID;
import static com.example.ailatrieuphu.NetworkUtils.BASE_URL;
import static com.example.ailatrieuphu.NetworkUtils.URI_CAU_HOI;
import static com.example.ailatrieuphu.Other.GlobalVariables.GIA_LUOT_CHOI;
import static com.example.ailatrieuphu.Other.GlobalVariables.KEY_LIMIT;
import static com.example.ailatrieuphu.Other.GlobalVariables.KEY_PAGE;
import static com.example.ailatrieuphu.Other.GlobalVariables.LIMIT_KHOI_TAO;
import static com.example.ailatrieuphu.Other.GlobalVariables.PAGE_KHOI_TAO;
import static com.example.ailatrieuphu.Other.GlobalVariables.PAGE_SIZE;

public class HienThiCauHoiActivity extends AppCompatActivity{
    public ViewPager vpgShowCauHoi;
    public TextView tvTen, tvTinDung,tvDiem;
    private CauHoiAdapter cauHoiAdapter;
    private List<CauHoi> cauHois  = new ArrayList<>();;
    public ImageView[] ivMang = new ImageView[5];
    private NguoiChoi nguoiChoi;
    private LinhVuc linhVuc;
    private Intent intent;
    public int diemSoMang = 0;
    public int tongDiem = 0;
    public boolean[] ischeckedSP = {false, false,false, false};
    public boolean checkCountTimerLoading =false;
    private boolean checkLastPage = false;
    private int currentPage = 1;
    public ArrayList<CountDownTimer> countDownTimer = new ArrayList<>();
    private static final int NUM_PAGES = 5;
    private ViewPager mPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_cau_hoi);
        Bundle inBundle=getIntent().getExtras();
        String id=inBundle.get("linh_vuc_id").toString();
        mPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new HienThiCauHoiActivity.ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);
        mPager.setPageTransformer(true, new HienThiCauHoiActivity.DepthPageTransformer());
    }
    public void onBackPressed() {
    if (mPager.getCurrentItem() == 0) {
        // If the user is currently looking at the first step, allow the system to handle the
        // Back button. This calls finish() on this activity and pops the back stack.
        super.onBackPressed();
    } else {
        // Otherwise, select the previous step.
        mPager.setCurrentItem(mPager.getCurrentItem() - 1);
    }
}

    public static class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public Fragment getItem(int position) {
            return new TheThaoQuest();
        }
    }
    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1f);
                view.setTranslationX(0f);
                view.setScaleX(1f);
                view.setScaleY(1f);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }

}
