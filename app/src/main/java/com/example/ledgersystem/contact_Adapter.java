package com.example.ledgersystem;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

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
        final contactHolder myHolder = new contactHolder(myView);

        requestDialog = new Dialog(ctx);
        requestDialog.setContentView(R.layout.dialog_req);
        final TextView c_name = (TextView)requestDialog.findViewById(R.id.dialog_name_d);
        final TextView c_no = requestDialog.findViewById(R.id.dialog_number_d);
        final CircleImageView c_img =  requestDialog.findViewById(R.id.dialog_img_d);




        myHolder.item_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c_name.setText(data.get(myHolder.getAdapterPosition()).getName());
                c_no.setText(data.get(myHolder.getAdapterPosition()).getPhone());
                c_img.setImageResource(R.drawable.ic_launcher_background);
                Toast.makeText(ctx,"you click" + String.valueOf(myHolder.getAdapterPosition()),Toast.LENGTH_SHORT).show();
                requestDialog.show();
            }
        });

        return myHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull contactHolder holder, int position) {
        //todo we have to bind the holder later on let me do the fragment work done.
        final listforadapter myListobj1 =data.get(position);
        holder.t1_name.setText(myListobj1.getName());
        holder.t2_number.setText(myListobj1.getPhone());
        holder.contactimg.setImageResource(R.drawable.ic_launcher_background);
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
