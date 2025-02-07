package semanas01a03;

public class Semana03 {
	
	private static int[] wrongMerge(int[] aa, int[] bb) {
		int[] result = new int[aa.length + bb.length];
		int iR = 0; // result index
		int iA = 0; // aa index
		int iB = 0; // bb index
		int minAa = aa[iA];
		int minBb = bb[iB];
		while(iR < result.length) {
			if(minAa < minBb) {
				result[iR] = minAa;
				if(iA < aa.length-1) {
					iA++;
					minAa = aa[iA];
				}
			}
			else {
				result[iR] = minBb;
				if(iB < bb.length-1) {
					iB++;
					minBb = bb[iB];
				}
			}
			iR++;
		}
		return result;
	}
	
	public static void printArray(int[] aa) {
		if(aa.length > 0) {
			int i = 0;
			while(i < aa.length-1) {
				System.out.print(aa[i] + ", ");
				i++;
			}
			System.out.println(aa[aa.length-1]);
		}
	}
	
	public static void main(String[] args) {
		int[] aa = new int[] {2,4,6};
		int[] bb = new int[] {1,3,5};
		int[] cc = new int[] {4,5,6,10,11,12};
		int[] dd = new int[] {1,2,3,7,8,9};
		
		printArray(wrongMerge(aa,bb));
		
		printArray(wrongMerge(cc,dd));
		
	}

}
