package org.sync.bullseye;

public class InProgressUpgrade {
	String name;
	int cost;
	int time;
	int timeCompleted;
	
	public Upgrade(String name, int cost,int time,int timeCompleted){
		this.name=name;
		this.cost=cost;
		this.time=time;
		this.timeCompleted=timeCompleted;
	}
	public String getName(){
		return name;
	}
	public int getCost(){
		return cost;
	}
	public in getTimeCompleted()
	{
		return timeCompleted;
	}

}
