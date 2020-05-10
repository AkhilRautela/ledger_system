package com.example.ledgersystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class multicontactselector_adapter extends RecyclerView.Adapter<multicontactselector_adapter.ViewHolder> {

    List<listforadapter> data;
    Context app;
    multicontactselector_adapter(Context app,List<listforadapter> data){
        this.data=data;
        this.app=app;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView phoneno;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.textView5);
            phoneno=itemView.findViewById(R.id.textView6);
        }
    }
    @NonNull
    @Override
    public multicontactselector_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(app).inflate(R.layout.onerow,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull multicontactselector_adapter.ViewHolder holder, int position) {
        listforadapter ekobject=data.get(position);
        holder.name.setText(ekobject.getName());
        holder.phoneno.setText(ekobject.getPhone());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
