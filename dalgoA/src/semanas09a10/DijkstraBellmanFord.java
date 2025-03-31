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
		int[] distances = new int[gg.order];
		fillArray(distances, Integer.MAX_VALUE);
		distances[source] = 0;
		
		for(int i=1; i < gg.order; i++) {
			for(Edge edge : gg.edges) {
				int u = edge.source;
				int v = edge.target;
				int weight = edge.weight;
				
				if(distances[u] < Integer.MAX_VALUE && distances[u] + weight <
						distances[v]) {
					distances[v] = distances[u] + weight;
				}
			}
		}
		
		// check for negative-weight cycles.

		for(Edge edge : gg.edges) {
			int u = edge.source;
			int v = edge.target;
			int weight = edge.weight;
			
			if(distances[u] < Integer.MAX_VALUE && distances[u] + weight < 
					distances[v]) {
				System.out.println("This digraph contains negative cycles.");
			}
		}

		return distances;
	}
	
	public static void fillArray(int[] aa, int value) {
		for(int i=0; i < aa.length; i++) {
			aa[i] = value;
		}
	}
	
}
