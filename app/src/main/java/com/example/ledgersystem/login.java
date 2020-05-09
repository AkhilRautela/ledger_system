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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    EditText name,pass;
    String email;
    FirebaseAuth fauth;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name=findViewById(R.id.editText);
        pass=findViewById(R.id.editText2);
        fauth=FirebaseAuth.getInstance();
    }
    public void register(View v){
        AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
        v.startAnimation(buttonClick);
        Vibrator vv = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        vv.vibrate(100);
        Intent i = new Intent(getApplicationContext(),register.class);
        startActivity(i);
    }
    public void home(View v) {
        AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
        v.startAnimation(buttonClick);
        Vibrator vv = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        vv.vibrate(100);
        if(name.getText().toString().equals("")||pass.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(),"Null Credentials",Toast.LENGTH_SHORT).show();
        }
        else {
            DatabaseReference myRef = database.getReference("Users");
            myRef.child(name.getText().toString()).child("E_mail").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    email=dataSnapshot.getValue().toString();
                    fauth.signInWithEmailAndPassword(email,pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                SharedPreferences sf= getSharedPreferences("Login data",MODE_PRIVATE);
                                SharedPreferences.Editor edit= sf.edit();
                                edit.putInt("islogged",1);
                                edit.putString("user",name.getText().toString());
                                edit.commit();
                                Intent i= new Intent(getApplicationContext(),home.class);
                                startActivity(i);
                                finish();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"INCORRECT CREDENTIALS",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(),"INCORRECT CREDENTIALS",Toast.LENGTH_SHORT);
                }
            });

        }





    }
}

