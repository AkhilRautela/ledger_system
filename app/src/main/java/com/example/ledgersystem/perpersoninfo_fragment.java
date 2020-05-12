package com.example.ledgersystem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class perpersoninfo_fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.frag_personinfo,container,false);
        RecyclerView showtrans=v.findViewById(R.id.alltransactions);
        showtrans.setLayoutManager(new LinearLayoutManager(getContext()));
        showtrans.setAdapter(new personinfo_adapter(getContext(),inbetweendata.ll));
        TextView na=v.findViewById(R.id.textView10);
        na.setText(inbetweendata.name.toUpperCase());
        if(inbetweendata.ll.size()==0){
            TextView tranc=v.findViewById(R.id.textView9);
            tranc.setText("NO RECORDS FOUND");
        }
        inbetweendata.name=" ";
        inbetweendata.ll=new ArrayList<>();
        return v;
    }
}
