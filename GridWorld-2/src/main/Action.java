package main;

import java.util.HashMap;

/**
 * 
 * @author Bhuvana Bellala; Tenji Tembo
 * Class Details:
 * 	This class is designed to contain the action for each state
 * Attributes:
 * 	probabilites: still needs to finalized as to hold whether a string and a double
 * or an action and double;
 * 	For now string sounds better because with action comes extra complexity
 */

public class Action {

	private String name;
	protected HashMap<String, Double> probablities;
	//if this particular action is taken at the given
	//state where would the agent end up in
	//may be compare the endState and the start state names 
	//to determine the reward
	private Node endState;
	private double qValue;
	
	public Action(String name,Node endHere, double qValue, HashMap<String, Double> probabilities){
		this.name = name;
		this.endState = endHere;
		this.qValue = qValue;
		this.probablities = probabilities;
	}
	
	//Shallow Copy constructor
	public Action(Action other){
		this.name = other.name;
		this.endState = other.endState;
		this.qValue = other.qValue;
		this.probablities = other.probablities;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Node getEndState() {
		return endState;
	}

	public void setEndState(Node endState) {
		this.endState = endState;
	}

	public double getqValue() {
		return qValue;
	}

	public void setqValue(double qValue) {
		this.qValue = qValue;
	}
	
	public HashMap<String, Double> getProbs(){
		return probablities;
	}
}
