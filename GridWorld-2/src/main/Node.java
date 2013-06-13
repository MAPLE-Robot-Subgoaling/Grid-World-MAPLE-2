package main;

import java.util.ArrayList;

/**
 * 
 * @author Bhuvana Bellala
 * Class Details:
 * 	This class is designed to contain the Node for the Graph ADT
 * Attributes:
 * 	listOfActions - Action objects that can be carried out in a state
 */

public class Node {
	
	//list of attributes for the class
	private ArrayList<Action> listOfActions;
	private int reward;
	private String name;
	private boolean isHere;
	private boolean isGoal;
	
	//Constructor: Initializes the attributes
	public Node(String name, int reward){
		
		this.name = name;
		this.reward = reward;
		this.isHere = false;
		this.isGoal = false;
		this.listOfActions = new ArrayList<Action>();
		
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHere() {
		return isHere;
	}

	public void setHere(boolean isHere) {
		this.isHere = isHere;
	}

	public boolean isGoal() {
		return isGoal;
	}

	public void setGoal(boolean isGoal) {
		this.isGoal = isGoal;
	}
	
	/**toString() - print the node
	 */
	public String toString(){
		
		String stateInfo = "";
		stateInfo += "The state is " + this.name;
		return stateInfo;
	}

}
