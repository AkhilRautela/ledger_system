package com.example.ledgersystem;

import androidx.lifecycle.ViewModelProviders;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.Context.MODE_PRIVATE;

public class about_fragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View v=inflater.inflate(R.layout.frag_about_user, container, false);
        final TextView name=v.findViewById(R.id.textView);
        final TextView email=v.findViewById(R.id.textView17);
        final TextView phoneno=v.findViewById(R.id.textView4);
        final CircleImageView civ=v.findViewById(R.id.circleImageView);
        SharedPreferences sf=getActivity().getSharedPreferences("Login data",MODE_PRIVATE);
        final String s=sf.getString("user","unable to fetch");
        final String email1=FirebaseAuth.getInstance().getCurrentUser().getEmail();
        DatabaseReference db=FirebaseDatabase.getInstance().getReference("Users");
        System.out.println(s);
        db.child(s).child("Phone_number").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String phone_no1=dataSnapshot.getValue().toString();
                name.setText(s.toUpperCase());
                email.setText(email1);
                phoneno.setText(phone_no1);
                TextDrawable drawable=TextDrawable.builder().buildRound(s.substring(0,1).toUpperCase(), Color.rgb(10,150,50));
                civ.setBackground(drawable);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return v;
    }


}
