package org.sync.bullseye;

public class Upgrade {
	String name;
	int cost;
	
	public Upgrade(String name, int cost){
		this.name=name;
		this.cost=cost;
	}
	public String getName(){
		return name;
	}
	public int getCost(){
		return cost;
	}

}
