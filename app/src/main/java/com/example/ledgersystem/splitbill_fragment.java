package com.example.ledgersystem;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class splitbill_fragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Map<String, String> dat = getcontacts.dcontacts;
        List<listforadapter> li = new ArrayList<>();
        for (String s : dat.keySet()) {
            li.add(new listforadapter(s, dat.get(s)));
            System.out.println(s + dat.get(s));
        }
        View split = inflater.inflate(R.layout.frag_splitbill, container, false);
        RecyclerView cont = split.findViewById(R.id.contactlist);
        cont.setAdapter(new multicontactselector_adapter(getActivity().getBaseContext(), li));
        LinearLayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext());
        cont.setLayoutManager(manager);
        Button b= split.findViewById(R.id.button4);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
                v.startAnimation(buttonClick);
                Vibrator vv = (Vibrator) getActivity().getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                vv.vibrate(100);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_hu_container,new splitnext_fragment()).commit();
            }
        });
        return  split;
    }
}
