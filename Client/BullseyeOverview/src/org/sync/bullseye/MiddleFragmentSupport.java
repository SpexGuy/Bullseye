package org.sync.bullseye;

import android.app.Activity;
import android.view.View;

import com.google.zxing.integration.android.IntentIntegrator;

public class MiddleFragmentSupport {

	View original;
	Activity activity;
	
	public MiddleFragmentSupport(View v, Activity active){
		original = v;
		activity=active;
	}

	
}
