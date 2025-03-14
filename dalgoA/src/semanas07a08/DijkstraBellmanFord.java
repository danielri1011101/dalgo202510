package semanas07a08;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DijkstraBellmanFord {

	/**
	 * For directed graphs, the mask can take values in
	 * [0..n*(n-1)/2-1] and [n*(n+1)/2..n^2-1], where n is the
	 * order of the graph.
	 * For the values in between or above, the nodes returned
	 * don't make sense for a graph with the order as specified.
	 * We therefore add a new field to store the graph's order.
	 * @author danielri1011101
	 *
	 */
	private static class Edge {
		private int graphOrder;
		private int mask; 
		private int weight;
		
		private boolean validMask;
		
		public Edge(int graphOrder, int mask, int weight) {
			this.graphOrder = graphOrder;
			this.mask = mask;
			this.weight = weight;
			
			int n = this.graphOrder;
			
			this.validMask = 0 <= this.mask && this.mask < n*(n-1)/2 ||
					n*(n+1)/2 <= this.mask && this.mask < n*n;
		}
		
		public Edge() {
		}
		
		/**
		 * @TODO: Edge constructor with node pair.
		 */
		private Edge(int[] nodes, int weight, int graphOrder) {
			this.graphOrder = graphOrder;
			int a = nodes[0];
			int b = nodes[1];
			int mask = getMask(a,b,graphOrder);
			this.mask = mask;
			this.weight = weight;
		}
		
		/**
		 * Computes the member nodes from the triangle mask.
		 * @return an int array of size 2 with the masked nodes.
		 * @TODO: modify accordingly.
		 */
		public int[] giveNodes() {
			int[] result = new int[2];
			int mask = this.mask;
			int n = this.graphOrder;
			if(mask >= n*(n+1)/2) {
				int lowMask = mask - n*(n-1)/2;
				int lRow = giveLowRow(lowMask);
				int triangle = lRow*(lRow-1)/2;
				result[0] = lRow;
				result[1] = lowMask - triangle;
			}
			else {
				int row = giveLowRow(mask);
				int triangle = row*(row-1)/2;
				result[0] = mask-triangle;
				result[1] = row;
			}
			return result;
		}
		
		/**
			 * Returns the row to which the mask m belongs. It is assumed
			 * to represent a lower-triangular edge.
			 * @param m \in [0..n*(n-1)/2-1]
			 * @return
			 */
			private static int giveLowRow(int m) {
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
			private static int getLowMask(int a, int b) {
				int row = b;
				int col = a;
				int triangle = row*(row-1)/2;
				return triangle + col;
			} 
			
			/**
			 * Returns the mask of the edge <a,b>. They must be
			 * distinct non-negative integers.
			 * Makes perfect sense as a non-static method, since the
			 * field _graphOrder_ is accessed to assign the edge as
			 * upper-triangular.
			 * @param a _non-negative integer_
			 * @param b _non-negative integer, different from a_
			 * @return
			 */
			private static int getMask(int a, int b, int order) {
				if(a<b) {
					return getLowMask(a, b);
				}
				else {
					int n = order;
					int lowMask = getLowMask(b, a);
					return lowMask + n*(n+1)/2;
				}
			}
		
	}
	
	/**
	 * Directed graph with weights.
	 * @author danielri1011101
	 *
	 */
	private static class DiGraph {

		private int order; // number of nodes.
		private Edge[] edges;
		
		public DiGraph(int n, Edge[] edges) {
			this.order = n;
			this.edges = edges;
		}
		
		/**
		 * The edges, by construction of the graph
		 * @return The matrix of DIRECT distances between any two nodes
		 * of the graph.
		 */
		public int[][] distanceMatrix() {
			int n = this.order;
			Edge[] es = this.edges;
			boolean[] visited = new boolean[n*n];
			int[][] result = new int[n][n];
			for(int c=0; c < es.length; c++) {
				Edge e = es[c];
				int[] pair = e.giveNodes();
				int dist = e.weight;
				int i = pair[0];
				int j = pair[1];
				result[i][j] = dist;
				visited[e.mask] = true;
			}
			for(int i=0; i < n; i++) {
				for(int j=0; j < n; j++) {
					int m = Edge.getMask(i, j, n);
					boolean v = visited[m];
					if(!v) {
						result[i][j] = Integer.MAX_VALUE;
					}
				}
			}
			return result;
		}

	}
	
	/**
	 * Insertion sort with edge weight as sorting criterion.
	 * @param es _edges to be sorted_.
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
	
	public static int[] dijkstra(DiGraph dgg, int source) {
		int n = dgg.order;
		int s = source;
		int[] result = new int[n-1];
		int[][] dss = dgg.distanceMatrix();
		for(int i=0; i < n; i++) {
			if(i < s) {
				result[i] = dss[s][i]; 
			}
			else if(i > s) {
				result[i-1] = dss[s][i];
			}
		}
		int full = (1 << n)-1;
		int cc = full ^ (1 << s);
		int low = 0;
		while(cc != 0) {
			int minIdx = log2(cc);
			for(int i=low; i < log2(cc); i++) {
				if(((cc & (1<<i)) != 0) &&
						result[i] < result[minIdx]) {
					minIdx = i;
				}
			}
			cc ^= minIdx;
			if(minIdx == low) {
				low++;
			}
			if(cc == 0) {
				continue;
			}
			for(int i=low; i < log2(cc); i++) {
				if((cc & (1<<i)) != 0) {
					result[i] = Math.min(result[i],
							result[minIdx] + dss[minIdx][i]);
				}
			}
		}
		return result;
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
	public static DiGraph setUp(String input) throws IOException {
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
			Edge e = new Edge();
			es_[c] = e;
			c++;
			line = br.readLine();
		}
		Edge[] es = new Edge[c];
		for(int i=0; i<c; i++) {
			es[i] = es_[i];
		}
		DiGraph ans = new DiGraph(nodes, es);
		return ans;
	}
	
	/**
	 * Floor of the base 2 logarithm of the integer n.
	 * @param n
	 * @return
	 */
	private static int log2(int n) {
		return (int) (Math.log(n)/Math.log(2));
	}
	
	/**
	 * The main method.
	 * @param args
	 */
	public static void main(String[] args) {
		boolean[] bb = new boolean[1];
		System.out.println(bb[0]);
		int var = (int) (Math.log(85)/Math.log(2));
		System.out.println(var);
		System.out.println(log2(63));
	}

}
