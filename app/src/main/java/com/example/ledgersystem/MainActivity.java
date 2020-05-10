package com.example.ledgersystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Handler h1;
    ImageView aditya,dishant,mishra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getcontacts g=new getcontacts();
        g.getphonenumbers(getApplicationContext());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aditya=findViewById(R.id.aditya);
        mishra=findViewById(R.id.mishra);
        dishant=findViewById(R.id.dishant);
        Animation di= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.ledger);
        dishant.setAnimation(di);
        Animation hu=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.dish);
        mishra.setAnimation(hu);
        Animation lm= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.mis);
        aditya.setAnimation(lm);
        h1=new Handler();
        h1.postDelayed(new Runnable() {
            @Override
            public void run() {
                try{
                    SharedPreferences sp = getSharedPreferences("Login data",MODE_PRIVATE);
                    if(sp.getInt("islogged",0)==1){
                        Intent i =new Intent(getApplicationContext(),home.class);
                        startActivity(i);
                        finish();
                    }
                    else{
                        Intent i =new Intent(getApplicationContext(),login.class);
                        startActivity(i);
                        finish();
                    }
            }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },  2000);

    }

}