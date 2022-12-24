package com.project.ECoinWallet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.*;  

public class AccountCreateActivity extends Activity {
    EditText txtPWD,txtuid,txtph;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_create);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        txtPWD=(EditText)findViewById(R.id.txtPWD);
        txtuid=(EditText)findViewById(R.id.txtuid);
        txtph=(EditText)findViewById(R.id.txtPhone);    
    }
    
    public void createAccount(View v){
    	//...Send details to Cloud server and save ..send private key as SMS
    	String uid,pwd,ph,pwd32;
    	uid=txtuid.getText().toString();
    	pwd=txtPWD.getText().toString();
    	ph=txtph.getText().toString();
    	if(uid.compareTo("")==0 || pwd.compareTo("")==0 || ph.compareTo("")==0 ){
			 Toast.makeText(getApplicationContext(), "Please enter all the details", Toast.LENGTH_LONG).show();
		 }else if(pwd.length()!=8){
			 Toast.makeText(getApplicationContext(), "Password Length should be 8 Charatcters", Toast.LENGTH_LONG).show();
		}else if(isPhoneValid(ph) && isPWDValid(pwd)){
			pwd32=pwd + pwd + pwd + pwd;
			String inputline="";
			String totalData="";
			try {
					URL url=new URL("http://myspace.belgaumonline.com/blockchain/saveaccount.php?pwd="+ pwd32 +"&uid="+ uid +"&ph="+ph);
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
    	else{
    		Toast.makeText(getApplicationContext(), "Phone or Pwd not valid", Toast.LENGTH_LONG).show();
		 }
    } 
    
    public static boolean isPhoneValid(String str){
    	int n=str.length();
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) < '0'
                || str.charAt(i) > '9') {
                return false;
            }
        }
        if(n==10)
        	return true;
        else
        	return false;
        		
    }
    
    public static boolean isPWDValid(String str){
    	int n=str.length();
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) < '0'
                || str.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }
    
}