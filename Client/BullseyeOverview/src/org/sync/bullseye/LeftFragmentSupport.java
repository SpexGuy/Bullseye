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

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public class LeftFragmentSupport {

	View original;
	String responseStr;
	Activity activity;
	
	public LeftFragmentSupport(View v, Activity active){
		original=v;
		activity=active;
		TextView tx= (TextView)v.findViewById(R.id.text);
		new MakeGetCall().execute();
	}
	public void setUpLayout(TaskStatusLists tasks){ //takes in object holding 3 arrayLists.

		TableLayout taskField = (TableLayout)original.findViewById(R.id.task_list_TableLayout);
		
		for(int x=0; x<tasks.getCurrentlyActive().size(); x++){
			LinearLayout taskDescription = new LinearLayout(original.getContext());
			Button taskName = setUpButton(tasks.getCurrentlyActive().get(x).getName());
			TextView timeRemaining = new TextView(original.getContext());
			timeRemaining.setText(Integer.toString(tasks.getCurrentlyActive().get(x).getTime()));
			TextView workers = new TextView(original.getContext());
			workers.setText(Integer.toString(tasks.getCurrentlyActive().get(x).getCost()));
			TextView reward = new TextView(original.getContext());
			reward.setText(Integer.toString(tasks.getCurrentlyActive().get(x).getReward()));
			taskDescription.addView(taskName);
			taskDescription.addView(timeRemaining);
			taskDescription.addView(workers);
			taskDescription.addView(reward);
			taskField.addView(taskDescription);
		}
		
		for(int x=0; x<tasks.getCurrentlyInactive().size(); x++){
			LinearLayout taskDescription = new LinearLayout(original.getContext());
			Button taskName = setUpButton(tasks.getCurrentlyInactive().get(x).getName());
			TextView timeRemaining = new TextView(original.getContext());
			timeRemaining.setText(Integer.toString(tasks.getCurrentlyInactive().get(x).getTime()));
			TextView workers = new TextView(original.getContext());
			workers.setText(Integer.toString(tasks.getCurrentlyInactive().get(x).getCost()));
			TextView reward = new TextView(original.getContext());
			reward.setText(Integer.toString(tasks.getCurrentlyInactive().get(x).getReward()));
			taskDescription.addView(taskName);
			taskDescription.addView(timeRemaining);
			taskDescription.addView(workers);
			taskDescription.addView(reward);
			taskField.addView(taskDescription);
		}
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
		//put code to make server call.
	}
	private class MakeGetCall extends AsyncTask {


		@Override
		protected Object doInBackground(Object... arg0) {
			//String test = (String)arg0[0];
			String url = "http://bullseye-server.herokuapp.com/";
			String command = "get-tasks/?user=spexguy";		
			HttpGet httpGet = new HttpGet(url+command);
			HttpClient httpClient = new DefaultHttpClient(); 
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
			activity.runOnUiThread(new Runnable() {
				public void run(){
				TaskStatusLists lists = new TaskStatusLists(responseStr);
				setUpLayout(lists);
				}});
			
			return null;
		}

	}
	
}
