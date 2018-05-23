package Main;

public class WordSearch {

	public static void main(String[] args) {
		char[][] input = { { 'A', 'B', 'C', 'E' }, 
						   { 'S', 'C', 'C', 'S' }, 
						   { 'A', 'D', 'E', 'E' } };
		
		String target = "CCDEESECBA";
		
		System.out.println(new WordSearch().search(input, target));
	}

	public boolean search(char[][] input, String target) {
		boolean r = false;
		
		for(int i=0; i<input.length; i++) {
			for(int j=0; j<input[0].length; j++) {
				if(target.charAt(0) == input[i][j]) {
					r = searchInternally(input, i, j, target, 0, getVisited(input));
				}
				
				if(r) return r;
			}
		}
		
		return r;
	}
	
	public boolean searchInternally(char[][] input, int x, int y, String target, int pos, int[][] visited) {
		
		visited[x][y] = 1;
		
		//stop condition
		if(pos == target.length()-1) {
			return true;
		}
		
		boolean r = false;
		
		// move left
		if(y>0 && visited[x][y-1] == 0 && target.charAt(pos+1) == input[x][y-1]){
			r = searchInternally(input, x, y-1, target, pos+1, visited);
			if(r) return r;
		}
		
		// move right
		if(y<input[0].length-1 && visited[x][y+1] == 0 && target.charAt(pos+1) == input[x][y+1]){
			r = searchInternally(input, x, y+1, target, pos+1, visited);
			if(r) return r;
		}
		
		// move top
		if(x>0 && visited[x-1][y] == 0 && target.charAt(pos+1) == input[x-1][y]){
			r = searchInternally(input, x-1, y, target, pos+1, visited);
			if(r) return r;
		}
		
		// move bottom
		if(x<input.length-1 && visited[x+1][y] == 0 && target.charAt(pos+1) == input[x+1][y]){
			r = searchInternally(input, x+1, y, target, pos+1, visited);
			if(r) return r;
		}
		
		//unmarked if from this point nothing is reachable
		visited[x][y] = 0;
		
		return r;
	}
	
	public int[][] getVisited(char[][] input) {
		int[][] visited = new int[input.length][input[0].length];
		
		for(int i=0; i<visited.length; i++) {
			for(int j=0; j<visited[0].length; j++) {
				visited[i][j] = 0;
			}
		}
		
		return visited;
	}
	
}
 