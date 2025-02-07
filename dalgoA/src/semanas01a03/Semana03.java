package semanas01a03;

public class Semana03 {
	
	/**
	 * The input arrays are sorted and non-empty.
	 * @param aa
	 * @param bb
	 * @return the merging of the arrays.
	 */
	private static int[] merge(int[] aa, int[] bb) {
		int[] result = new int[aa.length + bb.length];
		int iA = 0;
		int iB = 0;
		int iR = 0;
		while(iR < result.length) {
			 
			if(iA == aa.length && iB < bb.length) {
				result[iR] = bb[iB];
				iR++;
				iB++;
			}
			else if(iB == bb.length && iA < aa.length) {
				result[iR] = aa[iA];
				iR++;
				iA++;
			}
			else {
				while(iA < aa.length && aa[iA] <= bb[iB] ) {
					result[iR] = aa[iA];
					iR++;
					iA++;
				}
				while(iB < bb.length && bb[iB] <= aa[iA] ) {
					result[iR] = bb[iB];
					iR++;
					iB++;
				}
			}
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
		
		printArray(merge(aa,bb));
		printArray(merge(cc,dd));
		
	}

}
