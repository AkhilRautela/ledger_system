package com.example.ledgersystem;

import androidx.constraintlayout.solver.widgets.ConstraintHorizontalLayout;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class home_fragment extends Fragment {
    private String[] gnames = new String[4];
    private int[] fake;
    private static final String TAG = "TAG";

    private Button spl,req,grp;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v= inflater.inflate(R.layout.frag_home, container, false);
        LinearLayout l=v.findViewById(R.id.impbtn);
        spl=l.findViewById(R.id.spl);
        req=l.findViewById(R.id.req);
        grp=l.findViewById(R.id.cg);

        RecyclerView recycle_groups = v.findViewById(R.id.recycle_group);


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
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("hii").commit();
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
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_hu_container,new Contact_Fragment()).addToBackStack(null).commit();
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
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_hu_container,new group_chat()).addToBackStack(null).commit();
                Toast.makeText(getActivity().getApplicationContext(),"Create Group",Toast.LENGTH_SHORT).show();
            }
        });

        // setting up adapter.........
        Log.e(TAG, "onCreateView: "+getContext());

        general_profileAdapter general_profileadapter = new general_profileAdapter(getContext(),gnames);
        Context ctx = getContext();
        LinearLayoutManager HorizontalLayout
                = new LinearLayoutManager(
                ctx,
                LinearLayoutManager.HORIZONTAL,
                false);
        recycle_groups.setLayoutManager(HorizontalLayout);
        recycle_groups.setAdapter(general_profileadapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gnames[0] = "alpha";
        gnames[1] = "beta";
        gnames[2] = "gamma";
        gnames[3] = "theta";





    }
}
