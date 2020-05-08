package com.example.ledgersystem;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class home_fragment extends Fragment {
    Button spl,req,grp;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v= inflater.inflate(R.layout.frag_home, container, false);
        LinearLayout l=v.findViewById(R.id.impbtn);
        spl=l.findViewById(R.id.spl);
        req=l.findViewById(R.id.req);
        grp=l.findViewById(R.id.cg);
        spl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
                v.startAnimation(buttonClick);
                Vibrator vv = (Vibrator) getActivity().getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                vv.vibrate(100);
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
                Toast.makeText(getActivity().getApplicationContext(),"Create Group",Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

}
