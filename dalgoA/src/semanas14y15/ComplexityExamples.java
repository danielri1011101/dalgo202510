package semanas14y15;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ComplexityExamples {

	
	
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
			int mask = this.mask;
			int row = giveTriangleRow(mask);
			int triangle = row*(row-1)/2;
			
			result[0] = mask-triangle;
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
		int nodes; // Number of nodes, aka. order of graph.
		Edge[] edges;
		
		public Graph(int nodes, Edge[] edges) {
			this.nodes = nodes;
			this.edges = edges;
		}
		
		public int getMaxWeight() {
			Edge[] es = this.edges;
			int ans = es[0].weight;
			for(Edge e : es) {
				if(e.weight > ans) {
					ans = e.weight;
				}
			}
			return ans;
		}
		
		public void removeEdge(Edge e) {
			Edge[] newEdges = new Edge[this.edges.length-1];
			int i = 0;
			boolean found = false;
			for(Edge ed : this.edges) {
				if(ed.equals(e)) {
					found = true;
				}
				else if(i < newEdges.length){
					newEdges[i] = ed;
					i++;
				}
			}
			if(!found) return;
			this.edges = newEdges;
		}
		
		public void addEdge(Edge e) {
			Edge[] newEdges = new Edge[this.edges.length+1];
			int i = 0;
			for(Edge ed : this.edges) {
				if(ed.equals(e)) return;
				newEdges[i] = ed;
				i++;
			}
			newEdges[i] = e;
			this.edges = newEdges;
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
	
	/**
	 * Dummy method, to pretend we have it.
	 * @param gg
	 * @param totalWeight
	 * @return whether or not _gg_ contains a simple path of weight
	 * at most _totalWeight_
	 */
	public static boolean checkTSPD(Graph gg, int totalWeight) {
		return false;
	}
	
	public static Edge[] computeTSP(Graph gg) {

		// initialize path weight 
		int pathWeight = gg.getMaxWeight() * gg.edges.length;
		
		// The graph doesn't contain simple cycles.
		if(!checkTSPD(gg, pathWeight)) return null;
		
		// decrease path weight to detect the minimum possible.
		while(checkTSPD(gg, pathWeight)) {
			pathWeight--;
		}

		pathWeight++;
		
		Edge[] edgesCopy = gg.edges.clone();
		
		// remove redundant edges until a single optimal path remains.
		for(Edge e : edgesCopy) {
			gg.removeEdge(e);
			if(!checkTSPD(gg, pathWeight)) gg.addEdge(e);
		}
		
		// Now the edges of gg make up a single optimal hamiltonian cycle.
		return gg.edges;
	}
	
	/**
	 * 
	 * @param gg
	 * @return Wrong. Currently returns an array of true_s, I think.
	 */
	public static boolean[] approxVertexCover(Graph gg) {
		boolean[] cover = new boolean[gg.nodes];
		int esm = 1 << gg.edges.length; // Edge set bitmask.
		Edge[] es = gg.edges.clone();
		while(esm != 0) {
			for(Edge e : es) {
				int[] eNodes = e.giveNodes();
				int u = eNodes[0];
				int v = eNodes[1];
				esm ^= u;
				esm ^= v;
				cover[u] = true;
				cover[v] = true;
			}
		}
		return cover;
	}
	
	/**
	 * The main method.
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

}
