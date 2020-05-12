package com.example.ledgersystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class notification_adapter extends RecyclerView.Adapter<notification_adapter.notiholder> {
    List<notification_data> data;
    Context app;
    notification_adapter(Context app,List<notification_data> data){
        this.app=app;
        this.data=data;
    }
    public class notiholder extends RecyclerView.ViewHolder{
        TextView t1;
        TextView t2;
        public notiholder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.notification);
            t2=itemView.findViewById(R.id.remark);
        }
    }
    @NonNull
    @Override
    public notification_adapter.notiholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new notiholder(LayoutInflater.from(app).inflate(R.layout.onenotification,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull notification_adapter.notiholder holder, int position) {
        notification_data obj = data.get(position);
        holder.t2.setText(obj.getRemark());
        holder.t1.setText(obj.getFrom().toUpperCase()+" REQUESTED "+obj.getMsg());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
