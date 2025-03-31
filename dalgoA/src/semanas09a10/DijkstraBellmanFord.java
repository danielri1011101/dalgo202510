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
	
	/**
	 * Sub-optimal but very readable.
	 * @param gg
	 * @param source
	 * @return
	 */
	public static int[] dijkstra(DiGraph gg, int source) {
		int n = gg.order;
		int[] distances = new int[n];
		fillArray(distances, Integer.MAX_VALUE);
		distances[source] = 0;
		boolean[] visited = new boolean[n];
		
		for(int i=0; i < n; i++) {
			
			// find closest node
			int u = -1;
			int minDist = Integer.MAX_VALUE;
			for(int v=0; v < n; v++) {
				if(!visited[v] && distances[v] < minDist) {
					minDist = distances[v];
					u = v;
				}
			}
			
			visited[u] = true;
			 
			if(u==-1) {
				break;
			}
			
			for(Edge edge : gg.edges) {
				if(edge.source == u) {
					int v = edge.target;
					int weight = edge.weight;
					int newDist = distances[u] + weight;
					if(newDist < distances[v]) {
						distances[v] = newDist;
					}
				}
			}
		}
		return distances;
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
				
				int newDist = distances[u] + weight;
				if(distances[u] < Integer.MAX_VALUE && newDist <
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
			
			int newDist = distances[u] + weight;
			if(distances[u] < Integer.MAX_VALUE && newDist < 
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
