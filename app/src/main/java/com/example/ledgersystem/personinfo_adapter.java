package com.example.ledgersystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class personinfo_adapter extends RecyclerView.Adapter<personinfo_adapter.ViewHolder> {
    List<datapersoninfo> ll;
    Context app;
    personinfo_adapter(Context app,List<datapersoninfo> ll){
        this.ll=ll;
        this.app=app;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView money;
        TextView giveortake;
        TextView remarks;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            money=itemView.findViewById(R.id.textView16);
            giveortake=itemView.findViewById(R.id.textView13);
            remarks=itemView.findViewById(R.id.textView11);
        }
    }
    @NonNull
    @Override
    public personinfo_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(app).inflate(R.layout.onepersonview,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull personinfo_adapter.ViewHolder holder, int position) {
        datapersoninfo dpf=ll.get(position);
        holder.money.setText(dpf.getMoney());
        holder.remarks.setText(dpf.getRemark());
        holder.giveortake.setText(dpf.getGiveottake());
    }

    @Override
    public int getItemCount() {
        return ll.size();
    }
}
