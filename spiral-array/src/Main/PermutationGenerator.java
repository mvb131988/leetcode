package Main;

public class PermutationGenerator {

	public static void main(String[] args) {
		PermutationGenerator generator = new PermutationGenerator(10);
		int[] permutataion;
		int counter = 0;
		while((permutataion = generator.next()) != null) {
			for(int i=0; i<permutataion.length; i++) {
				System.out.print(permutataion[i] + " ");
			}
			System.out.println();
			counter++;
		}
		
		System.out.println("permutation count: " + counter);
	}
	
	private int[] current;
	
	private boolean firstStart = true;
	
	public PermutationGenerator(int n) {
		current = new int[n];
		for(int i=0; i<n; i++) {
			current[i] = i+1;
		}
	}
	
	public int[] next() {
		
		if(firstStart) {
			firstStart = false;
			return current;
		}
		
		for(int i=current.length-1; i>-1; i--) {
			if(i == 0) {
				return null;
			}
			
			//step 1:swapping point
			if(current[i] > current[i-1]) {
				//step2:
				//max possible index of a[i]<a[k] 
				int k = i;
				for(int j=i+1; j<current.length; j++) {
					if(current[i-1] < current[j]) {
						k = j;
					}
				}
				
				//step3:
				//swap a[i] and a[k]
				int temp = current[i-1];
				current[i-1] = current[k];
				current[k] = temp;
				
				//step4: ascending sort of subarray a[i+1]-a[length-1]
				sort(i);
				
				break;
			}
		}
		
		return current;
	}
	
	public void sort(int startPos) {
		for(int i = startPos; i < current.length; i++) {
			int cur = current[i];
			int iMin = i;
			int min = cur;
			for(int j = i+1; j < current.length; j++) {
				if(current[j] < min) {
					min = current[j];
					iMin = j;
				}
			}
			//swap
			current[i] = min;
			current[iMin] = cur;
		}
	}
	
}
