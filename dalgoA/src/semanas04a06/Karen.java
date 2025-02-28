package semanas04a06;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Karen {
	
	//-------------------------------------------------------------------------
	//----------------------- FIELDS -----------------------------------------
	//-------------------------------------------------------------------------

	private static int cases;

	/**
	 * Karen's world has size rank * rank.
	 */
	private static int rank;

	/**
	 * Coordinates of Karen's starting position.
	 */
	private static int karenX;

	private static int karenY;

	/**
	 * Number of beepers for Karen to collect.
	 */
	private static int beepers;

	/**
	 * Karen an the beeper's coordinates. These arrays must have length
	 * equal to 1 + _beepers_.
	 */
	private static int[] xCoords;

	private static int[] yCoords;

	/**
	 * Distance matrix. It's a square matrix of lenght 1 + _beepers_.
	 * The first node is Karen.
	 */
	private static int[][] distances;
	
	/**
	 * The matrix with the shortest paths, built bottom-up.
	 * it has 1 + _beepers_ rows and 2^_beepers_ columns.
	 * we're interested in gg[0][_beepers_ - 1]. 
	 */
	private static int[][] gg;
	
	//-------------------------------------------------------------------------
	//----------------------- METHODS -----------------------------------------
	//-------------------------------------------------------------------------

	/**
	 * Reads the input file in order to set up Karen's task.
	 * @param inputAddress
	 * @throws IOException 
	 */
	public static void setUp(String inputAddress) throws IOException {
		FileReader fr = new FileReader(inputAddress);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();

		int cs = Integer.parseInt(line);
		cases = cs;
		
		line = br.readLine();
		int rk = Integer.parseInt(line);
		rank = rk;
		
		line = br.readLine();
		String[] kcs = line.split("\\s+");
		
		int kx = Integer.parseInt(kcs[0]);
		karenX = kx;
		
		int ky = Integer.parseInt(kcs[1]);
		karenY = ky; 
		
		line = br.readLine();
		int bps = Integer.parseInt(line);
		beepers = bps;
		
		int[] xs = new int[1+beepers];
		int[] ys = new int[1+beepers];
		
		xs[0] = karenX;
		ys[0] = karenY;
		
		for(int i=0; i < beepers; i++) {
			line = br.readLine();
			String[] coords = line.split("\\s+");
			
			int bx = Integer.parseInt(coords[0]);
			xs[i+1] = bx;

			int by = Integer.parseInt(coords[1]);
			ys[i+1] = by;

		}
		
		xCoords = xs;
		yCoords = ys;
		
		distances = getDistances(xCoords, yCoords);
		
		gg = new int[1+beepers][1 << beepers];
		
	}
	
	/**
	 * Computes the distance matrix, given the coordinate arrays.
	 * @param xs
	 * @param ys
	 * @return
	 */
	private static int[][] getDistances(int[] xs, int[] ys) {
		int dim = xs.length;
		int[][] result = new int[dim][dim];
		
		for(int i=0; i < dim; i++) {
			for(int j=0; j < dim; j++) {
				int xdif = xs[i]-xs[j];
				int ydif = ys[i]-ys[j];
				result[i][j] = Math.abs(xdif) + Math.abs(ydif);
			}
		}
		
		return result;
	}
	
	/**
	 * The solution to the current setup.
	 * @return
	 */
	private static int krn() {
		int n = 1 + beepers;
		int allVisited = (1 << beepers)-1;
		
		// initialize table
		for(int i=0; i < n; i++) {
			for(int j=0; j<= allVisited; j++) {
				gg[i][j] = Integer.MAX_VALUE;
			}
		}
		
		// base case
		for(int i=0; i < n; i++) {
			gg[i][0] = distances[i][0];
		}
		
		// table filling. We use a bitmask for the subsets.
		for(int mask=1; mask <= allVisited; mask++) {
			for(int i=0; i < n; i++) {
				
				if(i==0 && mask < allVisited) {
					continue; // Karen must collect all beepers.
				}
				
				for(int j=1; j < n; j++) {
					if((mask & (1 << (j-1))) != 0 ) {
						gg[i][mask] = Math.min(gg[i][mask],
								distances[i][j] +
								gg[j][mask ^ (1 << (j-1))]);
					}
				}
				
			}
		}

		return gg[0][allVisited];
	}
	
	public static void main(String[] args) {
        try {
            setUp("C:\\Users\\profesor\\git\\dalgo202510\\"
            		+ "dalgoA\\src\\semanas04a06\\karenInput.txt"); 
            int shortestPath = krn();
            System.out.println("The shortest path has length " + shortestPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}
