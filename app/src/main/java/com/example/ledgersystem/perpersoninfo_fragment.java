package com.example.ledgersystem;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;
import static java.lang.Math.abs;

public class perpersoninfo_fragment extends Fragment {

    long count = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.frag_personinfo,container,false);
        inbetweendata.ll=new ArrayList<>();
        final TextView total=v.findViewById(R.id.textView14);
        final TextView tranc=v.findViewById(R.id.textView9);
        final TextView na=v.findViewById(R.id.textView10);
        final RecyclerView showtrans=v.findViewById(R.id.alltransactions);
        final String frname=inbetweendata.name;
        inbetweendata.name=frname;
        SharedPreferences sf=getActivity().getSharedPreferences("Login data",MODE_PRIVATE);
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
                            count=count-Long.parseLong(money);
                            inbetweendata.ll.add(new datapersoninfo(sss,money,name,"give money"));
                        }
                    }
                }
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
                                    count=count+Long.parseLong(money);
                                }
                            }

                        }
                        if(count==0){
                          total.setText("NOT IN ANY DEBT");
                        }
                        else if (count>0){
                            total.setText("You have to take "+abs(count));
                        }
                        else{
                            total.setText("You have to give"+abs(count));
                        }
                        showtrans.setLayoutManager(new LinearLayoutManager(getContext()));
                        personinfo_adapter pia=new personinfo_adapter(getContext(),inbetweendata.ll);
                        showtrans.setAdapter(pia);

                        if(inbetweendata.ll.size()==0){
                            tranc.setText("NO RECORDS FOUND");
                            na.setText(" ");
                        }
                        else{
                            na.setText(inbetweendata.name.toUpperCase());
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        return v;
    }
}
