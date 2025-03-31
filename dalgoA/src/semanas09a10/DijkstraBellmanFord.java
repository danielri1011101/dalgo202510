package semanas09a10;

public class DijkstraBellmanFord {
	
	private static class Edge {

		private int source;
		private int target;
		private int weight;
		
		public Edge(int source, int target, int weight) {
			this.source = source;
			this.target= target;
			this.weight = weight;
		}

	}
	
	private static class DiGraph {

		private int order;
		private Edge[] edges;
		
		public DiGraph(Edge[] edges, int order) {
			this.order = order;
			this.edges = edges;
		}

	}
	
	public static int[] bellmanFord(DiGraph gg, int source) {
		
	}

}
