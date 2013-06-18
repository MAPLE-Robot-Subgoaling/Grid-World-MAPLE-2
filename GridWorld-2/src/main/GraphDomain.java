package main;

import java.util.*;

//Create the actual domain
//Create the states(Node) and associate with actions
public class GraphDomain {
	
	//The Environment of Nodes
	private ArrayList<Node> nodes = new ArrayList<Node>();;
			
	
	//Constructor: create the 9 states and the associated actions
	public GraphDomain(){
		nodes.add(new Node("s0", -1));
		nodes.add(new Node("s1", -1));
		nodes.add(new Node("s2", -1));
		nodes.add(new Node("s3", -1));
		nodes.add(new Node("s4", -1));
		nodes.add(new Node("s5", -1));
		nodes.add(new Node("s6", -1));
		nodes.add(new Node("s7", -1));
		nodes.add(new Node("s8", 1000));
		
		//sets up the start state and the goal state 
		nodes.get(0).setHere(true);
		nodes.get(nodes.size() - 1).setGoal(true);
		
		//Sets up the Action List for each Node
		this.initializeActions();
	}
	
	private void initializeActions(){
		//Action Probabilities
		HashMap<String, Double> northProb = new HashMap<String, Double>();
		HashMap<String, Double> southProb = new HashMap<String, Double>();
		HashMap<String, Double> eastProb = new HashMap<String, Double>();
		HashMap<String, Double> westProb = new HashMap<String, Double>();
		
		//North action
		northProb.put("North", 0.70);
		northProb.put("South", 0.8);
		northProb.put("East", 0.9);
		northProb.put("West", 1.0);
						
		//South action
		southProb.put("North", 0.1);
		southProb.put("South", 0.8);
		southProb.put("East", 0.9);
		southProb.put("West", 1.0);
		
		//East Action
		eastProb.put("North", 0.1);
		eastProb.put("South", 0.2);
		eastProb.put("East", 0.9);
		eastProb.put("West", 1.0);
				
		//West Action
		westProb.put("North", 0.1);
		westProb.put("South", 0.2);
		westProb.put("East", 0.3);
		westProb.put("West", 1.0);
		
		//s0
		nodes.get(0).addAction("North", null, Math.random() * 12, northProb);
		nodes.get(0).addAction("South", nodes.get(3), Math.random() * 12, southProb);
		nodes.get(0).addAction("East",nodes.get(1), Math.random() * 12, eastProb);
		nodes.get(0).addAction("West", null, Math.random() * 12, westProb);
		
		//s1
		nodes.get(1).addAction("North", null, Math.random() * 12, northProb);
		nodes.get(1).addAction("South", nodes.get(4), Math.random() * 12, southProb);
		nodes.get(1).addAction("East",nodes.get(2), Math.random() * 12, eastProb);
		nodes.get(1).addAction("West", nodes.get(0), Math.random() * 12, westProb);
		
		//s2
		nodes.get(2).addAction("North", null, Math.random() * 12, northProb);
		nodes.get(2).addAction("South", nodes.get(5), Math.random() * 12, southProb);
		nodes.get(2).addAction("East", null, Math.random() * 12, eastProb);
		nodes.get(2).addAction("West", nodes.get(1), Math.random() * 12, westProb);
		
		//s3
		nodes.get(3).addAction("North", nodes.get(0), Math.random() * 12, northProb);
		nodes.get(3).addAction("South", nodes.get(6), Math.random() * 12, southProb);
		nodes.get(3).addAction("East",nodes.get(4), Math.random() * 12, eastProb);
		nodes.get(3).addAction("West", null, Math.random() * 12, westProb);
		
		//s4
		nodes.get(4).addAction("North", nodes.get(1), Math.random() * 12, northProb);
		nodes.get(4).addAction("South", nodes.get(7), Math.random() * 12, southProb);
		nodes.get(4).addAction("East",nodes.get(5), Math.random() * 12, eastProb);
		nodes.get(4).addAction("West", nodes.get(3), Math.random() * 12, westProb);
		
		//s5
		nodes.get(5).addAction("North", nodes.get(2), Math.random() * 12, northProb);
		nodes.get(5).addAction("South", nodes.get(8), Math.random() * 12, southProb);
		nodes.get(5).addAction("East", null, Math.random() * 12, eastProb);
		nodes.get(5).addAction("West", nodes.get(4), Math.random() * 12, westProb);
		
		//s6
		nodes.get(6).addAction("North", nodes.get(3), Math.random() * 12, northProb);
		nodes.get(6).addAction("South", null, Math.random() * 12, southProb);
		nodes.get(6).addAction("East", nodes.get(7), Math.random() * 12, eastProb);
		nodes.get(6).addAction("West", null, Math.random() * 12, westProb);
		
		//s7
		nodes.get(7).addAction("North", nodes.get(4), Math.random() * 12, northProb);
		nodes.get(7).addAction("South", null, Math.random() * 12, southProb);
		nodes.get(7).addAction("East", nodes.get(8), Math.random() * 12, eastProb);
		nodes.get(7).addAction("West", nodes.get(6), Math.random() * 12, westProb);
		
		//s8
		nodes.get(8).addAction("North", nodes.get(5), Math.random() * 12, northProb);
		nodes.get(8).addAction("South", null, Math.random() * 12, southProb);
		nodes.get(8).addAction("East", null, Math.random() * 12, eastProb);
		nodes.get(8).addAction("West", nodes.get(7), Math.random() * 12, westProb);
		
	}
	
