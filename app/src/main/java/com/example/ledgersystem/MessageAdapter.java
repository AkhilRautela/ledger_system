package com.example.ledgersystem;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.time.Instant;
import java.util.List;

public class MessageAdapter extends ArrayAdapter<FriendlyMessage> {


    public MessageAdapter(Context context, int resource, List<FriendlyMessage> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.group_chat_child, parent, false);
        }

        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        TextView authorTextView = (TextView) convertView.findViewById(R.id.nameTextView);




        FriendlyMessage message = getItem(position);

        messageTextView.setText(message.getText());

        authorTextView.setText(message.getName());

        TextDrawable drawable = TextDrawable.builder().buildRound(message.getName().substring(0,1).toUpperCase(), Color.rgb(15,167,255));
        photoImageView.setImageDrawable(drawable);
        return convertView;
    }
}
