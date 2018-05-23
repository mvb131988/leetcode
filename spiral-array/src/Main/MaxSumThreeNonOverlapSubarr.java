package Main;

import java.util.Arrays;

public class MaxSumThreeNonOverlapSubarr {

	static int counter = 0;
	
	public static void main(String[] args) {
		//int[] input = {2,1,1,1,1,2,2,6,7,5,1};
		//int k = 1;
	
		int[] input = {1,2,1,2,6,7,5,1};
		int k = 2;
		
		System.out.println(new MaxSumThreeNonOverlapSubarr().find(input, k, 0, input.length-1, 0).sum);
		System.out.println(Arrays.toString(new MaxSumThreeNonOverlapSubarr().find(input, k, 0, input.length-1, 0).indexes));
		System.out.println(counter);
	}
	
	public Context find(int[] input, int k, int iStart, int iEnd, int iSubarr) {
		//end included
		int firstEnd = iEnd - k*(2-iSubarr);
		
		Context maxSum = new Context();
		for(int i=iStart; i <= firstEnd-k+1; i++) {
			counter++;
			
			int localSum = getSum(input, i, i+k-1);
			
			if(iSubarr<2) {
				Context context = find(input, k, i+k, iEnd, iSubarr+1);
				
				if(localSum + context.sum > maxSum.sum) {
					maxSum.sum = localSum + context.sum;
					maxSum.indexes = context.indexes;
					maxSum.indexes[iSubarr] = i;
				} 
				//lexicographic ordering 
				else if(localSum + context.sum == maxSum.sum) {
					maxSum.indexes[iSubarr] = ordering(input, i, maxSum.indexes[iSubarr], k);
				}
			} else if(iSubarr == 2) {
				if(localSum > maxSum.sum) {
					maxSum.sum = localSum;
					maxSum.indexes[iSubarr] = i;
				}
			}
		}
		
		
		return maxSum;
	}
	
	private int ordering(int[] input, int start1, int start2, int k) {
		for(int i=0; i<k; i++) {
			if(input[start1+i] > input[start2+i]) {
				return start2;
			}
			if(input[start1+i] < input[start2+i]) {
				return start1;
			}
		}
		
		return start1;
	}
	
	public int getSum(int[] input, int start, int end) {
		int sum = 0;
		
		for(int i=start; i<=end; i++) {
			sum += input[i];
		}
		
		return sum;
	}
	
	static class Context {
		int sum = 0;
		int[] indexes = new int[3];
	}
	
}
