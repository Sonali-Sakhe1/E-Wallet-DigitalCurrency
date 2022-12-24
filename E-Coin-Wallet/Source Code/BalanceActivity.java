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
import android.widget.TextView;
import android.widget.Toast;

public class BalanceActivity extends Activity {
    TextView lblAcNo,lblBal;
    String gblacno,gblprkey;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_balance);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        lblAcNo=(TextView)findViewById(R.id.lblacno);
        lblBal=(TextView)findViewById(R.id.lblbal);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        gblacno = preferences.getString("acno","");
        gblprkey=preferences.getString("prkey","");
        lblAcNo.setText(gblacno);
    //...Fetch Balance
    	String inputline="";
		String totalData="";
		try {
				URL url=new URL("http://myspace.belgaumonline.com/blockchain/getbalance.php?acno="+ gblacno);
				BufferedReader in=new BufferedReader(new InputStreamReader(url.openStream()));
				while((inputline=in.readLine())!=null){
					totalData+=inputline;
				}
		}catch (Exception e) {
			 Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
		}
		lblBal.setText(totalData);
		//Toast.makeText(getApplicationContext(), totalData, Toast.LENGTH_SHORT).show();
    }
}