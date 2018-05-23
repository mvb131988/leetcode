package Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

	public static void main(String[] args) {
		
		int[] input = {-1, 0, 1, 2, -1, 0, 1, -4, -2};
		int target = 0;
		
		List<List<Integer>> resS = new ThreeSum().getSequence(input, target);
		
		if(resS != null) {
			for(List<Integer> res: resS) {
				for(Integer i: res) {
					System.out.print(i + " ");
				}
				System.out.println();
			}
		}
		
		
	}
	
	public List<List<Integer>> getSequence(int[] input, int target) {
		
		Arrays.sort(input);
		
		List<List<Integer>> res = new ArrayList<>();
		
		for(int i=0; i<input.length; i++) {

			if(i>0 && input[i] != input[i-1]) {
	
				List<Integer> r = new ArrayList<>();
				
				int j = i+1;
				int k = input.length-1;
				while (j<k) {
					
					if(input[i] + input[j] + input[k] == target) {
						r.add(input[i]);
						r.add(input[j]);
						r.add(input[k]);
						
						res.add(r);
						r = new ArrayList<>();
						
						while(input[j] == input[j+1]) {
							j++;
						}
						
						while(input[k] == input[k-1]) {
							k--;
						}
						
						j++;
						k--;
					} 
					else if(input[i] + input[j] + input[k] < target) {
						j++;
					} else {
						k--;
					}
					
				}
			}
			
		}
		
		return res;
	}
	
}
