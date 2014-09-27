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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class RightFragmentSupport {

	String responseStr="";
	View original;
	Activity activity;
	final String USERNAME;

	public RightFragmentSupport(View v, Activity active, String userName){
		activity=active;
		original=v;
		USERNAME=userName;
		String getUpgrades = "get-upgrades/?user="+USERNAME;
		new MakeGetCall().execute(getUpgrades);

		new Thread(new Runnable() {
			public void run() {
				while(true){
					String command = "ping/?user="+USERNAME;				
					String baseUrl = "http://bullseye-server.herokuapp.com/";	
					HttpGet httpGet = new HttpGet(baseUrl+command);
					HttpClient httpClient = new DefaultHttpClient(); 
					try {
						HttpResponse response = httpClient.execute(httpGet);
						StatusLine statusLine = response.getStatusLine();
						if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
							HttpEntity entity = response.getEntity();
							ByteArrayOutputStream out = new ByteArrayOutputStream();
							entity.writeTo(out);
							out.close();
							// do something with response 
						} else {
							// handle bad response
						}
					} catch (ClientProtocolException e) {
						// handle exception
					} catch (IOException e) {
						// handle exception
					}			
					
					try{
						Thread.sleep(1000);
					}catch(InterruptedException e){};
				}  

			}
		}).start();

	}
	public void setUpLayout(UpgradeStatusLists status){ //takes in object holding 3 arrayLists.

		TableLayout upgradeField = (TableLayout)original.findViewById(R.id.individual_feature_TableRow);

		for(int x=0; x<status.getAlreadyPurchased().size(); x++){
			TableRow upgradeDescription = new TableRow(original.getContext());
			Button upgradeName = setUpButton(status.getAlreadyPurchased().get(x).getName(),x);
			upgradeName.setClickable(false);
			upgradeDescription.addView(upgradeName);
			upgradeField.addView(upgradeDescription);
		}

		//adds an empty line
		TableRow empty = new TableRow(original.getContext());
		TextView pmet = new TextView(original.getContext());
		pmet.setText(" ");
		empty.addView(pmet);
		upgradeField.addView(empty);

		for(int x=0; x<status.getInProduction().size();x++){
			TableRow upgradeDescription = new TableRow(original.getContext());
			Button upgradeName = setUpButton(status.getInProduction().get(x).getName(),x);
			upgradeName.setClickable(false);
			TextView timeLeft = new TextView(original.getContext());
			timeLeft.setText(Integer.toString(status.getInProduction().get(x).getTime()));
			upgradeDescription.addView(upgradeName);
			upgradeDescription.addView(timeLeft);
			upgradeField.addView(upgradeDescription);
		}

		//adds an empty line
		empty = new TableRow(original.getContext());
		pmet = new TextView(original.getContext());
		pmet.setText(" ");
		empty.addView(pmet);
		upgradeField.addView(empty);

		for(int x=0; x<status.getCanPurchase().size(); x++){
			TableRow upgradeDescription = new TableRow(original.getContext());
			Button upgradeName = setUpButton(status.getCanPurchase().get(x).getName(),x);
			TextView upgradeCost = new TextView(original.getContext());
			upgradeCost.setText(Integer.toString(status.getCanPurchase().get(x).getCost()));
			upgradeDescription.addView(upgradeName);
			upgradeDescription.addView(upgradeCost);
			upgradeField.addView(upgradeDescription);
		}

	}
	private Button setUpButton(final String name, int index) {
		final Button listButton = new Button(original.getContext());
		final int DESIREDINT=index;
		listButton.setText(name);
		listButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//make asynch call to server given my index.
				String command = "buy-upgrade/?user="+USERNAME+"&index="+DESIREDINT;
				MakeGetCall callServer = new MakeGetCall();
				callServer.execute(command);
			}

		});
		return listButton;
	}
	private class MakeGetCall extends AsyncTask{


		@Override
		protected Object doInBackground(Object... arg0) {
			if(arg0[0] == null || arg0[0].getClass() != String.class){
				throw new RuntimeException(); //bad things happened
			}
			String baseUrl = "http://bullseye-server.herokuapp.com/";	
			String command = (String)arg0[0];
			HttpGet httpGet = new HttpGet(baseUrl+command);
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
					if(!responseStr.equals("true") && !responseStr.equals("false")){
						UpgradeStatusLists lists = new UpgradeStatusLists(responseStr);
						//parseStrings(); returns 3 separate array lists done, in progrgress, yet to be completed.
						setUpLayout(lists);
					}
					
				}});

			return null;
		}

	}
}
