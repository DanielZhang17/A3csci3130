package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText num, name,address;
    private Spinner businessType, prov;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());
        submitButton = (Button) findViewById(R.id.submitButton);
         num = (EditText)findViewById(R.id.num);
         name = (EditText)findViewById(R.id.name);
         address = (EditText)findViewById(R.id.address);
         businessType = (Spinner) findViewById(R.id.pb);
         prov = (Spinner)findViewById(R.id.prov);
        ArrayAdapter<CharSequence> arrayAdapter1 = ArrayAdapter.createFromResource(appState,R.array.BusinessType,android.R.layout.simple_spinner_item);
        businessType.setAdapter(arrayAdapter1);
        ArrayAdapter<CharSequence> arrayAdapter2 = ArrayAdapter.createFromResource(appState,R.array.Province_territory,android.R.layout.simple_spinner_item);
        prov.setAdapter(arrayAdapter2);
    }

    public void submitInfoButton(View v) {
        String t = num.getText().toString();
        if(t.length()<9){
            num.setError("This field should be a 9 digit number !");
            return;
        }
        if(!android.text.TextUtils.isDigitsOnly(t)){
            num.setError("This field should be a 9 digit number !");
            return;
        }
        long n = Long.parseLong(t);
        String Cname = name.getText().toString();
        if(Cname.length()>48||Cname.length()<2){
            name.setError("This field should be 2-48 characters long !");
            return;
        }
        String Address = address.getText().toString();
        if(Address.length()>=50){
            address.setError("The address should be less than 50 characters !");
            return;
        }
        String PB = businessType.getSelectedItem().toString();
        String Prov = prov.getSelectedItem().toString();
        String id = appState.firebaseReference.push().getKey();
        Business business = new Business(n,Cname,PB,Address,Prov,id);
        appState.firebaseReference.child(id).setValue(business).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                    Toast.makeText(getApplicationContext(),"Item successfully added!",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"Sorry, there was an error.",Toast.LENGTH_LONG).show();
            }
        });
        finish();

    }
}
