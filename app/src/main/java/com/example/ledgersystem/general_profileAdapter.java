package com.example.ledgersystem;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.List;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class general_profileAdapter extends RecyclerView.Adapter<general_profileAdapter.myHolder> {

    List<String> names;
    //private int[] profile_res;
    Context ctx;
    public general_profileAdapter(Context context, List<String> names) {
        ctx = context;
        this.names = names ;


    }

    @NonNull
    @Override
    public general_profileAdapter.myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myInflater = LayoutInflater.from(ctx);
        View myView = myInflater.inflate(R.layout.general_profile_ui,parent,false);

        return new myHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull general_profileAdapter.myHolder holder, int position) {
        holder.t1.setText(names.get(position));
        TextDrawable drawable =TextDrawable.builder().buildRound(names.get(position).substring(0,1).toUpperCase(), Color.rgb(15,167,255));
        holder.cimg.setBackground(drawable);

    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class myHolder extends RecyclerView.ViewHolder {
        private TextView t1;
        private CircleImageView cimg;
        public myHolder(@NonNull View itemView) {
            super(itemView);
        t1 = itemView.findViewById(R.id.prof_name);
        cimg = itemView.findViewById(R.id.prof_photo);

        }
    }
}
