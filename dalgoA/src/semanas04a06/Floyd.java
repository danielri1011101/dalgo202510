package semanas04a06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class Floyd {
	
	private static int[][] stepMatrix;

	private static int[][] floydWarshall(int[][] distances) {
		int rank = distances[0].length;
		stepMatrix = new int[rank][rank];
		for(int k = 0; k < rank; k++) {
			for(int i = 0; i < rank; i++) {
				for(int j = 0; j < rank; j++) {
					if(distances[i][k] + distances[k][j] < distances[i][j]) {
						distances[i][j] = distances[i][k] + distances[k][j];
						stepMatrix[i][j] = k+1;
					}
				}
			}
		}
		return distances;
	}
	
	public static void printMatrix(int[][] matrix) {
		int height = matrix.length;
		int width = matrix[0].length;
		for(int i = 0; i < height; i++) {
			int j = 0;
			while(j < width-1) {
				if(matrix[i][j] < 10) {
					System.out.print(matrix[i][j] + "  ");
				}
				else {
					System.out.print(matrix[i][j] + " ");
				}
				j++;
			}
			System.out.println(matrix[i][j]);
			System.out.println();
		}
	}
	
	private static int[][] getSubmatrix(int[][] matrix, int[] subset) {
        int size = subset.length;
        int[][] submatrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                submatrix[i][j] = matrix[subset[i]][subset[j]];
            }
        }
        return submatrix;
    }
	
	public static void main(String[] args)  {
		try {
			FileReader fr = new FileReader("/home/profesor/git/dalgo202510/"
					+ "dalgoA/src/semanas04a06/floydData.txt");
			// take subset {2,3,4,5,6} as an example.
			BufferedReader br = new BufferedReader(fr);
			
			String nrs = br.readLine();
			
			int rank = nrs.split("\\s+").length;
			int[][] distances = new int[rank][rank];
			
			int i = 0;
			while(nrs != null) {
				if(nrs.trim().isEmpty()) {
					nrs = br.readLine();
					continue;
				}
				String[] nrList = nrs.split("\\s+");
				for(int j=0; j < nrList.length; j++) {
					int v = Integer.parseInt(nrList[j]);
					distances[i][j] = v;
				}
				i++;
				nrs = br.readLine();
			}
			
			int[] subset = {2,3,4,5,6};
 			
			int[][] subDistances = getSubmatrix(distances, subset);

			printMatrix(subDistances);
			
//			System.out.println();
//			System.out.println();
//			
//			printMatrix(floydWarshall(subDistances.clone()));
//			
//			System.out.println();
//			System.out.println();
//			
//			printMatrix(stepMatrix);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
