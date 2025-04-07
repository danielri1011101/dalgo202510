package semanas09a11;

import java.util.Arrays;

public class FlowNetwork {
	
	private static class Edge {

		private int start;
		private int end;
		private int capacity;
		
		public Edge(int start, int end, int capacity) {
			this.start = start;
			this.end= end;
			this.capacity = capacity;
		}

	}
	
	private static class Queue {
		private int[] qArr;
		private int maxLength;
		private int head;
		private int tail;
		
		public Queue(int m) {
			this.maxLength = m;
			this.qArr = new int[m];
			this.head = 0;
			this.tail = 0;
		}
		
		public Queue() {
			this(1 << 8);
		}
		
		public void add(int v) {
			this.qArr[this.tail] = v;
			this.tail++;
		}
		
		public int poll() {
			int ans = this.qArr[this.head];
			this.head++;
			return ans;
		}
		
		public boolean isEmpty() {
			return this.head >= this.tail;
		}
		
		public void reset() {
			if(this.head == this.tail) {
				this.head = 0;
				this.tail = 0;
			}
		}
		
	}
	
	private static class DiGraph {

		private int order;
		private Edge[] edges;
		
		public DiGraph(Edge[] edges, int order) {
			this.order = order;
			this.edges = edges;
		}
		
		public int getOrder() {
			return this.order;
		}
		
		public Edge[] getEdges() {
			return this.edges;
		}

	}
	
	private static class Network extends DiGraph {
		private int source;
		private int sink;
		public Network (Edge[] edges, int order) {
			super(edges, order);
			this.source = 0;
			this.sink = order-1;
		}
	}
	
	public static int[][] capacityMatrix(Network xx) {
		int n = xx.getOrder();
		Edge[] ees = xx.getEdges();
		int[][] ans = new int[n][n];
		for(Edge e : ees) {
			int i = e.start;
			int j = e.end;
			ans[i][j] = e.capacity;
		}
		return ans;
	}
	
	/**
	 * Breadth-First search Ford-Fulkerson.
	 * @param xx flow network.
	 * @return maximum flow.
	 */
	public static int fordFulkerson(Network xx) {
		int source = xx.source;
		int sink = xx.sink;
		int n = xx.getOrder();
		
		int maxFlow = 0;
		
		int[] parent = new int[n];
		
		int[][] capacity = capacityMatrix(xx);
		int[][] residuals = capacity.clone();
		
		while(bfs(xx,residuals,parent)) {
			int pathFlow = Integer.MAX_VALUE;
			
			// Find bottleneck capacity
			for(int v = sink; v != source; v = parent[v]) {
				int u = parent[v];
				if(residuals[u][v] < pathFlow) {
					pathFlow = residuals[u][v];
				}
			}
			
			// Update residual capacities and contribute to flow.
			for(int v = sink; v != source; v = parent[v]) {
				int u = parent[v];
				residuals[u][v] -= pathFlow;
			}
			maxFlow += pathFlow;
		}
		
		return maxFlow;
	}
	
	public static boolean bfs(Network xx, int[][] residuals, int[] parent) {
		int n = xx.getOrder();
		int source = xx.source;
		int sink = xx.sink;

		boolean[] visited = new boolean[n];
		visited[source] = true;
		
		Queue qq = new Queue();
		qq.add(source);
		
		while(!qq.isEmpty()) {
			int u = qq.poll();
			
			for(int v=0; v < n; v++) {
				if(!visited[v] && residuals[u][v] > 0) {
					if(v==sink) {
						parent[v] = u;
						return true;
					}
					qq.add(v);
					parent[v] = u;
					visited[v] = true;
				}
			}
		}
		return false;
	}
	
	public static int originalFordFulkerson(Network xx) {
		int[][] capacity = capacityMatrix(xx);
		int[][] residuals = capacity.clone();
		int source = xx.source;
		int sink = xx.sink;
		int n = xx.getOrder();
		
		boolean[] visited = new boolean[n];
		
		int maxFlow = 0;
		
		int pathFlow;
		
		while((pathFlow = dfs(xx, source, visited, residuals,Integer.MAX_VALUE))>0) {
			Arrays.fill(visited, false);
			maxFlow += pathFlow;
		}
		
		return maxFlow;
	}
	
	private static int dfs(Network xx,int u, boolean[] visited,
			int[][] residuals, int flow) {
		int source = xx.source;
		int sink = xx.sink;
		int n = xx.getOrder();
		
		if(u == sink) {
			return flow;
		}
		
		visited[u] = true;
		
		for(int v=0; v < n; v++) {
			if(!visited[v] && residuals[u][v] > 0) {
				int currentFlow = Math.min(flow, residuals[u][v]);
				int pathFlow = dfs(xx, v, visited,residuals, currentFlow);
				
				if(pathFlow > 0) {
					residuals[u][v] -= pathFlow;
					return pathFlow;
				}
			}
		}
		return 0;
	}
	
	public static void fillArray(int[] aa, int value) {
		for(int i=0; i < aa.length; i++) {
			aa[i] = value;
		}
	}
	
}
