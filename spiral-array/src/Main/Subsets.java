package Main;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

	public static void main(String[] args) {
		int[] input = {1,2,3,4,5};
		
		List<List<Integer>> subsets = new Subsets().generate(input);
		for(List<Integer> subset: subsets) {
			for(Integer i: subset){
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
	
	List<List<Integer>> generate(int[] input) {
		List<List<Integer>> subsets = new ArrayList<List<Integer>>();
		List<Integer> empty = new ArrayList<>();
		subsets.add(empty);
		
		for(int i: input) {
			List<List<Integer>> temp = new ArrayList<List<Integer>>();
			for(List<Integer> s: subsets) {
				temp.add(new ArrayList<>(s));
			}
			
			for(List<Integer> s: temp) {
				s.add(i);
			}
			
			subsets.addAll(temp);
		}
		
		return subsets;
	}
	
}
