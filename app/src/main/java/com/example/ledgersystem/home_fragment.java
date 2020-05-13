package com.example.ledgersystem;

import androidx.constraintlayout.solver.widgets.ConstraintHorizontalLayout;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class home_fragment extends Fragment {
    List<String> gnames=new ArrayList<>();
    private int[] fake;
    private static final String TAG = "TAG";

    private Button spl,req,grp;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        final View v= inflater.inflate(R.layout.frag_home, container, false);

        LinearLayout l=v.findViewById(R.id.impbtn);
        spl=l.findViewById(R.id.spl);
        req=l.findViewById(R.id.req);
        grp=l.findViewById(R.id.cg);

        final RecyclerView recycle_groups = v.findViewById(R.id.recycle_group);


        // initializing variables for the group adapter (just dummy)
        // todo : fetching from the database has to be done. currently using dummy data.



        spl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
                v.startAnimation(buttonClick);
                Vibrator vv = (Vibrator) getActivity().getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                vv.vibrate(100);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_hu_container,new splitbill_fragment()).commit();
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("splitbill").commit();
                Toast.makeText(getActivity().getApplicationContext(),"Split Bill",Toast.LENGTH_SHORT).show();

            }
        });
        req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
                v.startAnimation(buttonClick);
                Vibrator vv = (Vibrator) getActivity().getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                vv.vibrate(100);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_hu_container,new Contact_Fragment()).commit();
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("request").commit();
                Toast.makeText(getActivity().getApplicationContext(),"Request",Toast.LENGTH_SHORT).show();
            }
        });
        grp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
                v.startAnimation(buttonClick);
                Vibrator vv = (Vibrator) getActivity().getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                vv.vibrate(100);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_hu_container,new group_chat()).commit();
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("GroupChat").commit();
                Toast.makeText(getActivity().getApplicationContext(),"Group Chat",Toast.LENGTH_SHORT).show();
            }
        });


        // setting up adapter.........
        Log.e(TAG, "onCreateView: "+getContext());

        DatabaseReference db= FirebaseDatabase.getInstance().getReference("Users");
        SharedPreferences sf=getActivity().getSharedPreferences("Login data",MODE_PRIVATE);
        String s=sf.getString("user","unable to fetch");
        db.child(s).child("Transactions").child("take money").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null){
                    for(DataSnapshot dd:dataSnapshot.getChildren()){
                        String s=dd.getKey();
                        for(DataSnapshot fg:dd.getChildren()){
                            gnames.add(s);
                        }
                    }
                }
                general_profileAdapter general_profileadapter = new general_profileAdapter(getContext(),gnames);
                Context ctx = getContext();
                LinearLayoutManager HorizontalLayout
                        = new LinearLayoutManager(
                        ctx,
                        LinearLayoutManager.HORIZONTAL,
                        false);



               // act.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,dat));
                recycle_groups.setLayoutManager(HorizontalLayout);
                recycle_groups.setAdapter(general_profileadapter);
                final Map<String, String> data = getcontacts.dcontacts;
                List<listforadapter> list_hai = new ArrayList<>();
                for (String ss : data.keySet()) {
                    list_hai.add(new listforadapter(ss, data.get(ss)));
                    System.out.println(ss+" "+data.get(ss));
                }
                RecyclerView homecontacts=v.findViewById(R.id.conactinhome);
                homecontacts.setLayoutManager(new LinearLayoutManager(getContext()));
                homecontacts.setAdapter(new contactsinhomeadapter(getContext(),list_hai));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return v;
    }


}
