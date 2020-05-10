package com.example.ledgersystem;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.Settings;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

public class getcontacts {
    String pnumber[] = new String[10000];
    int i = 0;
    int j;
    String name;
    final static Map<String, String> dcontacts = new HashMap<String, String>();
    public void getphonenumbers(Context s) {

        Cursor phones = s.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phones.moveToNext()) {
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            pnumber[i] = phoneNumber.replaceAll("\\D", "");
            i++;
        }

        DatabaseReference df = FirebaseDatabase.getInstance().getReference("Phonenumbers");

        for ( j = 0; j < i; j++) {
            System.out.println(pnumber[j]);
            df.child(pnumber[j]).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue()!=null){
                        name=dataSnapshot.getValue().toString();
                        System.out.println(name);
                        dcontacts.put(name,pnumber[j]);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }


    }
}