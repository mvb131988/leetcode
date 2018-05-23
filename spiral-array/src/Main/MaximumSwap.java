package Main;

public class MaximumSwap {

	public static void main(String[] args) {
		
		long input = 56431299;
		
		MaximumSwap ms = new MaximumSwap();
		int[] arrInput = ms.toArray(input);
		ms.swap(arrInput);
		ms.swap(arrInput);
		ms.swap(arrInput);
		ms.swap(arrInput);
		System.out.println(arrInput);
	}
	
	public void swap(int[] input) {
		
		//looking for a barrier
		int barrier=-1;
		for(int i=0; i<input.length-1; i++) {
			if(input[i]<input[i+1]) {
				barrier = i;
				break;
			}
		}
		
		if(barrier == -1) {
			return;
		}
		
		//find last max in right subarray [barrier .. length]
		int max = input[barrier+1];
		int iMax = barrier+1;
		for(int j=barrier+1; j<input.length; j++){
			if(input[j] == max) {
				iMax = j;
			}
			if(input[j] > max) {
				max = input[j];
				iMax = j;
			}
		}
		
		//find first digit less than max;
		int i = 0;
		for(i=0; i<input.length; i++) {
			if(input[i] < max) {
				break;
			}
		}
		
		//swap i and iMax;
		int temp = input[i];
		input[i] = input[iMax];
		input[iMax] = temp;
	}
	
	public int[] toArray(long input) {
		int size = 0;
		long temp = input;
		while(temp > 0) {
			temp /= 10;
			size++;
		}
		
		int[] a = new int[size];
		int i = 0;
		temp = input;
		while(temp > 0) {
			a[i++] = (int)(temp%10);
			temp /= 10;
		}
		
		//reverse
		for(int j=0; j<a.length/2; j++) {
			int t = a[j];
			a[j] = a[a.length-1 - j];
			a[a.length-1 - j] = t;
		}
		
		return a;
	}
	
}
