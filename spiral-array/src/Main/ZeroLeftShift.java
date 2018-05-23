package Main;

import java.util.LinkedList;
import java.util.Queue;

public class ZeroLeftShift {

	public static void main(String[] args) {
	
		int[] in =  {3, 0, 2, 0, 1, 3};
		int[] in1 = {0, 0, 0, 2, 1, 0, 0, 5, 0, 0, 0, 3, 0, 4, 5, 0, 0, 0, 0, 0, 7};
		
		int[] res = new ZeroLeftShift().shift2(in1);
		for(int i=0; i< res.length; i++) {
			System.out.print(res[i] + " ");
		}
	}
	
	public int[] shift1(int[] nums) {
		Queue<Integer> q = new LinkedList<>();
		
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				q.add(i);
			} else {	
				if(q.size() > 0) {
					int j = q.remove();
					q.add(i);
					
					int temp = nums[i];
					nums[i] = nums[j];
					nums[j] = temp;
				}
			}
		}

		return nums;
	}
	
	public int[] shift2(int[] nums) {
		int iNonNull = -1;
		
		for(int i=0; i<nums.length; i++) {
			if(nums[i] != 0) {
				//3d variant swap instead
				
				nums[++iNonNull] = nums[i];
			}
		}
		
		for(int i=iNonNull+1; i<nums.length; i++) {
			nums[i] = 0;
		}
		
		return nums;
	}
	
}
