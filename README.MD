Agent enters the State (Node)
Agent pulls up the Q-Value List for that State
Agent Picks a Q-Value (Highest) and tells the State the corresponding action
State pulls up the Action List and finds the Action
Action list has 4 Actions for this particular domain
  N, S, E, W
State finds the “golden range” for the action to run correctly
  Generate a Random Number, and checks the range according to that action
  Finds the range that matches and checks out the corresponding Next State
  Moves the Agent to that new State
  Agent then receives a reward based on the action done
Agent and/or State updates the Q-Value for that action chosen by agent. 

So if the agent chooses to go north from (S4), it should end up in (S1). 
The action north contains a chart which shows it having an 70% of it going 
north (0.0 -> 0.7) and 10% for east, south, and west each. The State creates 
a random variable and the value is 0.46. Therefore the State looks for a match 
in the corresponding ranges (0.0 -> 0.7) and return the Outcome (S1). The agent 
is transferred from (S4) to (S1) and returns the Reward and the State to the agent. 
The Agent updates its reward value and the State S4 updates the Q-Value for North.
