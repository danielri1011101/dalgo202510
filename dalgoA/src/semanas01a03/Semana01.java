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

}
