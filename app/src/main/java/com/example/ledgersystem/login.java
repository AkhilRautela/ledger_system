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
import android.widget.ProgressBar;

public class login extends AppCompatActivity {
    EditText email,pass;
    private ProgressBar spinner;
    FirebaseAuth fauth;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.editText);
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
    public void home(View v){
        AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
        v.startAnimation(buttonClick);

        Vibrator vv = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);



        vv.vibrate(100);


        if (email.getText().toString().equals("") || pass.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(),"NULL PASSWORD OR EMAIL :(",Toast.LENGTH_SHORT).show();
        }
        else
        {   spinner = (ProgressBar)findViewById(R.id.progressBar1);

            spinner.setVisibility(View.VISIBLE);

            fauth.signInWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {

                @Override

                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        SharedPreferences sf= getSharedPreferences("Login data",MODE_PRIVATE);
                        SharedPreferences.Editor edit= sf.edit();
                        edit.putInt("islogged",1);
                        edit.putString("user",email.getText().toString());
                        edit.apply();
                        Intent i= new Intent(getApplicationContext(),home.class);
                        spinner.setVisibility(View.GONE);
                        startActivity(i);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"INCORRECT CREDENTIALS",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }



    }
}

