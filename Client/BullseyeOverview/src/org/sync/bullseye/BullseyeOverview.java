package org.sync.bullseye;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class BullseyeOverview extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bullseye_overview);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bullseye_overview, menu);
        return true;
    }
    
	public void goToStore(View view) {
		Intent intent = new Intent(this, StoreDisplay.class);
		startActivity(intent);
	}
	public void goToAccount(View view) {
		Intent intent = new Intent(this, AccountOptions.class);
		startActivity(intent);
	}
	public void startNewGame(View view) {
		Intent intent = new Intent(this, NewGame.class);
		startActivity(intent);
	}
    
    
}
