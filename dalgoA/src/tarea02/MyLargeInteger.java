package tarea02;

public class MyLargeInteger {
	
//------------------------------------------------------------------------------
//------------------------ class fields ----------------------------------------
//------------------------------------------------------------------------------
	
	/** The position of the largest block. Namely, it's the
	 * (floor of the) BASE logarithm of the number.
	 * 
	 */
	private int sizeIndex; 
	
	/** The number in BASE expansion, from right to left. For instance if
	 * the base is 10 then 7 _times_ 7 is {9,4} and  7 _times_ 9 is {3,6}.
	 */
	private int[] blocks; 
	
	/**
	 * Length of the BASE expansion of the number.
	 */
	public static final int BLOCK_SIZE = 16;
	
	/**
	 * The base. You should test your algorithm with several bases.
	 */
	public static final int BASE = 10;

	/**
	 * Whether or not the base yields to reasonably extending the int primitive.
	 */
	public static final boolean VALID_BASE = 2 <= BASE && BASE <= 46340;
	
	
//------------------------------------------------------------------------------
//------------------------ constructors ----------------------------------------
//------------------------------------------------------------------------------
	
	
	/**
	 * Creates an instance in degree 0.
	 * @param v
	 */
	public MyLargeInteger(int v) {
		this(v,0);
	}
	
	/**
	 * creates an instance
	 * @param value
	 * @param power
	 */
	private MyLargeInteger(int value, int power) {
		this.blocks = new int[BLOCK_SIZE];
		if(-BASE <= value && value <= BASE &&
				0 <= power && power < BLOCK_SIZE) {
			this.blocks[power] = value;
			this.sizeIndex = power;
		}
	}
	
	/**
	 * To use with care, because the argument might not satisfy
	 * the class constraints.
	 * @param blocks
	 */
	private MyLargeInteger(int[] blocks) {
		this.blocks = blocks;
	}
	
//-------------------------------------------------------------------------------
//------------------------ class methods ----------------------------------------
//-------------------------------------------------------------------------------
	
	/**
	 * Product of two large integers.
	 * @param nn
	 * @param mm
	 * @return
	 */
	public static MyLargeInteger multiply(MyLargeInteger nn, MyLargeInteger mm) {
		// TODO: Implement this method.
		// XXX: The main task of this exercise.
		return nn;
	}
	
	/**
	 * Sum of two large integers.
	 * @param nn
	 * @param mm
	 * @return
	 */
	public static MyLargeInteger add(MyLargeInteger nn, MyLargeInteger mm) {
		int b = BASE;
		
		int steps = nn.sizeIndex;
		if(mm.sizeIndex > steps) {
			steps = mm.sizeIndex;
		}
		MyLargeInteger result = new MyLargeInteger(0);
		result.sizeIndex = steps;
		
		boolean carry = false;
		int i = 0;
		while(i <= steps || carry) {
			if(carry) {
				result.blocks[i] = (nn.blocks[i] + mm.blocks[i] + 1) % b;
				carry = nn.blocks[i] + mm.blocks[i] + 1 > b;
			}
			else {
				result.blocks[i] = (nn.blocks[i] + mm.blocks[i]) % b;
				carry = nn.blocks[i] + mm.blocks[i] > b;
			}
			i++;
		}
		if(i > steps+1) {
			result.sizeIndex++;
		}
		
		return result;
	}
	
	/**
	 * The number shifts itself p places to the right, if possible.
	 * @param p
	 */
	private void shift(int p) {
		if(p <= 0 || this.sizeIndex + p >= BLOCK_SIZE) {
			return;
		}
		for(int i = this.sizeIndex; i >= 0; i--) {
			this.blocks[i+p] = this.blocks[i];
			this.blocks[i] = 0;
		}
		this.sizeIndex +=p;
	}
	
	/**
	 * The number prints itself, from left to right, with exactly one
	 * blank between each coefficient, ending with a new line.
	 * Example: {5,6,-12} ==> "-12 6 5\n"
	 */
	// XXX: Implement properly to pass automated testing.
	public void print() {
		// TODO: Implement this method.
	}
	
	
//------------------------------------------------------------------------------
//------------------------ main method -----------------------------------------
//------------------------------------------------------------------------------

	public static void main(String[] args) {
		MyLargeInteger fifty = new MyLargeInteger(5, 1);
		MyLargeInteger two = new MyLargeInteger(2);
		MyLargeInteger nine = new MyLargeInteger(9);
		
		MyLargeInteger fiftyTwo = add(fifty, two);
		MyLargeInteger fiftyNine = add(fifty, nine);
		
		add(fifty, two).print();
		
		add(fiftyNine, fiftyTwo).print();
		
		fiftyNine.shift(2);
		
		fiftyNine.print();
		
	}
	
}