	/**
	 * resetSim() - Prepares the Environment for the next Simulation
	 */
	public void resetSim(){
		nodes.get(0).setHere(true);
		nodes.get(nodes.size() - 1).setGoal(true);
	}
	
	public void move(int index){
		
		Node currentNode = nodes.get(index);	// Get Node
		double maxQValue = -1;
		Action action = null;	// Action with highest q-value after search
		for(Action listAction : currentNode.getListOfActions()){	// Search for highest q-value action
			if(listAction.getqValue() > maxQValue){
				maxQValue = listAction.getqValue();
				action = listAction;	// Set action to new highest q-value action
			}
		}
		
		if (action == null) // If action found
			System.out.println("Problem - No Action Found on move("
					+ String.valueOf(index) + ")");

		boolean withinRange = true; // Temporary
		Random random = new Random();

		// Check probability outcome, set withinRange

		if (withinRange) {
			double randomNumber = Math.random();
			currentNode.setHere(false);
			action.getEndState().setHere(true);

			// Update Q value

		} else {
			
			// Execute random action
			// Update new position and Q value
			
		}
		
		// Print transition results
		
		/**
		 * Steps for move:
		 * 1) Find the Agent and it's corresponding Node Location
		 * 2) Using the Action List inside of the Node, find the largest Q-Value and return it.
		 * 3) When you find the largest Q-Value, return a String Value of that corresponding Action
		 * 4) Node goes to the Action list and finds the matching action, end state, and probability table.
		 * 5) Node creates a random number to determine the outcome of that action.
		 * 	a) if within range:
		 * 		i) Agent Updates his position to the end state node
		 * 		ii) Agent Recomputes the new Q-Value and applies it to that action. 
		 *	b)if not within range:
		 *		i) Agent executes action determined by Random Variable
		 *		ii) Agent updates his position and the corresponding Q-Value for the action chosen.
		 *	6) Print out Transition results
		 *
		 * Note: if the end state is null, Agent doesn't move, but updates the Q-Value for that action
		 */
	}
	
	/**
	 * isWinner() - checks to see if agent is at goal state
	 * @param index - index of the node we are checking
	 * @return - true or false
	 */
	public boolean isWinner(int index){
		return nodes.get(index).isHere() && nodes.get(index).isGoal();
	}
	
	/**
	 * findAgent() - scans the arraylist for the agent location
	 * @return - index of the agent's locations
	 */
	public int findAgent(){
		for(int i = 0; i < nodes.size(); i++){
			if(nodes.get(i).isHere())
				return i;
		}
		return -1;
	}
	

}
