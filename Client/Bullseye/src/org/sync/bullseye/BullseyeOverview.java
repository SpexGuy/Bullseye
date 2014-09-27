package org.sync.bullseye;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

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
    
}
