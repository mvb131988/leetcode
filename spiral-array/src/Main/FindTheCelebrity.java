package Main;

public class FindTheCelebrity {

	public static void main(String[] args){
		int[] input = new int[] {1,2,3,4,5,6};
		
		FindTheCelebrity ftc = new FindTheCelebrity();

		int left = 0;
		int right = input.length-1;
		while(left<right) {
			if (ftc.knows(input[left], input[right])) {
				left++;
			} else{
				right--;
			}
		}
		
		int id = right;
		for(int i=0; i<input.length; i++) {
			if(i != right) {
				if(!ftc.knows(input[i], input[right])) {
					id = -1;
				}
				if(ftc.knows(input[right], input[i])){
					id = -1;
				}
			}
		}
		
		System.out.println("Candidate " + right);
		System.out.println(id == -1 ? -1 : input[id]);
	}
	
	public boolean knows(int a, int b) {
		int[][] map = new int[][] {{0,1,0,1,0,1},
			   					   {0,0,0,0,0,0},
			   					   {0,1,0,0,0,1},
			   					   {0,1,0,1,0,1},
			   					   {0,1,0,0,1,0},
			   					   {0,1,0,0,0,0}};
		return map[a-1][b-1] == 1;
	}
	
}
