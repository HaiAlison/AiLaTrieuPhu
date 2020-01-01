package com.example.ailatrieuphu.Adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.ailatrieuphu.Class.CauHoi;
import com.example.ailatrieuphu.Class.NguoiChoi;
import com.example.ailatrieuphu.Fragment.HienThiCauHoiFragment;

import java.util.List;

import static com.example.ailatrieuphu.Other.GlobalVariables.KEY_CH_POSITION;

public class CauHoiAdapter extends FragmentStatePagerAdapter {
    private List<CauHoi> cauHoiList;
    private Context context;

    public CauHoiAdapter(FragmentManager fm, List<CauHoi> cauHoiList, Context context, NguoiChoi nguoiChoi) {
        super(fm);
        this.cauHoiList = cauHoiList;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        HienThiCauHoiFragment hienThiCauHoiFragment = new HienThiCauHoiFragment(cauHoiList,context,this);
        Bundle saveStatus = new Bundle();
        saveStatus.putInt(KEY_CH_POSITION,position);
        hienThiCauHoiFragment.setArguments(saveStatus);
        return hienThiCauHoiFragment;
    }

    public int getItemPosition(Object object){return POSITION_NONE;}
    @Override
    public int getCount() {
        return cauHoiList.size();
    }
}
