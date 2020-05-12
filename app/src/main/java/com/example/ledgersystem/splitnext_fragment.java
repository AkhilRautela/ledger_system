package com.example.ledgersystem;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;

public class splitnext_fragment extends Fragment {
    List<money_split_data> l=new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        for(int j=0;j<selectedsplitdata.i;j++){
            l.add(new money_split_data(selectedsplitdata.names[j],"0"));
        }
        View d= inflater.inflate(R.layout.frag_nextsplit, container, false);
        RecyclerView spli = d.findViewById(R.id.recyclerView5);
        spli.setAdapter(new splitter_adapter(getActivity().getApplicationContext(),l));
        LinearLayoutManager llm=new LinearLayoutManager(getActivity().getApplicationContext());
        spli.setLayoutManager(llm);
        Button b=d.findViewById(R.id.button5);
        final EditText remark=d.findViewById(R.id.editText5);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sf=getActivity().getSharedPreferences("Login data",MODE_PRIVATE);
                String s=sf.getString("user","unable to fetch");
                DatabaseReference dr=FirebaseDatabase.getInstance().getReference("Users");
                for(money_split_data d:l) {
                    dr.child(s).child("Transactions").child("take money").child(d.getName()).child(remark.getText().toString()).setValue(d.getMoney());
                    dr.child(d.getName()).child("Transactions").child("give money").child(s).child(remark.getText().toString()).setValue(d.getMoney());
                }
                Toast.makeText(getActivity().getApplicationContext(),"Split Done",Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_hu_container,new home_fragment()).commit();
                getActivity().getSupportFragmentManager().beginTransaction().remove(new perpersoninfo_fragment());
                getActivity().getSupportFragmentManager().popBackStack();

            }
        });
        return d;
    }


}
