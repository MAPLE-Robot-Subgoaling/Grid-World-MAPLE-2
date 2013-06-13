package main;

import java.util.HashMap;

public class Action {
	
	private String name;
	protected HashMap<String, Double> probablities;
	//if this particular action is taken at the given
	//state where would the agent end up in
	//may be compare the endState and the start state names 
	//to determine the reward
	private Node endState;
	private double qValue;
	
	public Action(String name,Node endHere, double qValue){
		this.name = name;
		this.endState = endHere;
		this.qValue = qValue;
		this.probablities = new HashMap<String, Double>();
	}
	
	//Shallow Copy constructor
	public Action(Action other){
		this.name = other.name;
		this.endState = other.endState;
		this.qValue = other.qValue;
		this.probablities = other.probablities;
	}
}
