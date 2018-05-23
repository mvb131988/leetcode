package Main;

import java.util.ArrayList;
import java.util.List;

public class TargetSum {

	private static int execCounter = 0; 
	
	public static void main(String[] args) {
		int[] input = {1, 1, 1, 1, 1, 1};
		input = new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
						   1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
						   1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
						   1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		int target= 38;
		
		int[][] countMatcher = new TargetSum().getCountMatcher(input);
		int count = new TargetSum().countRecursively(input, 0, target, 0, countMatcher);
		
		System.out.println("raw counter : " + count);
		System.out.println("map counter : " + countMatcher[0][1000]);
		System.out.println();
		
		List<List<Integer>> sequences = new TargetSum().allSequences(input, target);
		sequences = new TargetSum().filter(sequences, target);
		
		for(List<Integer> sequence: sequences){
			
			for(Integer i: sequence) {
				if(i>0) {
					System.out.print("+" + i + "   ");
				} else {
					System.out.print(i + "   ");
				}
			}
			
			System.out.println();
		}
		
		System.out.println();
		System.out.println(execCounter);
	}
	
	public int countRecursively(int[] input, int pos, int target, int sum, int[][] countMatches) {
		execCounter++;
		
		if(pos == input.length && sum == target) {
			return 1;
		} else if(pos == input.length && sum != target) {
			return 0;
		} else {
//			if(countMatches[pos][1000 + sum] > 0) {
//				return countMatches[pos][1000 + sum];
//			}
		}
		
		int c1 = countRecursively(input, pos + 1, target, sum+input[pos], countMatches);
		int c2 = countRecursively(input, pos + 1, target, sum-input[pos], countMatches);
		
		countMatches[pos][1000+sum] = c1+c2;
		
		return c1+c2;
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	
	public int[][] getCountMatcher(int[] input) {
		int[][] countMatcher = new int[input.length][2001];
		return countMatcher;
	}
	
	public List<List<Integer>> filter(List<List<Integer>> sequences, int target) {
		List<List<Integer>> temp = new ArrayList<>();
		for(List<Integer> s: sequences) {
			int sum = 0;
			for(Integer i: s) {
				sum += i;
			}
			if(sum == target) {
				temp.add(s);
			}
		}
		return temp;
	}
	
	public List<List<Integer>> allSequences(int[] input, int target) {
		List<List<Integer>> sequences = new ArrayList<>();
		
		for(int i: input) {
			if(sequences.size() == 0) {
				List<Integer> positive= new ArrayList<>();
				positive.add(i);
				List<Integer> negative= new ArrayList<>();
				negative.add(-i);
				sequences.add(positive);
				sequences.add(negative);
			}
			else {
				//double lists
				List<List<Integer>> tempList = new ArrayList<>();
	 			for(List<Integer> list: sequences) {
					List<Integer> temp = getCopy(list);
					temp.add(-i);
					tempList.add(temp);
				}
	
				for(List<Integer> list: sequences) {
					list.add(i);
				}	
				
				sequences.addAll(tempList);
			}
		}
		
		return sequences;
	}
	
	public List<Integer> getCopy(List<Integer> list) {
		List<Integer> temp = new ArrayList<>();
		for(Integer i: list) {
			temp.add(i);
		}
		return temp;
	}
	
}
