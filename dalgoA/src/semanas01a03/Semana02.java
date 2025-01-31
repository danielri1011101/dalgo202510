package semanas01a03;

public class Semana02 {
	
	public static int recursiveP3dR(int steps, int t0, int t1) {
		if(steps <= 0) {
			return t0;
		}
		if(steps == 1) {
			return t1;
		}
		return -recursiveP3dR(steps-2, t0, t1) - recursiveP3dR(steps-1, t0, t1);
	}
	
	public static int iterativeP3dR(int steps, int t0, int t1) {
		if(steps <= 0) {
			return t0;
		}
		if(steps == 1) {
			return t1;
		}
		int temp, previous, current = 0;
		previous = t0;
		current = t1;
		while(steps >= 2) {
			temp = current;
			current = -current-previous;
			previous = temp;
			steps--;
		}
		return current;
	}
	
	public static void main(String[] args) {
		int steps = 10;
		int i = 0;
//		while(i < steps-1) {
//			System.out.print(recursiveP3dR(i, 2, 2) + ", ");
//			i++;
//		}
//		System.out.println(recursiveP3dR(i, 2, 2));
//
//		System.out.println();
//		
//		i = 0;

		while(i < steps-1) {
			System.out.print(iterativeP3dR(i, 3, 4) + ", ");
			i++;
		}
		System.out.println(iterativeP3dR(i, 3, 4));
	}

}
