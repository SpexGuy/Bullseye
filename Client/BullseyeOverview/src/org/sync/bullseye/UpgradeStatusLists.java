package org.sync.bullseye;
import org.json.*;
import java.util.ArrayList;

public class UpgradeStatusLists {
	
	ArrayList<Upgrade> alreadyPurchased = new ArrayList<Upgrade>();
	ArrayList<InProgressUpgrade> inProduction = new ArrayList<InProgressUpgrade>();
	ArrayList<Upgrade> canPurchase = new ArrayList<Upgrade>();
	
	public UpgradeStatusLists(String serverResponse){
		JSONObject obj = new JSONObject("serverResponse");
		JSONArray  availableArray = obj.getJSONObject("available");
		for(int i = 0 ; i < availableArray.length() ; i++)
		{
			JSONObject o = availableArray.getJSONObject(i);
			canPurchase.add(new Upgrade(o.getString("name"),o.getInt("cost"),o.getInt("time")));
		}
		JSONArray a = obj.getJSONObject("unlocked");
		for(int i = 0 ; i < a.length() ; i++)
		{
			JSONObject o = a.getJSONObject(i);
			alreadyPurchased.add(new Upgrade(o.getString("name"),o.getInt("cost"),o.getInt("time")));
		}
		a = obj.getJSONObject("inProgress");
		for(int i = 0 ; i < a.length() ; i++)
		{
			JSONArray subA = a.getJSONArray(i);
			JSONObject o = subA.get(1);
			inProduction.add(new InProgressUpgrade(o.getString("name"),o.getInt("cost"),o.getInt("time"),subA.get(0)));
		}

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
