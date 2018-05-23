package Main;

public class SortColors {

	public static void main(String[] args) {
		
		int[] input = new int[]{1,2,1,0,1,2,0,0,2,1,2,0,0,1,1,2};
		
//		int[] input = new int[]{1,2,1,0};
		
//		int[] input = new int[]{1,0,1,2,0,0,2};
		
//		int[] input = new int[]{2,0,0,2};
		
		new SortColors().sort(input);
		System.out.println(input);
	}
	
	public void sort(int[] input) {
		int left = 0;
		int right = input.length - 1;
		
		while(input[left] == 0) {left++;}
		while(input[right] == 2) {right--;};
		
		int middle = left;
		
		while(middle<=right) {
			if(input[middle] == 0) {
				swap(input, left, middle);
				left++;
				if(middle <left) {
					middle = left;
				}
			}
			else if(input[middle] == 2) {
				swap(input, right, middle);
				right--;
			}
			else if(input[middle] == 1) {
				middle++;
			}
		}
	}
	
	public void swap(int[] input, int pos1, int pos2) {
		int temp = input[pos1];
		input[pos1] = input[pos2];
		input[pos2] = temp;
	}
	
}
