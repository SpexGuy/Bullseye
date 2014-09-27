package org.sync.bullseye;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

@SuppressLint("NewApi") public class RightFragmentSupport {
	boolean getCallFinished=false;
	String returnedData="No data";
	View original;
	Activity activity;
	public RightFragmentSupport(View v, Activity active){
		activity=active;
		original=v;
		new MakeGetCall().execute("blah");
		//setUpLayout();


	}
	public void setUpLayout(){
		
		LinearLayout featureList = (LinearLayout)original.findViewById(R.id.feature_list_LinearLayout);
		Button feature = setUpButton(returnedData);
		Button test = setUpButton("$1000");
		featureList.addView(feature);
		featureList.addView(test);

	}
	private Button setUpButton(final String name) {
		final Button listButton = new Button(original.getContext());
		listButton.setText(name);
		listButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				updateSelectedList(listButton);
			}

		});
		return listButton;
	}
	public void updateSelectedList(Button lb){

	}
	private class MakeGetCall extends AsyncTask {


		@Override
		protected Object doInBackground(Object... arg0) {
			//String test = (String)arg0[0];
			String url = "http://bullseye-server.herokuapp.com/";
			String command = "get-upgrades/?user=spexguy";		
			HttpGet httpGet = new HttpGet(url+command);
			HttpClient httpClient = new DefaultHttpClient(); 
			String responseStr = null;
			try {
				HttpResponse response = httpClient.execute(httpGet);
				StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity entity = response.getEntity();
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					entity.writeTo(out);
					out.close();
					responseStr = out.toString();
					// do something with response 
				} else {
					// handle bad response
				}
			} catch (ClientProtocolException e) {
				// handle exception
			} catch (IOException e) {
				// handle exception
			}
			getCallFinished=true;
			returnedData=responseStr;
			activity.runOnUiThread(new Runnable() {
				public void run(){
				setUpLayout();
				}});
			
			return null;
		}

	}
}
