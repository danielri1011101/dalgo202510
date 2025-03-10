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
		private int totalNodes;
		private int mask; // mask for edges using triangular numbers.
		private int weight;
		
		private boolean validMask;
		
		public Edge(int nodes, int mask, int weight) {
			this.totalNodes = nodes;
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
			int v = 1 + 8*m; //discriminant.
			int root = (int) Math.sqrt(v);
			int row = (1+root)/2;
			
			result[0] = m-row*(row-1)/2;
			result[1] = row;
			
			return result;
		}
	}
	
	public static void main(String[] args) {
		int var = (int) Math.sqrt(26);
		boolean b = var == Math.sqrt(26);
		System.out.println(b);
	}

}
