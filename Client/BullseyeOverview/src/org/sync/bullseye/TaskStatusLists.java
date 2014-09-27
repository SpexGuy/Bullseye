package org.sync.bullseye;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TaskStatusLists {
	
	private ArrayList<Task> current = new ArrayList<Task>();
	private ArrayList<Task> future = new ArrayList<Task>();
	private int numEmployees=-1;
	
	public TaskStatusLists(String serverResponse){
		

		try{
		JSONObject obj = new JSONObject(serverResponse);
		numEmployees = obj.getInt("employeesAvailable");
		JSONArray  availableArray = obj.getJSONArray("tasksAvailable");
		for(int i = 0 ; i < availableArray.length() ; i++)
		{
			JSONObject o = availableArray.getJSONObject(i);
			future.add(new Task(o.getString("name"),o.getInt("cost"),o.getInt("time"),o.getInt("reward")));
		}
		JSONArray a = obj.getJSONArray("tasksInProgress");
		
		for(int i = 0 ; i < a.length() ; i++)
		{
			JSONArray subA = a.getJSONArray(i);
			JSONObject o = (JSONObject) subA.get(1);
			current.add(new Task(o.getString("name"),o.getInt("cost"),o.getInt("time"),o.getInt("reward")));
		}
		
		}catch(JSONException e){
			throw new RuntimeException(e);
		};
		

	}
	public ArrayList<Task> getCurrentlyActive(){
		
		return current;
	}
	public ArrayList<Task> getCurrentlyInactive(){
		
		return future;
	}
	public int getNumFreeEmployees(){
		return numEmployees;
	}
	

}
