package com.project.ECoinWallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MenuPageActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupage);
    }
    
    public void logout(View v){
    	Intent i1 = new Intent(MenuPageActivity.this, LoginActivity.class);
		startActivity(i1);
    }
    
    public void buycoins(View v){
    	Intent i1 = new Intent(MenuPageActivity.this, BuyCoinsActivity.class);
		startActivity(i1);
    }

    public void paycoins(View v){
    	Intent i1 = new Intent(MenuPageActivity.this, PayCoinsActivity.class);
		startActivity(i1);
    }
    
    public void chkbalance(View v){
    	Intent i1 = new Intent(MenuPageActivity.this, BalanceActivity.class);
		startActivity(i1);
    }
}