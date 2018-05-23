package Main;

import java.util.Arrays;

public class KLargestElement {

	public static void main(String[] args) {
		int[] input = {3,2,1,5,6,4};
		input = new int[] {1, 16, 5, 30, 27, 17, 20, 2, 57, 3, 90};
		input = new int[] {100, 19, 36, 17, 3, 25, 1, 2, 7};
		
		new KLargestElement().buildHeap(input);
		
		for(int i=0; i<input.length; i++) {
			System.out.print(input[i] + " ");
		}
		
		System.out.println();
		for(int i=1; i<=input.length; i++) {
			System.out.println(new KLargestElement().kLargeElement(Arrays.copyOf(input, input.length), i));
		}
	}
	
	public int kLargeElement(int[] heap, int kOrder) {
		int heapLength = heap.length;
		
		int temp = heap[0];
		
		for(int i=0; i<kOrder; i++) {
			temp = heap[0];
			heap[0] = heap[heapLength - 1];
			heap[heapLength - 1] = temp;
			heapLength--;
			
			siftDown(heap, 0, heapLength);
		}
		
		return temp;	
	}
	
	public void buildHeap(int[] input) {
		int levelNumber = levelNumber(input.length);
		
		for (int i = levelNumber; i > 0; i--) {
			//level boundaries
			int start = (int) Math.pow(2, i - 1) - 1;
			int end = ((int) Math.pow(2, i) - 1) - 1;
			end = end > input.length - 1 ? input.length - 1 : end; 
			for(int j=start; j<=end; j++) {
				siftDown(input, j, input.length);
			}
		}
		
	}
	
	public void siftDown(int[] input, int pos, int length) {
		//compare with left element
		int leftPos = (pos+1)*2-1;
		
		//compare with right element
		int rightPos = ((pos+1)*2+1)-1;
		
		int swapPos = -1;
		if(leftPos < length && input[pos] > input[leftPos]) {
			//swap input[pos] and input[leftPos]
			swapPos = leftPos;
		}
		
		if(rightPos < length && input[pos] > input[rightPos] && input[rightPos] < input[leftPos]) {
			//swap input[pos] and input[rightPos]
			swapPos = rightPos;
		}
		
		if(swapPos != -1) {
			int temp = input[pos];
			input[pos] = input[swapPos];
			input[swapPos] = temp;

			siftDown(input, swapPos, length);
		}
		
	}
	
	public int levelNumber(int number) {
		int level = -1;

		while(number > 0) {
			number = number/2;
			level++;
		}
		
		return level;
	}
	
}
