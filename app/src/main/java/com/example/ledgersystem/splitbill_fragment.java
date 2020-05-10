package com.example.ledgersystem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        return  split;
    }
}
