package com.example.ledgersystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.amulyakhare.textdrawable.TextDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.collection.LLRBNode;

import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import de.hdodenhof.circleimageview.CircleImageView;

public class home extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
        }
        else {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_hu_container,new home_fragment()).commit();
            getSupportFragmentManager().popBackStack();
        }

    }
    DrawerLayout drawer;
    NavigationView navigationView;
    TextView lt;
    String s;
    private AppBarConfiguration mAppBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_hu_container,new home_fragment()).commit();
        SharedPreferences sf=getSharedPreferences("Login data",MODE_PRIVATE);
        s=sf.getString("user","unable to fetch");
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ConstraintLayout c= findViewById(R.id.bcontrol);
        Button b1=c.findViewById(R.id.navbarcoming);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
                v.startAnimation(buttonClick);
                Vibrator vv = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                vv.vibrate(100);
                drawer.openDrawer(GravityCompat.START);
                lt=findViewById(R.id.nameo);
                lt.setText(s);
                TextDrawable drawable = TextDrawable.builder().buildRound(s.substring(0,1), Color.BLUE);
                CircleImageView iv=findViewById(R.id.photoo);
                iv.setBackground(drawable);
            }
        });
        Button b2=c.findViewById(R.id.noticoming);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Notifications",Toast.LENGTH_SHORT).show();
                AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
                v.startAnimation(buttonClick);
                Vibrator vv = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                vv.vibrate(100);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_hu_container,new notification_fragment()).commit();
                getSupportFragmentManager().beginTransaction().addToBackStack("notification").commit();
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int l=menuItem.getItemId();
                if(l==R.id.nav_home){
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_hu_container,new home_fragment()).commit();

                }
                if(l==R.id.nav_logout){
                    Toast.makeText(getApplicationContext(),"Logging Out",Toast.LENGTH_SHORT);
                    SharedPreferences sf=getSharedPreferences("Login Data",MODE_PRIVATE);
                    SharedPreferences.Editor edit=sf.edit();
                    edit.putInt("islogged",0);
                    edit.commit();
                    Intent i = new Intent(getApplicationContext(),login.class);
                    startActivity(i);
                    finish();
                }
                if(l==R.id.nav_about){
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_hu_container,new about_fragment()).commit();
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

}
