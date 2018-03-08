package com.lca.app.mycontact.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lca.app.mycontact.R;
import com.lca.app.mycontact.model.Contact;

import java.util.ArrayList;

import javax.crypto.spec.RC2ParameterSpec;

/**
 * Created by Admin on 03/03/2018.
 */

public class CustomAdapter extends ArrayAdapter<Contact> {
    private Context context;
    private int resource;
    private ArrayList<Contact> list_contact;

    public CustomAdapter(Context context, int resource, ArrayList<Contact> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.list_contact=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.imgAvatar=(ImageButton)convertView.findViewById(R.id.imgAvatar);
            viewHolder.tvname= (TextView)convertView.findViewById(R.id.tv_name);
            viewHolder.tvphone= (TextView)convertView.findViewById(R.id.tv_phone);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Contact contact=list_contact.get(position);
        viewHolder.tvname.setText(contact.getName());
        viewHolder.tvphone.setText(contact.getPhone());
        if(contact.isMale()){
            viewHolder.imgAvatar.setBackgroundResource(R.drawable.male);
        }else{
            viewHolder.imgAvatar.setBackgroundResource(R.drawable.female);
        }

        return convertView;
    }
    public class ViewHolder{
        TextView tvname,tvphone;
        ImageButton imgAvatar;
    }
}
