package Main;

public class ProductArrayExceptSelf {

	public static void main(String[] args) {
		int[] input = new int[] {1,2,3,4};
		
		int[] output = new ProductArrayExceptSelf().product0(input);
		for(int i: output) {
			System.out.print(i + " ");
		}
		
		System.out.println();
		
		output = new ProductArrayExceptSelf().product1(input);
		for(int i: output) {
			System.out.print(i + " ");
		}
	}
	
	private int[] product1(int[] input) {
		int[] out = new int[input.length];
		
		out[0] = 1;
		
		for (int i = 1; i < input.length; i++) {
			out[i] = out[i-1] * input[i-1];
		}
		
		int last = 1;
		for (int i = input.length - 2; i >= 0; i--) {
			out[i] = out[i] * last * input[i+1];
			last = last * input[i+1];
		}
		
		return out;
	}
	
	private int[] product0(int[] input) {
		int[] out = new int[input.length];
		
		int[] left = new int[input.length]; 
		int[] right = new int[input.length];

		left[0] = 1;
		for (int i = 1; i < input.length; i++) {
			left[i] = left[i-1] * input[i-1];
		}
		
		right[input.length-1] = 1;
		for (int i = input.length - 2; i >= 0; i--) {
			right[i] = right[i+1] * input[i+1];
		}
		
		for (int i = 0; i < input.length; i++) {
			out[i] = left[i] * right[i];
		}
		
		return out;
	}
	
}
