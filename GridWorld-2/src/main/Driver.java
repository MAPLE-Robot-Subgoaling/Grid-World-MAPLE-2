package main;

public class Driver {

	public static void main(String[] args) {
		/**
		 * See old Driver for similar implementation
		 */

		GraphDomain graph = new GraphDomain();
		for(int i = 0; i <= 3; i++){ //Runs three trials
			System.out.println("Episode Number: " + i + "\n");
			int step = 0;
			
			while(!graph.isWinner(graph.findAgent())){
				System.out.println("Step #: " + step);
				graph.move(graph.findAgent());
				step++;
			}
			System.out.println("\nThe Agent has reached the goal state. Number of steps: " + step + "\n");
			graph.resetSim();
		}
	}
}
