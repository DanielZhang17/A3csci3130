package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class DetailViewActivity extends Activity {

    private EditText num, name,address;
    private Spinner businessType, prov;
    private MyApplicationData appState;
    Business receivedInfo;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        appState = ((MyApplicationData) getApplicationContext());
        receivedInfo = (Business)getIntent().getSerializableExtra("Business");
        num = (EditText)findViewById(R.id.num);
        name = (EditText)findViewById(R.id.name);
        address = (EditText)findViewById(R.id.address);
        businessType = (Spinner) findViewById(R.id.pb);
        prov = (Spinner)findViewById(R.id.prov);
        ArrayAdapter<CharSequence> arrayAdapter1 = ArrayAdapter.createFromResource(getApplicationContext(),R.array.BusinessType,android.R.layout.simple_spinner_item);
        businessType.setAdapter(arrayAdapter1);
        ArrayAdapter<CharSequence> arrayAdapter2 = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Province_territory,android.R.layout.simple_spinner_item);
        prov.setAdapter(arrayAdapter2);

        if(receivedInfo != null){
            num.setText(receivedInfo.getBusinessNumber()+"");
            name.setText(receivedInfo.getName());
            address.setText(receivedInfo.getAddress());
            businessType.setSelection(arrayAdapter1.getPosition(receivedInfo.getPrimaryBusiness()));
            prov.setSelection(arrayAdapter2.getPosition(receivedInfo.getProvince()));
            uid = receivedInfo.getUID();
        }
    }

    public void update(View v){
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
        if(uid==null){
            Toast.makeText(getApplicationContext(),"Sorry,there was an error",Toast.LENGTH_LONG).show();
            return;
        }
        Business business = new Business(n,Cname,PB,Address,Prov,uid);
        appState.firebaseReference.child(uid).setValue(business).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                    Toast.makeText(getApplicationContext(),"Item successfully updated !",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"Sorry, there was an error.",Toast.LENGTH_LONG).show();
            }
        });
        finish();
    }
    public void erase(View v)
    {
        if(uid==null){
            Toast.makeText(getApplicationContext(),"Sorry,there was an error",Toast.LENGTH_LONG).show();
            return;
        }
        appState.firebaseReference.child(uid).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                    Toast.makeText(getApplicationContext(),"Operation successful !",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"Some error occurred !",Toast.LENGTH_LONG).show();
            }
        });
        finish();
        startActivity(new Intent(DetailViewActivity.this,MainActivity.class));
    }
}
