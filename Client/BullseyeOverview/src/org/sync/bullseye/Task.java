package org.sync.bullseye;

public class Task {
	String name;
	int cost;
	int time;
	int reward;
	public Task(String name, int cost,int time,int reward){
		this.name=name;
		this.cost=cost;
		this.time=time;
		this.reward=reward;
	}
	public String getName(){
		return name;
	}
	public int getCost(){
		return cost;
	}
	public int getTime(){
		return time;
	}
	public int getReward()
	{
		return reward;
	}

}
