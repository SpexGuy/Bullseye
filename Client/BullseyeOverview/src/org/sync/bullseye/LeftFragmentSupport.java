package org.sync.bullseye;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LeftFragmentSupport {

	View original;
	
	public LeftFragmentSupport(View v){
		original=v;
		TextView tx= (TextView)v.findViewById(R.id.text);
		tx.setText("WAS CHANGED BY ME!");
		setUpButton();
	}
	public void setUpButton(){
		
		Button button = (Button)original.findViewById(R.id.test_Button);
		 button.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	            	TextView tx= (TextView)original.findViewById(R.id.text);
	                tx.setText("Works!");
	            }
	        });

	}
}
