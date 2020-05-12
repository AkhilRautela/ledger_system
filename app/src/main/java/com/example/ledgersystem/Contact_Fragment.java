package com.example.ledgersystem;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


public class Contact_Fragment extends Fragment {
    private static final String TAG = "TAG";
    private RecyclerView contact_list;
    private Button searchButton;
    private EditText searchBarText;
    Dialog requestDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(TAG, "onCreate: of ContactFragment ");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_contact_, container, false);
        searchButton = (Button)rootView.findViewById(R.id.cont_search);
        contact_list = (RecyclerView)rootView.findViewById(R.id.contact_list);


        final Map<String, String> data = getcontacts.dcontacts;
        List<listforadapter> list_hai = new ArrayList<>();
        for (String s : data.keySet()) {
            list_hai.add(new listforadapter(s, data.get(s)));
            System.out.println(s + data.get(s));
        }

        //SET LAYOUT MANAGER
        contact_list.setLayoutManager(new LinearLayoutManager(getContext()));
        // SET ADAPTER
        contact_list.setAdapter(new contact_Adapter(getContext(),list_hai));


        final AutoCompleteTextView act=rootView.findViewById(R.id.yoyo_search);
        String dat[]=new String[getcontacts.dcontacts.size()];
        int count=0;
        for(String x:getcontacts.dcontacts.keySet()){
            dat[count]=x;
            count++;
            System.out.println(count+" "+x);
        }
        act.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,dat));
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(final String s:getcontacts.dcontacts.keySet()){
                    if (s.equals(act.getText().toString())){
                        requestDialog=new Dialog(getActivity());
                        requestDialog.setContentView(R.layout.dialog_req);
                        final TextView c_name = (TextView)requestDialog.findViewById(R.id.dialog_name_d);
                        final TextView c_no = requestDialog.findViewById(R.id.dialog_number_d);
                        final CircleImageView c_img =  requestDialog.findViewById(R.id.dialog_img_d);
                        Button b=requestDialog.findViewById(R.id.requestt);
                        final EditText money=requestDialog.findViewById(R.id.amt);
                        final EditText remark=requestDialog.findViewById(R.id.remark);
                        final String sst=s;
                        b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DatabaseReference dr= FirebaseDatabase.getInstance().getReference("Users");
                                SharedPreferences sf=getActivity().getSharedPreferences("Login data",MODE_PRIVATE);
                                String ss=sf.getString("user","unable to fetch");
                                dr.child(ss).child("Transactions").child("take money").child(sst).child(remark.getText().toString()).setValue(money.getText().toString());
                                dr.child(sst).child("Transactions").child("give money").child(ss).child(remark.getText().toString()).setValue(money.getText().toString());
                                requestDialog.dismiss();
                                Toast.makeText(getContext(),"REQUEST SUCCESSFUL",Toast.LENGTH_SHORT).show();
                                getActivity().getSupportFragmentManager().popBackStack();
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_hu_container,new home_fragment()).commit();

                            }
                        });
                        c_name.setText(sst);
                        c_no.setText(data.get(sst));
                        TextDrawable drawable = TextDrawable.builder().buildRound(sst.substring(0,1).toUpperCase(), Color.GREEN);
                        c_img.setBackground(drawable);
                        requestDialog.show();

                    }
                    else{
                        Toast.makeText(getActivity().getApplicationContext(),"Coantact Not Found",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
     return rootView;
    }


}
