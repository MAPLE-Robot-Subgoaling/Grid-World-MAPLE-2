package main;

public class Driver {

	public static void main(String[] args) {
		/**
		 * See old Driver for similar implementation
		 */

		GraphDomain graph = new GraphDomain();
		// for(int i = 0; i <= 10; i++){
		// graph.move(graph.findAgent());
		// }

		int steps = 0;
		int reward = 0;
		while (!graph.isWinner(graph.findAgent())) {
			graph.move(graph.findAgent());
			reward += graph.getNode(graph.findAgent()).getReward();
			steps++;
		}

		System.out.println("$$$ GOAL REACHED $$$ \n Steps Taken: "
				+ String.valueOf(steps) + "\n Total Reward: "
				+ String.valueOf(reward));

	}

}
