package com.lca.app.mycontact;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.lca.app.mycontact.adapter.CustomAdapter;
import com.lca.app.mycontact.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView list_contact;
//    String[] con={"titanic","love letter","Romeo","robin hood"};
    ArrayList<Contact> arrContact;
    CustomAdapter adapter;
    EditText edtname,edtphone;
    Button btnAdd;
    RadioButton rdMale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        checkAndRequestPermissions();
        adapter = new CustomAdapter(this,R.layout.list_item,arrContact);
        list_contact.setAdapter(adapter);

        list_contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                showDialogConfirm(position);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=edtname.getText().toString();
                String phone=edtphone.getText().toString();
                boolean isMale = true;

                if (rdMale.isChecked()) {
                    isMale = true;
                } else {
                    isMale = false;
                }
                Contact contact=new Contact(isMale,name,phone);
                arrContact.add(contact);
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void showDialogConfirm(final int position) {
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        Button btnCall = (Button)dialog.findViewById(R.id.btn_call);
        Button btnSend = (Button) dialog.findViewById(R.id.btn_sms);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentCall(position);
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentSend(position);
            }
        });
        dialog.show();
    }

    private void intentSend(int position) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:"+arrContact.get(position).getPhone()));
        startActivity(intent);
    }

    private void intentCall(int position) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+arrContact.get(position).getPhone()));
        startActivity(intent);
    }

    public void initWidget(){
        edtname = (EditText) findViewById(R.id.edt_name);
        edtphone= (EditText) findViewById(R.id.edt_phone);
        rdMale= (RadioButton) findViewById(R.id.rd_male);
        btnAdd= (Button) findViewById(R.id.btnAdd);
        arrContact=new ArrayList<>();
        list_contact= (ListView) findViewById(R.id.list_item);
    }
    private void checkAndRequestPermissions() {
        String[] permissions = new String[]{
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS
        };
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
        }
    }
}
