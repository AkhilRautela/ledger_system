package com.example.ledgersystem;

import android.content.Context;
import android.graphics.Color;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

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
        final listforadapter ekobject=data.get(position);
        holder.name.setText(ekobject.getName());
        holder.phoneno.setText(ekobject.getPhone());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
                v.startAnimation(buttonClick);
                Vibrator vv = (Vibrator) app.getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                vv.vibrate(100);
                if(ekobject.isIsselected()){
                    ekobject.setIsselected(false);
                }
                else{
                    ekobject.setIsselected(true);
                }
                if(ekobject.isIsselected()){
                    v.setBackgroundColor(Color.RED);
                    Toast.makeText(app.getApplicationContext(),ekobject.getName()+" Selected",Toast.LENGTH_SHORT).show();
                }
                else{
                    v.setBackgroundColor(Color.BLUE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
