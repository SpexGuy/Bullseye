package org.sync.bullseye;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UpgradeStatusLists {
	
	ArrayList<Upgrade> alreadyPurchased = new ArrayList<Upgrade>();
	ArrayList<Upgrade> inProduction = new ArrayList<Upgrade>();
	ArrayList<Upgrade> canPurchase = new ArrayList<Upgrade>();
	
	public UpgradeStatusLists(String serverResponse){
		
		try{
		JSONObject obj = new JSONObject(serverResponse);
		JSONArray  availableArray = obj.getJSONArray("available");
		for(int i = 0 ; i < availableArray.length() ; i++)
		{
			JSONObject o = availableArray.getJSONObject(i);
			canPurchase.add(new Upgrade(o.getString("name"),o.getInt("cost"),o.getInt("time")));
		}
		JSONArray a = obj.getJSONArray("unlocked");
		for(int i = 0 ; i < a.length() ; i++)
		{
			JSONObject o = a.getJSONObject(i);
			alreadyPurchased.add(new Upgrade(o.getString("name"),o.getInt("cost"),o.getInt("time")));
		}
		a = obj.getJSONArray("inProgress");
		for(int i = 0 ; i < a.length() ; i++)
		{
			JSONArray subA = a.getJSONArray(i);
			JSONObject o = (JSONObject) subA.get(1);
			inProduction.add(new Upgrade(o.getString("name"),o.getInt("cost"),o.getInt("time")));
		}
		}catch(JSONException e){
			throw new RuntimeException(e);
		};
		

	}
	public ArrayList<Upgrade> getAlreadyPurchased(){
		
		return alreadyPurchased;
	}
	public ArrayList<Upgrade> getInProduction(){
		
		return inProduction;
	}
	public ArrayList<Upgrade> getCanPurchase(){
		
		return canPurchase;
	}

}
