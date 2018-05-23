package Main;

public class Combination {

	public static void main(String[] args) {
		
		int size = 6;
		int groupSize= 4;
		
		int[] input = new int[size];
		
		for(int i=0; i<input.length; i++) {
			input[i] = i+1;
		}
		
		int[][] combinations = new Combination().generate(input, 0, null, groupSize);
		for(int i=0; i<combinations.length; i++) {
			for(int j=0; j<combinations[i].length; j++) {
				System.out.print(combinations[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public int[][] generate(int[] input, int position, int[] group, int groupSize) {
		
		if(position == input.length) {
			
			if(group != null && group.length == groupSize) {
				int[][] combinations = new int[1][];
				combinations[0] = group;
				return combinations;
			}
			
			return null;
		} else {
			
			//leave
			int[][] combination1 = generate(input, position+1, group, groupSize);
			
			//take
			int[] new_group = group == null ? new_group = new int[1] : new int[group.length+1];
			if(group != null) {
				for(int i=0; i<group.length; i++) {
					new_group[i] = group[i];
				}
			}
			new_group[new_group.length-1] = input[position]; 
			int[][] combination2 = generate(input, position+1, new_group, groupSize);
			
			
			return consolidate(combination1, combination2);
		}
		
	}
	
	public int[][] consolidate(int[][] a1, int[][] a2) {
		int[][] a3 = null;
		if(a1!=null && a2!=null) {
			a3 = new int[a1.length + a2.length][];
			for (int i = 0; i < a1.length; i++) {
				a3[i] = a1[i];
			}
			for (int j = a1.length, i=0; i < a2.length; j++, i++) {
				a3[j] = a2[i];
			}
		} 
		if(a1!=null && a2==null) {
			a3=a1;
		}
		if(a1==null && a2!=null) {
			a3=a2;
		}
		
		return a3;
	}
	
}
