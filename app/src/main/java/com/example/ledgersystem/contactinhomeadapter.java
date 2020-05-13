package com.example.ledgersystem;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.amulyakhare.textdrawable.TextDrawable;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

class contactsinhomeadapter extends RecyclerView.Adapter<contactsinhomeadapter.contactHolder> {
    Context ctx;
    List<listforadapter> data;
    Dialog requestDialog;

    class contactHolder extends RecyclerView.ViewHolder {
        LinearLayout item_contact;
        TextView t1_name;
        TextView t2_number;
        CircleImageView contactimg;
        public contactHolder(@NonNull View itemView) {
            super(itemView);
            item_contact = itemView.findViewById(R.id.contact_item);
            t1_name = itemView.findViewById(R.id.contact_name_d);
            t2_number = itemView.findViewById(R.id.contact_number_d);
            contactimg = itemView.findViewById(R.id.contact_img_d);
        }

    }
    public contactsinhomeadapter(Context c, List<listforadapter> data_list){
        ctx = c;
        data = data_list;
    }

    @NonNull
    @Override
    public contactsinhomeadapter.contactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myInflater = LayoutInflater.from(ctx);
        View myView = myInflater.inflate(R.layout.contact_child,parent,false);
        return new contactHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull contactHolder holder, int position) {
        //todo we have to bind the holder later on let me do the fragment work done.
        final listforadapter myListobj1 = data.get(position);
        holder.t1_name.setText(myListobj1.getName());
        holder.t2_number.setText(myListobj1.getPhone());
        TextDrawable drawable = TextDrawable.builder().buildRound(myListobj1.getName().substring(0, 1).toUpperCase(), Color.rgb(15,167,255));
        holder.contactimg.setBackground(drawable);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
                v.startAnimation(buttonClick);
                Vibrator vv = (Vibrator)ctx.getSystemService(Context.VIBRATOR_SERVICE);
                vv.vibrate(100);
                inbetweendata.name=myListobj1.getName();
                FragmentManager manager = ((home)ctx).getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.main_hu_container,new perpersoninfo_fragment()).commit();
                manager.beginTransaction().addToBackStack("personinfo").commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
