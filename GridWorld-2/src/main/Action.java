package main;

import java.util.Map;

public class Action {
	
	private String name;
	private Map<String, Double> probablities;
	//if this particular action is taken at the given
	//state where would the agent end up in
	//may be compare the endState and the start state names 
	//to determine the reward
	private Node endState;
	private double qValue;
}
