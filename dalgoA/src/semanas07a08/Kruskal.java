package semanas07a08;

public class Kruskal {

	private static class Partition {
		private int totalNodes;
		private int[] prttn;
		private int[] heights;
		
		public Partition(int n) {
			this.totalNodes = n;
			this.prttn = new int[n];
			this.heights = new int[n];
		}
		
		public void initialize() {
			int n = this.totalNodes;
			for(int i=0; i<n; i++) {
				this.prttn[i] = i;
			}
		}
		
		/**
		 * Merges the two sets with labels _a_ and _b_.
		 * @param a
		 * @param b
		 */
		private void union(int a, int b) {
			int ha = heights[a];
			int hb = heights[b];
			int[] pp = this.prttn;

			if(ha >= hb) {
				pp[b] = a;
				if(hb <= ha) {
					ha++;
				}
			}
			else {
				pp[a] = b;
			}
		} 
		
		/**
		 * Finds the root/label of the set containing _a_.
		 * @param a
		 */
		private int find(int a) {
			int[] pp = this.prttn;
			int result = pp[a];
			while(result != pp[result]) {
				result = pp[result];
			}
			return result;
		}
		
	}
	
	private static class Edge {
		private int mask; // mask for edges using triangular numbers.
		private int weight;
		
		private boolean validMask;
		
		public Edge(int mask, int weight) {
			this.mask = mask;
			this.weight = weight;
		}
		
		private Edge(int[] nodes, int weight) {
			int col = nodes[0];
			int row = nodes[1];
			int mask = getTMask(col, row);
			this.mask = mask;
			this.weight = weight;
		}
		
		/**
		 * Computes the member nodes from the triangle mask.
		 * @return an int array of size 2 with the masked nodes.
		 */
		public int[] giveNodes() {
			int[] result = new int[2];
			int m = this.mask;
			int row = giveTriangleRow(m);
			int t = row*(row-1)/2;
			
			result[0] = m-t;
			result[1] = row;
			
			return result;
		}
		
	}
	
	/**
	 * Undirected graph with weights.
	 * @author danielri1011101
	 *
	 */
	private static class Graph {
		int nodes;
		Edge[] edges;
		
		public Graph(int nodes, Edge[] edges) {
			this.nodes = nodes;
			this.edges = edges;
		}
	}
	
	public static Edge[] kruskal(Graph gg) {
		Edge[] ans = new Edge[gg.edges.length];
		Partition pp = new Partition(gg.nodes);
		pp.initialize();
		Edge[] edges = gg.edges;
		sortEdges(edges);
		int n = gg.nodes;
		int reach = 0; // stop at value n-1
		int i = 0; // index to traverse edges.
		
		while(reach < n-1 && i < edges.length) {
			Edge e = edges[i];
			int[] nds = e.giveNodes();
			int ca = pp.find(nds[0]);
			int cb = pp.find(nds[1]);
			
			if(ca != cb) {
				pp.union(ca, cb);
				ans[reach] = e;
				reach++;
			}
			i++;
		}
		return ans;
	}
	
	/**
	 * Insertion sort with edge weight as sorting criterion.
	 * @param es edges to be sorted.
	 */
	public static void sortEdges(Edge[] es) {
		for(int i=1; i < es.length; i++ ) {
			Edge e = es[i];
			int j = i-1;
			while(j>=0 && es[j].weight > e.weight) {
				es[j+1] = es[j];
				j--;
			}
			es[j+1] = e;
		}
	}
	
	private static int giveTriangleRow(int m) {
		int v = 1+8*m;
		int root = (int) Math.sqrt(v);
		int row = (1+root)/2;
		return row;
	}
	
	/**
	 * Gives the triangle mask associated to the edge
	 * {a,b} where _a_ and _b_ are different non-negative
	 * integers.
	 * @param a non-negative integer.
	 * @param b non-negative integer, greater than _a_.
	 * @return
	 */
	private static int getTMask(int a, int b) {
		int row = b;
		int col = a;
		int t = row*(row-1)/2;
		return t+a;
	}
	
	public static void main(String[] args) {
		int[][] es_ = new int[12][2];

		int[] e01_ = {0,1};
		es_[0] = e01_;

		int[] e03_ = {0,3};
		es_[1] = e03_;

		int[] e12_ = {1,2};
		es_[2] = e12_;

		int[] e25_ = {2,5};
		es_[3] = e25_;

		int[] e56_ = {5,6};
		es_[4] = e56_;

		int[] e36_ = {3,6};
		es_[5] = e36_;

		int[] e14_ = {1,4};
		es_[6] = e14_;

		int[] e34_ = {3,4};
		es_[7] = e34_;

		int[] e46_ = {4,6};
		es_[8] = e46_;

		int[] e45_ = {4,5};
		es_[9] = e45_;

		int[] e13_ = {1,3};
		es_[10] = e13_;

		int[] e24_ = {2,4};
		es_[11] = e24_;
		
		int[] ws = {1,4,2,6,3,4,4,3,7,8,6,5};
		
		Edge[] es = new Edge[12];
		
		for(int i=0; i < 12; i++) {
			es[i] = new Edge(es_[i],ws[i]);
		}
		
		sortEdges(es);
		
		System.out.println("The sorted edges are:");
		for(int i=0; i < 12; i++) {
			int[] nodes = es[i].giveNodes();
			System.out.print("{" + (nodes[0]+1) + ", " + (nodes[1]+1));
			System.out.println("}");
		}
		
	}

}
