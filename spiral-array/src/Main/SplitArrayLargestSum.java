package Main;

public class SplitArrayLargestSum {
	
	private static int count =0;
	
	public static void main(String[] args) {
//		int[] nums = new int[]{7,2,5,10,8,9,3};
//		int m = 2;
		
//		int[] nums = new int[]{7,2,5,10,8};
//		int m = 3;
		
		int[] nums = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		int m = 8;
		
//		int[] nums = new int[]{1,2,3,4,5};
//		int m = 3;
		
		int sum = new SplitArrayLargestSum().calculate(nums, m);
		System.out.println(sum);
		System.out.println(count);
	}
	
	public int calculate(int[] nums, int m) {
		//validate nums, m
		
		int[] sums = new int[nums.length + 1];
		
		for(int i=1; i<sums.length; i++) {
			sums[i] = sums[i-1] + nums[i-1];
		}
		
		int sum = getMax(nums, 1, m, 0, sums);
		
		return sum;
	}
	
	public int getMax(int[] nums, int subArrNumber, int m, int subArrI, int[] sums) {
		int sum = Integer.MAX_VALUE;
		if (m - subArrNumber +1 > nums.length - subArrI) {
			return sum;
		}
		
		count++;
		
		if(subArrNumber == m) {
			if(subArrI < nums.length) {
				sum = sums[nums.length] - sums[subArrI];
			}
		} else {
			for(int i=subArrI+1; i<nums.length; i++) {
				int sumLeft = sums[i] - sums[subArrI];
				int sumRight = getMax(nums, subArrNumber+1, m, i, sums);
				
				int localMax = sumLeft > sumRight ? sumLeft : sumRight;
				sum = sum > localMax ? localMax : sum;
			}
		}
		
		return sum;
	}
	
//	//start index
//	int[] startI = new int[m];
//	for(int i=1; i<m; i++) {
//		startI[i] = i;
//	}
//	
//	//end index
//	int[] endI = new int[m];
//	for(int i=nums.length - m + 1, j=1; i<nums.length; i++, j++) {
//		endI[j] = i;
//	}
//	
//	//current
//	int[] currentI = new int[m];
//	for(int i=1; i<startI.length; i++) {
//		currentI[i] = startI[i];
//	}
//	
//	for(int j=0; j<currentI.length; j++) {
//		System.out.print(currentI[j] + " ");
//	}
//	System.out.println();
//	while(!equals(currentI,endI)) {
//		nextIndex(currentI, startI, endI);
//		for(int j=0; j<currentI.length; j++) {
//			System.out.print(currentI[j] + " ");
//		}
//		System.out.println();
//	}
	
//	public boolean equals(int[] currentI, int[] endI) {
//		for(int i=0; i<currentI.length; i++) {
//			if(currentI[i] != endI[i]) {
//				return false;
//			}
//		}
//		return true;
//	}
//	
//	public void nextIndex(int[] currentI, int[] startI, int[] endI) {
//		int i = currentI.length-1;
//		while(currentI[i] == endI[i]) {
//			i--;
//		}
//		
//		currentI[i]++;
//		
//		for(int j=i+1; j<currentI.length; j++) {
//			currentI[j] = currentI[j-1]+1;
//		}
//	}
	
}
