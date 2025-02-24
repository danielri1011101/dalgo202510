package semanas04a06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class Semana06 {
	
	
	private static class Item {
		int value;
		int weight;
		
		float unitValue;
		
		public Item(int value, int weight) {
			this.value = value;
			this.weight = weight;
			this.unitValue = value/weight;
		}
		
		public Item() {
			this(1,Integer.MAX_VALUE);
		}
		
		public boolean isValid() {
			return value > 0 && weight > 0;
		}
		
		public void setValue(int value) {
			this.value = value;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}	
		
		public void setUnitValue() {
			this.unitValue = this.value/this.weight;
		}
		
	}
	
	public static String knapsack(Item[] ys) {
		return "";
	}
	
	private static int[] knpsck(Item[] ys, int maxWeight) {
		int[] result = new int[ys.length];
		int[][] valueTable = new int[ys.length][maxWeight+1];
		Item y = ys[0];
		int wt = y.weight;
		valueTable[0][y.weight] = y.value;
		for(int i=1; i < ys.length; i++) {
			y = ys[i];
			wt += y.weight;
			for(int j=y.weight; j <= wt; j++) {
				if(valueTable[i-1][j-y.weight] + y.value > valueTable[i-1][j]) {
					
				}
			}
		}
		return result;
	}
	
	public static void mergeSort(Item[] items) {
        if (items == null || items.length <= 1) {
            return;
        }
        int mid = items.length / 2;

        Item[] left = new Item[mid];
        Item[] right = new Item[items.length - mid];

        System.arraycopy(items, 0, left, 0, mid);
        System.arraycopy(items, mid, right, 0, items.length - mid);

        mergeSort(left);
        mergeSort(right);

        merge(items, left, right);
    }

    private static void merge(Item[] result, Item[] left, Item[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i].weight <= right[j].weight) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }
    }

	
		public static String greedyKnapsack(Item[] ys) {
		return "";
	}
	
	public static int[] grdKnpsck(Item[] ys) {
		int[] result = new int[ys.length];
		return result;
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
		}
	}
	
	public static void main(String[] args)  {
			
	}

}
