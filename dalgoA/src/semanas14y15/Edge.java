package semanas14y15;

public class Edge {
	
	int mask; // mask for edges using triangular numbers.
	int weight;
	
	boolean validMask;
	
	public Edge(int mask, int weight) {
		this.mask = mask;
		this.weight = weight;
	}
	
	public Edge(int[] nodes, int weight) {
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
	
	public static int giveTriangleRow(int m) {
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
	public static int getTMask(int a, int b) {
		int row = b;
		int col = a;
		int t = row*(row-1)/2;
		return t+a;
	}

}
