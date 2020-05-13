package com.example.ledgersystem;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.amulyakhare.textdrawable.TextDrawable;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class group_chat extends Fragment {
    private static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    private static final String TAG = "TAG";
    private ListView mMessageListView;
    private MessageAdapter mMessageAdapter;
    private EditText mMessageEditText;
    private Button mSendButton;
    private FirebaseDatabase mdatabase ;
    private DatabaseReference mdatabasereferences;
    private String mUsername;
    private ChildEventListener childEventListener;
    private FirebaseAuth mfirebaseauth;
    private FirebaseUser user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group_chat, container, false);

        SharedPreferences sf = getActivity().getSharedPreferences("Login data", Context.MODE_PRIVATE);
        String s = sf.getString("user","unable to fetch");
        ImageButton ib =rootView.findViewById(R.id.photoPickerButton);
        TextDrawable td=TextDrawable.builder().buildRect(s.substring(0,1).toUpperCase(), Color.rgb(10,123,14));
        ib.setBackground(td);


        mdatabase = FirebaseDatabase.getInstance();
        mdatabasereferences = mdatabase.getReference().child("messages");

        mfirebaseauth = FirebaseAuth.getInstance();

        // Initialize references to views

        mMessageListView = (ListView) rootView.findViewById(R.id.messageListView);
        mMessageEditText = (EditText) rootView.findViewById(R.id.messageEditText);
        mSendButton = (Button) rootView.findViewById(R.id.sendButton);



        // Initialize message ListView and its adapter
        List<FriendlyMessage> friendlyMessages = new ArrayList<>();

        mMessageAdapter = new MessageAdapter(getContext(), R.layout.group_chat_child, friendlyMessages);
        mMessageListView.setAdapter(mMessageAdapter);

        attachReadListener();

        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mSendButton.setEnabled(true);
                } else {
                    mSendButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});


        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sf = getActivity().getSharedPreferences("Login data", Context.MODE_PRIVATE);
                String s = sf.getString("user","unable to fetch");
                mUsername = s;
                FriendlyMessage friendlyMessage = new FriendlyMessage(mMessageEditText.getText().toString(),mUsername,null);
                mdatabasereferences.push().setValue(friendlyMessage);
                // Clear input box
                mMessageEditText.setText("");
            }
        });

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: deatching listerner!!");
        detachReadListener();
        mMessageAdapter.clear();
    }
    public void onResume(){
        super.onResume();
        Log.e(TAG, "onPause: attaching listener!!");
        attachReadListener();
    }

    void attachReadListener(){
        Log.e(TAG, "onPause: attached listerner!!");
        if(childEventListener == null) {
            Log.e(TAG, "onPause: andar wala  listerner!!");
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Log.e(TAG, " maine to daal diya listerner!!");
                    FriendlyMessage friendlyMessage = dataSnapshot.getValue(FriendlyMessage.class);
                    mMessageAdapter.add(friendlyMessage);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            };

            mdatabasereferences.addChildEventListener(childEventListener);
        }
    }
    void detachReadListener(){
        if(childEventListener != null){
            mdatabasereferences.removeEventListener(childEventListener);
            childEventListener = null;
        }
    }
}
