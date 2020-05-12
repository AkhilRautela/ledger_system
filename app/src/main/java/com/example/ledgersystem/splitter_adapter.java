package com.example.ledgersystem;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class splitter_adapter extends RecyclerView.Adapter<splitter_adapter.viewholder> {
    Context app;
    String temp;
    List<money_split_data> l;
    splitter_adapter(Context app,List<money_split_data> l){
        this.app=app;
        this.l=l;
    }
    class viewholder extends RecyclerView.ViewHolder{
        TextView t1;
        EditText t2;
        CircleImageView contactimg;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.textView8);
            t2=itemView.findViewById(R.id.editText4);
            contactimg=itemView.findViewById(R.id.contact_img_d);
        }
    }
    @NonNull
    @Override
    public splitter_adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(app).inflate(R.layout.money_split_row,parent,false);
        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull splitter_adapter.viewholder holder, int position) {
        final money_split_data dat=l.get(position);
        holder.t1.setText(dat.getName());
        TextDrawable drawable = TextDrawable.builder().buildRound(dat.getName().substring(0,1).toUpperCase(), Color.rgb(15,167,255));
        holder.contactimg.setBackground(drawable);
        holder.t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                dat.setMoney(s.toString());
                System.out.println(dat.getMoney());
            }
        });
    }

    @Override
    public int getItemCount() {
        return l.size();
    }
}
