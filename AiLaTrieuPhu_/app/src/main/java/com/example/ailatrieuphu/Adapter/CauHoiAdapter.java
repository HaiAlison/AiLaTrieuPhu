package com.example.ailatrieuphu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ailatrieuphu.Class.CauHoi;
import com.example.ailatrieuphu.R;


import java.util.List;


public class CauHoiAdapter extends RecyclerView.Adapter<CauHoiAdapter.CauHoiHolder> {
    private List<CauHoi> cauHoiList;
    private Context context;

    public CauHoiAdapter(Context context,List<CauHoi> cauHoiList) {
        this.context = context;
        this.cauHoiList = cauHoiList;
    }

    @NonNull
    @Override
    public CauHoiAdapter.CauHoiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.custom_button_linhvuc,parent,false);
        return new CauHoiHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CauHoiAdapter.CauHoiHolder holder, int position) {
        CauHoi cauHoi=cauHoiList.get(position);
        holder.viewCauHoi.setText(cauHoi.getNoiDung());
        holder.btnPhuongAnA.setText(cauHoi.getDaA());
        holder.btnPhuongAnB.setText(cauHoi.getDaB());
        holder.btnPhuongAnC.setText(cauHoi.getDaC());
        holder.btnPhuongAnD.setText(cauHoi.getDaC());
    }

    @Override
    public int getItemCount() {
        return cauHoiList.size();
    }

    public class CauHoiHolder extends RecyclerView.ViewHolder {
        private Button btnPhuongAnA,btnPhuongAnB,btnPhuongAnC,btnPhuongAnD;
        private TextView viewCauHoi;
        private RecyclerView recyclerView;
        public CauHoiHolder(View v) {
            super(v);
            viewCauHoi=v.findViewById(R.id.vQuestion);
//            recyclerView=v.findViewById(R.id.rcvQuest);
            btnPhuongAnA=v.findViewById(R.id.btn_DapAn_A);
            btnPhuongAnB=v.findViewById(R.id.btn_DapAn_B);
            btnPhuongAnC=v.findViewById(R.id.btn_DapAn_C);
            btnPhuongAnD=v.findViewById(R.id.btn_DapAn_D);
        }
    }
}
