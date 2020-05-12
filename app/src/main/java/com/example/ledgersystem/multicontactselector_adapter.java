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

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.List;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

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
        CircleImageView civ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.contact_name_d);
            phoneno=itemView.findViewById(R.id.contact_number_d);
            civ=itemView.findViewById(R.id.contact_img_d);
        }
    }
    @NonNull
    @Override
    public multicontactselector_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(app).inflate(R.layout.contact_child,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull multicontactselector_adapter.ViewHolder holder, int position) {
        final listforadapter ekobject=data.get(position);
        holder.name.setText(ekobject.getName());
        holder.phoneno.setText(ekobject.getPhone());
        TextDrawable drawable = TextDrawable.builder().buildRound(ekobject.getName().substring(0,1).toUpperCase(),Color.GREEN);
        holder.civ.setBackground(drawable);
        if(ekobject.isIsselected()){
            holder.itemView.setBackgroundColor(Color.rgb(84,242,242));
            holder.itemView.setAlpha((float) 0.8);
            Toast.makeText(app.getApplicationContext(),ekobject.getName()+" Selected",Toast.LENGTH_SHORT).show();
        }
        else{
            holder.itemView.setAlpha((float) 1.0);
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
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
                    v.setBackgroundColor(Color.rgb(84,242,242));
                    v.setAlpha((float) 0.8);
                    Toast.makeText(app.getApplicationContext(),ekobject.getName()+" Selected",Toast.LENGTH_SHORT).show();
                }
                else{
                    v.setAlpha((float) 1.0);
                    v.setBackgroundColor(Color.WHITE);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
