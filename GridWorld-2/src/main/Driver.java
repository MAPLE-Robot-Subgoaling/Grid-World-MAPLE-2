package main;

public class Driver {

	public static void main(String[] args) {
		/**
		 * See old Driver for similar implementation
		 */
		
		GraphDomain environment = new GraphDomain();
	
		for(int i = 0; i < 10; i++){
			
			System.out.println("\t--Trial Number " + i +"--");
			
			int step = 0;
			while(!environment.isWinner(environment.findAgent())){
				System.out.println("\nStep " + step);
				environment.move();
				step++;
			}
			
			System.out.println("\nThe Agent has reached the goal state. Number of steps: " + step + "\n");
			environment.resetSim();
		}
	}

}
