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

public class getcontacts extends Thread {
    String pnumber[] = new String[10000];
    int i = 0;
    int j = 0;
    String name;
    Context s;
    final static Map<String, String> dcontacts = new HashMap<String, String>();
    getcontacts(Context s){
        this.s=s;
    }
    public void run() {
        Cursor phones = s.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phones.moveToNext()) {
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            phoneNumber=phoneNumber.replaceAll("\\D", "");
            if (phoneNumber.length()<9){
                continue;
            }
            if (phoneNumber.substring(0,2).equals("91"))
                pnumber[i] =phoneNumber.substring(2);
            else{
                pnumber[i]=phoneNumber;
            }
            i++;
        }
        System.out.println("Total Contacts "+i);
        DatabaseReference df = FirebaseDatabase.getInstance().getReference("Phonenumbers");
            df.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for ( j = 0; j < i; j++) {
                        System.out.println(pnumber[j]);
                        if (dataSnapshot.child(pnumber[j]).getValue() != null) {
                            dcontacts.put(dataSnapshot.child(pnumber[j]).getValue().toString(),pnumber[j]);
                            System.out.println("fetched "+pnumber[j]+" "+dataSnapshot.child(pnumber[j]).getValue().toString());
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



    }
}