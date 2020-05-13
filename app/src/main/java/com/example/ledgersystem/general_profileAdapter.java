package com.example.ledgersystem;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

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
    public void onBindViewHolder(@NonNull general_profileAdapter.myHolder holder, final int position) {
        holder.t1.setText(names.get(position));
        TextDrawable drawable = TextDrawable.builder().buildRound(names.get(position).substring(0, 1).toUpperCase(), Color.rgb(15, 167, 255));
        holder.cimg.setBackground(drawable);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
                v.startAnimation(buttonClick);
                Vibrator vv = (Vibrator)ctx.getSystemService(Context.VIBRATOR_SERVICE);
                vv.vibrate(100);
                inbetweendata.name=names.get(position);
                FragmentManager manager = ((home)ctx).getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.main_hu_container,new perpersoninfo_fragment()).commit();
                manager.beginTransaction().addToBackStack("personinfo").commit();
            }
        });

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
