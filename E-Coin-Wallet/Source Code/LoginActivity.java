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
import android.widget.Toast;

public class LoginActivity extends Activity {
    EditText txtacno,txtpkey;
    String gblacno,gblprkey;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        txtacno=(EditText)findViewById(R.id.txtAccNo);
        txtpkey=(EditText)findViewById(R.id.txtpwd);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        gblacno = preferences.getString("acno","");
        gblprkey=preferences.getString("prkey","");
        txtacno.setText(gblacno);
        txtpkey.setText(gblprkey);
    }
    
    public void login(View v){
    	String acno=txtacno.getText().toString();
    	String pwd=txtpkey.getText().toString();
    	
    	if(acno.compareTo("")==0 && pwd.compareTo("")==0 ){
			 Toast.makeText(getApplicationContext(), "Please enter a valid Account No. and Password", Toast.LENGTH_LONG).show();
		 }else{
			 String inputline="";
			 String totalData="";
			 try {
					URL url=new URL("http://myspace.belgaumonline.com/blockchain/chklogin.php?acno="+ acno +"&pkey=" + pwd+pwd+pwd+pwd);
					BufferedReader in=new BufferedReader(new InputStreamReader(url.openStream()));
					while((inputline=in.readLine())!=null){
						totalData+=inputline;
					}
			 }catch (Exception e) {
				// TODO: handle exception
				 Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
			}
			Toast.makeText(getApplicationContext(), totalData, Toast.LENGTH_SHORT).show();
			if(totalData.compareTo("valid")==0){
				
				SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
	 		  	SharedPreferences.Editor editor = preferences.edit();
	 		  	editor.putString("acno",acno);
	 		  	editor.putString("prkey",pwd);
	 		  	editor.commit();
				
	 		  	Intent i = new Intent(LoginActivity.this, MenuPageActivity.class);
				startActivity(i);
			}else{
				Toast.makeText(getApplicationContext(), "INVALID Account No or Private Key", Toast.LENGTH_SHORT).show();	
			}
		 }
    }
}