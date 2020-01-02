package com.example.ailatrieuphu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ailatrieuphu.Activity.HienThiCauHoiActivity;
import com.example.ailatrieuphu.Activity.MainActivity;
import com.example.ailatrieuphu.Class.LinhVuc;
import com.example.ailatrieuphu.R;

import java.util.List;


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
        holder.id_linhvuc=linhVuc.getId();
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
        private int id_linhvuc;
        private RecyclerView rcvLinhVucs;
        public LinhVucHolder(@NonNull View itemView) {
            super(itemView);
            btnLinhVuc=itemView.findViewById(R.id.btnPhuongAn);
            rcvLinhVucs=itemView.findViewById(R.id.rcvLinhVuc);
            btnLinhVuc.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            MainActivity mainActivity=(MainActivity) context;
            Intent intent=new Intent(context,HienThiCauHoiActivity.class);
            intent.putExtra("linh_vuc_id", id_linhvuc);
            mainActivity.startActivity(intent);
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }



}
