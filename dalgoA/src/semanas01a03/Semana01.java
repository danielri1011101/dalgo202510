package semanas01a03;

public class Semana01 {
	
	public static int fib1(int n) {
		if(n <= 1) {
			return 1;
		}
		return fib1(n-2) + fib1(n-1);
	}
	
	public static int fib2(int n) {
		if(n <= 1) {
			return 1;
		}
		int result = 1;
		int prev = 1;
		for(int i = 2; i <=n; i++) {
			result = result+prev;
			prev = result-prev;
		}
		return result;
	}
	
	public static boolean naiveSearch(int[][] mm, int y) {
		int height = mm.length;
		int width = mm[0].length;
		for(int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				if(mm[row][col] == y) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean searchInSorted(int[][] mm, int y) {
		int height = mm.length;
		int width = mm[0].length;
		int row = 0;
		int col = width-1;
		while(row < height && col >= 0) {
			if(y == mm[row][col]) {
				return true;
			}
			else if(y > mm[row][col]) {
				row++;
			}
			else {
				col--;
			}
		}
		return false;
	}
	
	public static void insertionSort(int[] aa) {
		int n = aa.length;
		for(int i=1; i<n; i++) {
			int x = aa[i];
			int j = i-1;
			while(j>=0 && x<aa[j]) {
				aa[j+1] = aa[j];
				j--;
							}
			aa[j+1] = x;
		}
	}
	
	/**
	 * Bolzano-Cauchy algorithm to compute the square root of n. 
	 * @param n
	 * @param steps
	 * @return
	 */
	public static float bcSqrt(int n, int steps) {
		if(steps <= 0 || n <= 0) {
			return n;
		}
		float lower = 0f;
		float upper = n;
		float ans = 0f;
		boolean found = false;
		while(steps > 0 && !found) {
			ans = (upper + lower)/2;
			if(ans*ans == n) {
				found = true;
			}
			else if(ans*ans > n) {
				upper = ans;
			}
			else {
				lower = ans;
			}
			steps--;
		}
		return ans;
	}
	
	public static void main(String[] args) {
		int foo = 2 << 4;
		System.out.println(1<<2);
		System.out.println(5^8);
	}

}
