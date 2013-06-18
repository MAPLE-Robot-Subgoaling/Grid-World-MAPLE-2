package main;

public class Driver {

	public static void main(String[] args) {
		/**
		 * See old Driver for similar implementation
		 */

		GraphDomain graph = new GraphDomain();
		for(int i = 0; i <= 10; i++){
			graph.move(graph.findAgent());
		}
	}

}
