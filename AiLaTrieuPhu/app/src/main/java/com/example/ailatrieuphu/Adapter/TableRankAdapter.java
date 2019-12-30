package com.example.ailatrieuphu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ailatrieuphu.Class.NguoiChoi;
import com.example.ailatrieuphu.R;

import java.util.List;

public class TableRankAdapter extends RecyclerView.Adapter<TableRankAdapter.TableRankedHolder> {
    private Context context;
    private List<NguoiChoi> nguoiChoiList;

    public TableRankAdapter(Context context, List<NguoiChoi> nguoiChoiList) {
        this.context = context;
        this.nguoiChoiList = nguoiChoiList;
    }
    @NonNull
    @Override
    public TableRankAdapter.TableRankedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.custem_table_rank,parent,false);
        return new TableRankedHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TableRankAdapter.TableRankedHolder holder, int position) {
        NguoiChoi nguoiChoi=nguoiChoiList.get(position);
        holder.text_name.setText(nguoiChoi.getTenTaiKhoan());
        holder.text_diem.setText(nguoiChoi.getDiemCaoNhat()+"");
    }

    @Override
    public int getItemCount() {
        return nguoiChoiList.size();
    }

    public class TableRankedHolder extends RecyclerView.ViewHolder {
        private TextView text_name,text_diem;
        private RecyclerView rcvTableScore;
        public TableRankedHolder(@NonNull View itemView) {
            super(itemView);
            text_diem=itemView.findViewById(R.id.tv_name_member);
            text_name=itemView.findViewById(R.id.tv_score);
            rcvTableScore=itemView.findViewById(R.id.rcv_table_ranked_member);
        }
    }
}
