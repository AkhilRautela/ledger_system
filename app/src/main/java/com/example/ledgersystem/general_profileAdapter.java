package com.example.ledgersystem;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        TextDrawable drawable =TextDrawable.builder().buildRound(names.get(position).substring(0,1).toUpperCase(), Color.rgb(15,167,255));
        holder.cimg.setBackground(drawable);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inbetweendata.ll=new ArrayList<>();
                final String frname=names.get(position);
                inbetweendata.name=frname;
                SharedPreferences sf=ctx.getSharedPreferences("Login data",MODE_PRIVATE);
                final String s=sf.getString("user","unable to fetch");
                final DatabaseReference db= FirebaseDatabase.getInstance().getReference("Users");
                db.child(s).child("Transactions").child("give money").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dp:dataSnapshot.getChildren()){
                            String sss=dp.getKey();
                            for(DataSnapshot sd:dp.getChildren()){
                                String name=sd.getKey();
                                String money=sd.getValue().toString();
                                System.out.println(sss+" "+name+" "+money);
                                if (sss.equals(frname)){
                                    inbetweendata.ll.add(new datapersoninfo(sss,money,name,"give money"));
                                }
                            }
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                db.child(s).child("Transactions").child("take money").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dp:dataSnapshot.getChildren()){
                            String sss=dp.getKey();
                            for(DataSnapshot sd:dp.getChildren()){
                                String name=sd.getKey();
                                String money=sd.getValue().toString();
                                System.out.println(sss+" "+name+" "+money);
                                if (sss.equals(frname)){
                                    inbetweendata.ll.add(new datapersoninfo(sss,money,name,"take money"));

                                }
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                FragmentManager manager = ((home)ctx).getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.main_hu_container,new perpersoninfo_fragment()).commit();
                manager.beginTransaction().addToBackStack("hllo").commit();
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
