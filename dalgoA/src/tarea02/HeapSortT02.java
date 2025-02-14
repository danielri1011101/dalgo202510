package tarea02;

public class HeapSortT02 {
	
	private static class Heap {
		
		private int[] nodeArr;
		private int capacity;
		private int heapIndex;
		
		public Heap(int capacity) {
			if(capacity > 0) {
				this.nodeArr = new int[capacity];
			}
			else { // useless, empty heap.
				this.nodeArr = new int[] {};
				this.heapIndex = -1;
			}
		}
		
		public Heap() {
			this(16);
		}
		
		private Heap(int[] nodeArr, int heapIndex) {
			this.nodeArr = nodeArr;
			this.capacity = nodeArr.length;
			this.heapIndex = heapIndex;
		}
		
		public int getMax() {
			return this.nodeArr[0];
		}
		
		public boolean isEmpty() {
			return this.capacity == 0;
		}
		
		public boolean isFull() {
			return this.heapIndex == this.capacity-1;
		} 
		
		public void deleteMax() {
			// TODO: Implement this method.
		}
		
		/**
		 * Safety requires ensuring the heap is not full.
		 * @param v
		 */
		public void insertNode(int v) {
			// TODO: Implement this method.
		}
		
	}
	
	/**
	 * Naive way to make a heap out of an ordinary array, without
	 * using the Heap class.
	 * @param aa
	 * @return An array with the same elements as aa, satisfying the
	 * heap property.
	 */
	private static int[] slwMkHp(int[] aa) {
		int[] result = new int[aa.length];
		for(int i=0; i < aa.length; i++) {
			prclt(result, i, aa[i]);
		}
		return result;
	}
	
	/**
	 * assigns value v to position i of array-heap h, and restores
	 * the heap property.
	 * @param h // non-empty array satisfying the heap property.
	 * @param i // assumed in the range 0, ..., h.length-1.
	 * @param v
	 */
	private static void prclt(int[] h, int i, int v) {
		int pos = i;
		int parentPos = (pos+1)/2 - 1;
		h[pos] = v;
		while(parentPos >= 0 && v > h[parentPos]) {
			h[pos] = h[parentPos];
			h[parentPos] = v;
			pos = parentPos;
			parentPos = (parentPos+1)/2 - 1;
		}
	}
	
	/**
	 * Assigns value v to position i of heap h and restores the heap property,
	 * whenever v is too small for position i.
	 * @param h // assumed non-empty.
	 * @param i // assumed in the range 0, ..., h.length-1.
	 * @param v
	 */
	private static void siftDown(Heap h, int i, int v) {
		// TODO: Implement this method.
	}
	
	/**
	 * Assigns value v to position i of heap h and restores the heap property,
	 * whenever v is too big for position i.
	 * @param h // assumed non-empty.
	 * @param i // assumed in the range 0, ..., h.length-1.
	 * @param v
	 */
	private static void percolate(Heap h, int i, int v) {
		// TODO: Implement this method.
	}
	
	/**
	 * Replaces the value at i with v and restores the heap property.
	 * @param h // assumed to be a non-empty heap.
	 * @param i // assumed in the range 0,...,h.length-1
	 * @param v
	 */
	private static void alterHeap(Heap h, int i, int v) {
		// TODO: Implement this method.
	}
	
	/**
	 * Naive way to make a heap, using the Heap class.
	 * @param aa
	 * @return
	 */
	public static Heap slowMakeHeap(int[] aa) {
		// TODO: Implement this method.
		return null;
	}
	
	
	/**
	 * Smart way to make a heap, using the Heap class.
	 * @param aa
	 * @return
	 */
	public static Heap makeHeap(int[] aa) {
		// TODO: Implement this method.
		return null;
	}
	
	public static int[] heapSort(int[] aa) {
		// TODO: Implement this method.
		// XXX: The main task of this exercise.
		return null;
	}
	
	/**
	 * Print the array aa.
	 * @param aa
	 */
	public static void printArray(int[] aa) {
		if(aa.length == 0) {
			return;
		}
		int i = 0;
		while(i < aa.length-1) {
			System.out.println(aa[i] + " ");
			i++;
		}
		System.out.println(aa[i]);
	}
	
//------------------------ main ----------------------------------
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		int[] example = new int[] {1,2,2,4,5,6,7,7,9,10};
		
		Heap h = new Heap(12);
	}
}
