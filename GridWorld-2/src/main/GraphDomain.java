package main;

import java.util.*;

//Create the actual domain
//Create the states(Node) and associate with actions
public class GraphDomain {

	//The Environment of Nodes
	private ArrayList<Node> nodes = new ArrayList<Node>();
	private static final double LEARNINGRATE = 0.99;
	private static final double DISCOUNTFACTOR = 0.95;
	private static final double WALLREWARD = -5;


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
		northProb.put("North", 1.0);
		northProb.put("South", 0.3);
		northProb.put("East", 0.2);
		northProb.put("West", 0.1);

		//South action
		southProb.put("North", 1.0);
		southProb.put("South", 0.8);
		southProb.put("East", 0.2);
		southProb.put("West", 0.1);

		//East Action
		eastProb.put("North", 1.0);
		eastProb.put("South", 0.9);
		eastProb.put("East", 0.8);
		eastProb.put("West", 0.1);

		//West Action
		westProb.put("North", 1.0);
		westProb.put("South", 0.9);
		westProb.put("East", 0.8);
		westProb.put("West", 0.7);

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
		nodes.get(nodes.size() - 1).setHere(false);
		nodes.get(nodes.size() - 1).setGoal(true);
	}

	public void move(int index){

		Node currentNode = nodes.get(index);	// Get Node
		double maxQValue = -100;
		Action action = null;	// Action with highest q-value after search
		for(Action listAction : currentNode.getListOfActions()){	// Search for highest q-value action
			if(listAction.getqValue() > maxQValue){
				maxQValue = listAction.getqValue();
				action = listAction;	// Set action to new highest q-value action
			}
		}
		System.out.print("The agent chose to go " + action + " from " + currentNode);
		System.out.println(". This action has a q-value of " + action.getqValue());

		boolean withinRange = true; // Temporary

		// Check probability outcome, set withinRange
		String actionShouldTake = null;
		double randomNumber = Math.random();

		if (withinRange) {
			HashMap<String, Double> probabilities= action.getProbablities();
			for(Map.Entry<String, Double> entry: probabilities.entrySet()){
				if(randomNumber <= entry.getValue()){
					System.out.println("The ranndom number is " + randomNumber + ". The value is " +entry.getValue());
					actionShouldTake = entry.getKey();
					break;
				}
			}
		}

		for(Action listAction : currentNode.getListOfActions()){	// Search for highest q-value action
			if(listAction.getName().equals(actionShouldTake)){
				System.out.println("The agent actually went "+ listAction);
				if(listAction.getEndState() == null){
					System.out.println("You hit a wall");		
					//Update q-values
					Double qNew = maxQValue + LEARNINGRATE * (WALLREWARD + DISCOUNTFACTOR *(maxQValue - maxQValue));
					action.setqValue(qNew);
					System.out.println("The updated qvalue for action " + action + " is " + action.getqValue() + "\n");
				}else{
					System.out.println("The agent ended up in " + listAction.getEndState());
					currentNode.setHere(false);
					listAction.getEndState().setHere(true);

					//Update q-value
					//Find the maximum qvalue of the new state
					double endStatemaxQValue = -100;
					for(Action actionList : listAction.getEndState().getListOfActions()){	// Search for highest q-value action
						if(actionList.getqValue() >endStatemaxQValue){
							endStatemaxQValue = actionList.getqValue();
						}
					}
					Double qNew = maxQValue + LEARNINGRATE * (listAction.getEndState().getReward() + DISCOUNTFACTOR *(endStatemaxQValue - maxQValue));
					action.setqValue(qNew);
					System.out.println("The updated qvalue for action " + action + " is " + action.getqValue() + "\n");
				}
			} 
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
