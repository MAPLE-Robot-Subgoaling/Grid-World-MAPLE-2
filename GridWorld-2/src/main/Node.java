package main;

import java.util.ArrayList;

public class Node {
	
	private ArrayList<Action> listOfActions;
	private int reward;
	private String name;
	private boolean isHere;
	private boolean isGoal;
	
	public Node(String name, int reward){
		
		this.name = name;
		this.reward = reward;
		this.isHere = false;
		this.isGoal = false;
		this.listOfActions = new ArrayList<Action>();
		
	}

}
