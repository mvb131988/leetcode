package Main;

public class TotalHammingDistance {

	public static void main(String[] args) {
		int[] nums = {4, 14, 2};
		
		System.out.println(new TotalHammingDistance().distance(nums));
	}
	
	public int distance(int[] nums) {
		int d = 0;
		
		for (int i = 0; i < nums.length-1; i++) {
			for (int j = 1; j < nums.length; j++) {
				d += distance(nums[i], nums[j]);
			}
		}
		return d;
	}
	
	public int distance(int x1, int x2) {
		int d = 0;
		
		while(!(x1 ==0 && x2 ==0)) {
			int r1 = x1%2;
			int r2 = x2%2;
			x1 = x1/2;
			x2 = x2/2;
			
			if(r1 != r2) {
				d++;
			}
		}
		
		return d;
	}
	
}
