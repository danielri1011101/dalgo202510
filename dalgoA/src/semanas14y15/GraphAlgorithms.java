package semanas14y15;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GraphAlgorithms {

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
		
		private void badUnion(int a, int b) {
			int[] pp = this.prttn;
			if(a < b) {
				pp[b] = a;
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
	
	
	
	public static Edge[] kruskal(Graph gg) {
		Edge[] ans_ = new Edge[gg.edges.length];
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
				ans_[reach] = e;
				reach++;
			}
			i++;
		}
		
		Edge[] ans = new Edge[reach];
		
		for(int j=0; j < reach; j++) {
			ans[j] = ans_[j];
		}

		return ans;
	}
	
	/**
	 * Approximate algorithm for the metric tsp problem.
	 * @param g _a complete graph_
	 * @return 2-approximate optimal hamiltonian cycle.
	 */
	public static int[] approxMTSP(Graph g) {
		int[] ans = new int[g.nodes];
		Edge[] mst = kruskal(g);
		adjacencySort(mst);
		// TODO dfs to recover approximate cycle.
		return ans;
	}
	
	/**
	 * Receives a list of edges that form a tree, and sorts it so that
	 * all [0..k]-th sublists form trees.
	 * @param es _list of edges_, they form a tree.
	 */
	private static void adjacencySort(Edge[] es) {
		int m = es.length;
		int[] p = new int[m+1]; // partition
		
		// initialize partition as totally disjoint.
		for(int i=0; i<m+1; i++) {
			p[i] = i;
		}
		
		int[] nds = es[0].giveNodes();
		
		int u0 = nds[0];
		int v0 = nds[1];
		
		// initializes connected component of "root" node.
		p[v0] = u0;
		
		for(int j=1; j<m; j++) {
			nds = es[j].giveNodes();
			int u = nds[0];
			int v = nds[1];
			
			if(p[v] < u) {
				p[u] = v;
			}
			else {
				p[v] = u;
			}
			
			int a = 0;
			
			while(!belongs(p,u,u0)) {
				a++;
				u = es[j+a].giveNodes()[0];
			}
			// Now j+a is the index where the next adjacent edge lies.
			
			// Progress the partition.
			v = es[j+a].giveNodes()[1];
			
			if(p[v] < u) {
				p[u] = v;
			}
			else {
				p[v] = u;
			} 
			
			// Swap the edges.
			Edge ed = es[j];
			es[j] = es[j+a];
			es[j+a] = ed;
		}
		
	}
	
	/**
	 * 
	 * @param p partition
	 * @param a node
	 * @param h head
	 * @return a -->* h in partition p.
	 */
	private static boolean belongs(int[] p, int a, int h) {
		int i = 0;
		int b = a;
		while(p[b] != b && i < p.length) {
			b = p[b];
			i++;
		}
		return b == h;
	}
	
	// TODO: Implement treewalk as dfs using a queue.
	
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
	
	
	/**
	 * Builds the graph from the input file.
	 * First line of input is the graph's number of nodes,
	 * followed by the data for the edges, one per line.
	 * It consists of three non-negative integers, the second and third
	 * being strictly positive. The integers are separated by
	 * a single blank space. The first two are the nodes connected
	 * by the edge, and the third integer is the weight. The first node
	 * should be the smaller one, so that computing the mask is easier.
	 * @param input
	 * @return
	 * @throws IOException 
	 */
	public static Graph setUp(String input) throws IOException {
		FileReader fr = new FileReader(input);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		int nodes = Integer.parseInt(line);
		int max = nodes*(nodes-1)/2;
		Edge[] es_ = new Edge[max];
		int c = 0; // edge count.
		line = br.readLine();
		while(line != null && c < max) {
			String[] nrs = line.split("\\s+");
			int[] node = new int[2];
			node[0] = Integer.parseInt(nrs[0]);
			node[1] = Integer.parseInt(nrs[1]);
			int weight = Integer.parseInt(nrs[2]);
			Edge e = new Edge(node, weight);
			es_[c] = e;
			c++;
			line = br.readLine();
		}
		Edge[] es = new Edge[c];
		for(int i=0; i<c; i++) {
			es[i] = es_[i];
		}
		Graph ans = new Graph(nodes, es);
		return ans;
	}
	
	/**
	 * The main method.
	 * @param args
	 */
	public static void main(String[] args) {
		String input = "/home/profesor/git/dalgo202510/dalgoA/"
				+ "src/semanas07a08/kInput1.txt";
		try {
			Graph gg = setUp(input);
			Edge[] mst = kruskal(gg); // a minimum spanning tree of gg.
			System.out.println("The edges of a minimum spanning"
					+ " tree for G are:");
			for(int i=0; i < mst.length; i++) {
				Edge e = mst[i];
				int[] nodes = e.giveNodes();
				System.out.print("{" + nodes[0] + ", " + nodes[1]);
				System.out.println("}");
			}
			
			System.out.println();
			
			System.out.println("Tree edges are now listed preserving"
					+ " adjacency:");
			
			adjacencySort(mst);
			
			for(int i=0; i < mst.length; i++) {
				Edge e = mst[i];
				int[] nodes = e.giveNodes();
				System.out.print("{" + nodes[0] + ", " + nodes[1]);
				System.out.println("}");
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}
