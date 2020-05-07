package com.example.ledgersystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText email,pass;
    EditText phone,username;
    FirebaseDatabase fb= FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        email=findViewById(R.id.editText);
        pass=findViewById(R.id.editText2);
        phone=findViewById(R.id.phoneno);
        username=findViewById(R.id.username);
    }
    public void login(View v){
        AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
        v.startAnimation(buttonClick);
        Vibrator vv = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        vv.vibrate(100);
        mAuth.createUserWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   Toast.makeText(getApplicationContext(),"Successfully Registered",Toast.LENGTH_SHORT).show();
                   String na=username.getText().toString();
                   DatabaseReference dref=fb.getReference("Users");
                   dref.child(na).child("Phone_number").setValue(phone.getText().toString());
                   dref.child(na).child("E_mail").setValue(email.getText().toString());
                   DatabaseReference dd=fb.getReference("Phonenumbers");
                   dd.child(phone.getText().toString()).setValue(username.getText().toString());
                   Intent i = new Intent(getApplicationContext(),login.class);
                   startActivity(i);
                   finishAffinity();
               }
               else{
                   Toast.makeText(getApplicationContext(),"ERROR IN CREATING ACCOUNT",Toast.LENGTH_SHORT).show();
               }
            }
        });

    }
}
