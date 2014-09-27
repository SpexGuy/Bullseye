package org.sync.bullseye;

public class Upgrade {
	String name;
	int cost;
	int time;
	
	public Upgrade(String name, int cost,int time){
		this.name=name;
		this.cost=cost;
		this.time=time;
	}
	public String getName(){
		return name;
	}
	public int getCost(){
		return cost;
	}

}
