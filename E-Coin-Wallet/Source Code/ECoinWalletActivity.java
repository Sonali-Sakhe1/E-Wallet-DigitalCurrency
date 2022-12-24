package com.project.ECoinWallet;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ECoinWalletActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void loginclick(View v){
    	Intent i1 = new Intent(ECoinWalletActivity.this, LoginActivity.class);
		startActivity(i1);
    }
    	
    public void Createclick(View v){
    	Intent i2 = new Intent(ECoinWalletActivity.this, AccountCreateActivity.class);
		startActivity(i2);
    }
    
}