package org.sync.bullseye;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TaskStatusLists {
	
	ArrayList<Upgrade> current = new ArrayList<Upgrade>();
	ArrayList<Upgrade> future = new ArrayList<Upgrade>();
	
	public UpgradeStatusLists(String serverResponse){
		

		try{
		JSONObject obj = new JSONObject(serverResponse);
		int numEmployees = obj.getInt("employeesAvailable");
		JSONArray  availableArray = obj.getJSONArray("tasksAvailable");
		for(int i = 0 ; i < availableArray.length() ; i++)
		{
			JSONObject o = availableArray.getJSONObject(i);
			current.add(new Upgrade(o.getString("name"),o.getInt("cost"),o.getInt("time"),o.getInt("reward")));
		}
		a = obj.getJSONArray("tasksInProgress");
		for(int i = 0 ; i < a.length() ; i++)
		{
			JSONArray subA = a.getJSONArray(i);
			JSONObject o = (JSONObject) subA.get(1);
			current.add(new Upgrade(o.getString("name"),o.getInt("cost"),o.getInt("time"),o.getInt("reward")));
		}
		}catch(JSONException e){
			throw new RuntimeException(e);
		};
		

	}
	public ArrayList<Upgrade> getCurrentlyActive(){
		
		return current;
	}
	public ArrayList<Upgrade> getCurrentlyInactive(){
		
		return future;
	}
	

}
