package semanas04a06;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Semana06clase1 {
	
	/**
	 * The input arrays have the same length.
	 * @param weights :: positive integers
	 * @param values :: positive integers
	 * @return
	 */
	private static int[][] preKnapsack(int[] weights, int[] values,
			int maxWt) {
		int rank = weights.length;
		int[][] valueTable = new int[rank][maxWt+1];
		for(int i=0; i < rank; i++) {
			for(int j=0; j <= maxWt; j++) {
				if(valueTable[i-1][j-weights[i]] + values[i]
						> valueTable[i-1][j]) {
					valueTable[i][j] = valueTable[i-1][j-weights[i]] + 
							values[i];
				}
			}
		}
		return valueTable;
	}

	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("C:\\Users\\profesor\\git\\dalgo202510"
					+ "\\dalgoA\\src\\semanas04a06\\trial.txt");
			BufferedReader br = new BufferedReader(fr);
			
			int[] weights = new int[10];
			
			String nr = br.readLine();
			
			int i = 0;
			while(nr != null && i < 10) {
				int weight = Integer.parseInt(nr);
				weights[i] = weight;
				i++;
				nr = br.readLine();
			}
			
//			for(int w : weights) {
//				System.out.print(w + " ");
//			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
