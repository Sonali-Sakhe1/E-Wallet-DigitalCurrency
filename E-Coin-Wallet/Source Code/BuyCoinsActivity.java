package com.project.ECoinWallet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class BuyCoinsActivity extends Activity {
    EditText txtnoofcoins;
    Spinner cmbFrom;
    String gblacno;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyecoinform);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        gblacno = preferences.getString("acno","");
        
        txtnoofcoins=(EditText)findViewById(R.id.txtNoOfCoins);
        cmbFrom=(Spinner)findViewById(R.id.cmbFrom);
    }
    
    public void buy(View v){
    	//...Send details to Cloud server and save to pending transactions.
    	String buyfrom,coins;
    	buyfrom=String.valueOf(cmbFrom.getSelectedItem());
    	coins=txtnoofcoins.getText().toString();
    	
    	if(buyfrom.compareTo("")==0 && coins.compareTo("")==0){
			 Toast.makeText(getApplicationContext(), "Please enter all the details", Toast.LENGTH_LONG).show();
		 }else{
			 String inputline="";
			 String totalData="";
			 try {
					URL url=new URL("http://myspace.belgaumonline.com/blockchain/savependingtrans.php?toac="+ gblacno +"&fromac="+ buyfrom +"&amt="+ coins +"&acno="+ gblacno);
					BufferedReader in=new BufferedReader(new InputStreamReader(url.openStream()));
					while((inputline=in.readLine())!=null){
						totalData+=inputline;
					}
			 }catch (Exception e) {
				// TODO: handle exception
				 Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
			}
			Toast.makeText(getApplicationContext(), totalData, Toast.LENGTH_SHORT).show();
		 }
    } 
}