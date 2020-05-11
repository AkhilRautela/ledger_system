package com.example.ledgersystem;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;


public class notification_fragment extends Fragment {
    List<notification_data> ll =new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v= inflater.inflate(R.layout.frag_notification, container, false);
        final RecyclerView rv=v.findViewById(R.id.recyview);
        DatabaseReference db=FirebaseDatabase.getInstance().getReference("Users");
        SharedPreferences sf=getActivity().getSharedPreferences("Login data",MODE_PRIVATE);
        String s=sf.getString("user","unable to fetch");
        db.child(s).child("Transactions").child("give money").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    for (DataSnapshot snap : dataSnapshot.getChildren()) {
                            String remark=snap.getKey();
                            for(DataSnapshot inside:snap.getChildren()){
                                String name=inside.getKey();
                                String money=inside.getValue().toString();
                                ll.add(new notification_data(money,name,remark));
                            }
                    }
                }
                System.out.println(ll.size());
                if(ll.size()==0){
                    TextView t=v.findViewById(R.id.textView5);
                    t.setText("No Notification");
                }
                rv.setAdapter(new notification_adapter(getActivity().getApplicationContext(),ll));
                rv.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return v;
    }
}