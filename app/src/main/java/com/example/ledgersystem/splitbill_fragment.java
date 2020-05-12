package com.example.ledgersystem;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

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
    List<listforadapter> li = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Map<String, String> dat = getcontacts.dcontacts;
        for (String s : dat.keySet()) {
            li.add(new listforadapter(s, dat.get(s)));
            System.out.println(s + dat.get(s));
        }
        View split = inflater.inflate(R.layout.frag_splitbill, container, false);
        final AutoCompleteTextView act=split.findViewById(R.id.select_search);
        String dut[]=new String[getcontacts.dcontacts.size()];
        int count=0;
        for(String x:getcontacts.dcontacts.keySet()){
            dut[count]=x;
            count++;
            System.out.println(count+" "+x);
        }
        act.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,dut));
        Button search=split.findViewById(R.id.search_karo);
        RecyclerView cont = split.findViewById(R.id.contactlist);
        final multicontactselector_adapter sda=new multicontactselector_adapter(getActivity().getBaseContext(), li);
        cont.setAdapter(sda);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext());
        cont.setLayoutManager(manager);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(listforadapter l:li){
                    System.out.println(l.getName()+" "+act.getText().toString());
                    if(l.getName().equals(act.getText().toString())){
                        System.out.println("hello");
                        l.setIsselected(true);
                            Toast.makeText(getActivity().getApplicationContext(),l.getName()+" Selected",Toast.LENGTH_SHORT).show();
                        sda.notifyDataSetChanged();
                        break;
                    }
                }
                Toast.makeText(getActivity().getApplicationContext(),"Name Not in Contact",Toast.LENGTH_SHORT).show();
            }
        });
        Button b= split.findViewById(R.id.button4);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
                v.startAnimation(buttonClick);
                Vibrator vv = (Vibrator) getActivity().getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                vv.vibrate(100);
                selectedsplitdata.i=0;
                selectedsplitdata.names=new String[1000];
                selectedsplitdata s= new selectedsplitdata(li);
                System.out.println("gsss "+getActivity().getSupportFragmentManager().getBackStackEntryCount());
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_hu_container,new splitnext_fragment()).commit();

            }
        });
        return  split;
    }
}
