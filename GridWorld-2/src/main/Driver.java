package main;

public class Driver {

	public static void main(String[] args) {
		/**
		 * See old Driver for similar implementation
		 */
		
		GraphDomain environment = new GraphDomain();
	
		int step = 0;
		while(!environment.isWinner(environment.findAgent())){
			System.out.println("Step " + step);
			environment.move();
			step++;
		}
		
		System.out.println("The Agent has reached the goal state");
	}

}
