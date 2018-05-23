package Main;

public class DPMaxSumThreeNonOverlapSubarr {

	public static void main(String[] args) {
//		int[] input = {2,1,1,1,1,2,2,6,7,5,1};
//		int k = 3;
	
		int[] input = {2,1,1,2,6,7,5,1};
		int k = 2;
		
		int[] res = new DPMaxSumThreeNonOverlapSubarr().find(input, k);
		
		for(int i: res) {
			System.out.print(i + " ");
		}
	}

	public int[] find(int[] input, int k) {
		int[] sum = new int[input.length+1];
		
		//sum
		for(int i=0; i<input.length; i++) {
			sum[i+1] = sum[i] + input[i];
		}
		
		//left
		int[] iLeft = new int[input.length];
		int max = sum[k];
		
		for(int i=k; i<input.length; i++) {
			int localSum = sum[i+1] - sum[i-1];
			if(max < localSum) {
				max = localSum;
				iLeft[i] = i-k+1;
			} else {
				iLeft[i] = iLeft[i-1];
			}
		}
		
		//right
		int[] iRight = new int[input.length];
		max = sum[sum.length-1] - sum[sum.length-1-k];
		iRight[input.length-k] = input.length-k;
		
		for (int i = input.length - k - 1; i >= 0; i--) {
			int localSum = sum[i+k] - sum[i];
			if(max < localSum) {
				max = localSum;
				iRight[i] = i;
			} else {
				iRight[i] = iRight[i+1];
			}
		}
		
		//traverse left, right, middle
		int maxSum = 0;
		int[] result = new int[3]; 
		for (int i=k; i<=input.length-2*k; i++) {
			int iLMax = iLeft[i-1];
			int iRMax = iRight[i+k];
			
			int leftSum = sum[iLMax+k] - sum[iLMax];
			int rightSum = sum[iRMax+k] - sum[iRMax];
			int localSum = sum[i+k] - sum[i];
			
			if(leftSum + rightSum + localSum > maxSum) {
				maxSum = leftSum + rightSum + localSum;
				
				result[0] = iLMax;
				result[1] = i;
				result[2] = iRMax;
			}
		}
		
		return result;
	}

	static class Context {
		int sum = 0;
		int[] indexes = new int[3];
	}
	
}
