package Main;

public class Island {
	
	public static void main(String[] args) {
		
//		int[][] map = {{0, 1, 0, 1, 1}, 
//					   {0, 0, 0, 1, 1}, 
//					   {1, 1, 0, 1, 0}};
		
//		int[][] map = {{0, 1, 0, 1, 0, 1}, 
//				   	   {0, 1, 1, 1, 0, 1}, 
//				   	   {1, 1, 0, 0, 0, 0},
//				   	   {1, 1, 0, 1, 1, 1}};
		
		int[][] map = {{0, 0, 1, 0, 0, 0}, 
				   	   {0, 0, 1, 0, 0, 0}, 
				   	   {1, 1, 0, 1, 1, 1},
				   	   {0, 0, 1, 0, 0, 0}};
		
		new Island().count(map);
		
	}

	private void count(int[][] map) {
		Context c = new Context();
		c.x = 0;
		c.y = 0;
		
		int[][] visitedPoints = new int[map.length][map[0].length];
		for(int i=0; i<visitedPoints.length; i++) {
			for(int j=0; j<visitedPoints[0].length; j++) {
				visitedPoints[i][j] = 0;
			}
		}
		c.visitedPoints = visitedPoints;
		
		int counter = countInner(map, c);
		System.out.println(counter);
		
		for(int i=0; i<c.visitedPoints.length; i++) {
			for(int j=0; j<c.visitedPoints[0].length; j++){
				System.out.print(c.visitedPoints[i][j] + "  ");
			}
			System.out.println();
		}
	}
	
	private int countInner(int[][] map, Context c) {
		int counter = 0;
		
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				
				if(map[i][j] == 1 && c.visitedPoints[i][j] == 0) {
					counter++;
					c.x = i;
					c.y = j;
					visit(map, c);
				}
				
				if(map[i][j] == 0) {
					c.visitedPoints[i][j]++;
				}
			}
		}
		
		return counter;
	}

	private void visit(int[][] map, Context c) {
		int x = c.x; 
		int y = c.y;
		
		c.visitedPoints[x][y]++;
		//try to move right
		if((y+1)<map[0].length && map[x][y+1] == 1 && c.visitedPoints[x][y+1] == 0) {
			c.y = y + 1;
			visit(map, c);
		}
		
		//try to move left
		if((y-1)>-1 && map[x][y-1] == 1 && c.visitedPoints[x][y-1] == 0) {
			c.y = y - 1;
			visit(map, c);
		}
		
		//try to move bottom
		if((x+1)<map.length && map[x+1][y] == 1 && c.visitedPoints[x+1][y] == 0) {
			c.x = x + 1;
			visit(map, c);
		}
		
		//try to move top
		if((x-1)>-1 && map[x-1][y] == 1 && c.visitedPoints[x-1][y] == 0) {
			c.x = x - 1;
			visit(map, c);
		}
	}

	public class Context {
		int x;
		int y;
		int[][] visitedPoints;
	}
	
}
