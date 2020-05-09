package com.example.ledgersystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class general_profileAdapter extends RecyclerView.Adapter<general_profileAdapter.myHolder> {

    private String[] names;
    //private int[] profile_res;
    Context ctx;
    public general_profileAdapter(Context context, String[] s1) {
        ctx = context;
        names = s1;


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
        holder.t1.setText(names[position]);
        holder.cimg.setImageResource(R.drawable.ic_launcher_background);

    }

    @Override
    public int getItemCount() {
        return names.length;
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
