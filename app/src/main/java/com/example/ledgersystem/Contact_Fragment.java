package com.example.ledgersystem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Contact_Fragment extends Fragment {
    private static final String TAG = "TAG";
    private RecyclerView contact_list;
    private Button searchButton;
    private EditText searchBarText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(TAG, "onCreate: of ContactFragment ");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact_, container, false);
        searchButton = (Button)rootView.findViewById(R.id.cont_search);
        searchBarText = (EditText)rootView.findViewById(R.id.edit_search);
        contact_list = (RecyclerView)rootView.findViewById(R.id.contact_list);

        Map<String, String> data = getcontacts.dcontacts;
        List<listforadapter> list_hai = new ArrayList<>();
        for (String s : data.keySet()) {
            list_hai.add(new listforadapter(s, data.get(s)));
            System.out.println(s + data.get(s));
        }
        //SET LAYOUT MANAGER
        contact_list.setLayoutManager(new LinearLayoutManager(getContext()));
        // SET ADAPTER
        contact_list.setAdapter(new contact_Adapter(getContext(),list_hai));


     return rootView;
    }


}
