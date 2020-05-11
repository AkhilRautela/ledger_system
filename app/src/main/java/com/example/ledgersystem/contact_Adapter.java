package com.example.ledgersystem;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.amulyakhare.textdrawable.TextDrawable;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

public class contact_Adapter extends RecyclerView.Adapter<contact_Adapter.contactHolder> {
    Context ctx;
    List<listforadapter> data;
    Dialog requestDialog;

    public contact_Adapter(Context c, List<listforadapter> data_list){
        ctx = c;
        data = data_list;
    }

    @NonNull
    @Override
    public contact_Adapter.contactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myInflater = LayoutInflater.from(ctx);
        View myView = myInflater.inflate(R.layout.contact_child,parent,false);
        return new contactHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull contactHolder holder, int position) {
        //todo we have to bind the holder later on let me do the fragment work done.
        final listforadapter myListobj1 =data.get(position);
        holder.t1_name.setText(myListobj1.getName());
        holder.t2_number.setText(myListobj1.getPhone());
        TextDrawable drawable = TextDrawable.builder().buildRound(myListobj1.getName().substring(0,1).toUpperCase(), Color.GREEN);
        holder.contactimg.setBackground(drawable);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDialog = new Dialog(ctx);
                requestDialog.setContentView(R.layout.dialog_req);
                final TextView c_name = (TextView)requestDialog.findViewById(R.id.dialog_name_d);
                final TextView c_no = requestDialog.findViewById(R.id.dialog_number_d);
                final CircleImageView c_img =  requestDialog.findViewById(R.id.dialog_img_d);
                Button b=requestDialog.findViewById(R.id.requestt);
                final EditText money=requestDialog.findViewById(R.id.amt);
                final EditText remark=requestDialog.findViewById(R.id.remark);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseReference dr= FirebaseDatabase.getInstance().getReference("Users");
                        SharedPreferences sf=ctx.getSharedPreferences("Login data",MODE_PRIVATE);
                        String s=sf.getString("user","unable to fetch");
                        dr.child(s).child("Transactions").child("take money").child(myListobj1.getName()).child(remark.getText().toString()).setValue(money.getText().toString());
                        dr.child(myListobj1.getName()).child("Transactions").child("give money").child(s).child(remark.getText().toString()).setValue(money.getText().toString());
                        requestDialog.dismiss();
                    }
                });
                c_name.setText(myListobj1.getName());
                c_no.setText(myListobj1.getPhone());
                TextDrawable drawable = TextDrawable.builder().buildRound(myListobj1.getName().substring(0,1).toUpperCase(), Color.GREEN);
                c_img.setBackground(drawable);
                requestDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class contactHolder extends RecyclerView.ViewHolder {
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
}
