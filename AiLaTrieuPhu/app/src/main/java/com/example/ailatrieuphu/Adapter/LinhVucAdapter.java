package com.example.ailatrieuphu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ailatrieuphu.Activity.GiaoDienChinh;
import com.example.ailatrieuphu.Activity.HienThiCauHoiActivity;
import com.example.ailatrieuphu.Activity.MainActivity;
import com.example.ailatrieuphu.Class.LinhVuc;
import com.example.ailatrieuphu.Class.NguoiChoi;
import com.example.ailatrieuphu.R;

import java.io.Serializable;
import java.util.List;

import static com.example.ailatrieuphu.Activity.GiaoDienChinh.KEY_REQUESTCODE;
import static com.example.ailatrieuphu.Other.GlobalVariables.KEY_DANGNHAP;
import static com.example.ailatrieuphu.Other.GlobalVariables.KEY_LINHVUC;
import static com.example.ailatrieuphu.Other.GlobalVariables.TYPE_ITEM;
import static com.example.ailatrieuphu.Other.GlobalVariables.TYPE_LOADING;

public class LinhVucAdapter extends RecyclerView.Adapter<LinhVucAdapter.LinhVucHolder> {
    private Context context;
    private List<LinhVuc> linhVucList;

    public LinhVucAdapter(Context context, List<LinhVuc> linhVucList) {
        this.context = context;
        this.linhVucList = linhVucList;
    }

    @NonNull
    @Override
    public LinhVucAdapter.LinhVucHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.custom_button_linhvuc,parent,false);
        return new LinhVucHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LinhVucAdapter.LinhVucHolder holder, int position) {
        LinhVuc linhVuc=linhVucList.get(position);
        holder.btnLinhVuc.setText(linhVuc.getTenLinhVuc());
    }

    @Override
    public int getItemCount() {
        return linhVucList.size();
    }
    public LinhVuc getItem(int position){
        return  linhVucList.get(position);
    }
    public class LinhVucHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {
        private Button btnLinhVuc;
        private RecyclerView rcvLinhVucs;
        public LinhVucHolder(@NonNull View itemView) {
            super(itemView);
            btnLinhVuc=itemView.findViewById(R.id.btnItemLinhVuc);
            rcvLinhVucs=itemView.findViewById(R.id.rcvLinhVuc);
            btnLinhVuc.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            LinhVuc linhVuc=linhVucList.get(getLayoutPosition());
            MainActivity mainActivity=(MainActivity) context;
            Intent intent=new Intent(context,HienThiCauHoiActivity.class);
            intent.putExtra("linh_vuc_id",linhVuc.getId());
            mainActivity.startActivity(intent);
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }

//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        if (viewType == TYPE_ITEM){
//            View view = LayoutInflater.from(context).inflate(R.layout.custom_button_linhvuc,parent,false);
//            return new LinhVucHolder(view);
//        }else if(viewType == TYPE_LOADING){
//            View view = LayoutInflater.from(context).inflate(R.layout.custom_button_linhvuc,parent,false);
//            return new LoadingHolder(view);
//        }
//        return  null;
//    }

//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        LinhVuc linhVuc=getItem(position);
//        if(holder instanceof LinhVucHolder){
//            LinhVucHolder linhVucHolder = (LinhVucHolder) holder;
//            linhVucHolder.btnItemLinhVuc.setText(linhVuc.getTenLinhVuc());
//        }
//    }


}
