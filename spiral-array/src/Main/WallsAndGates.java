package Main;

public class WallsAndGates {

	private static int counter = 0;
	
	public static void main(String[] args) {
		
		int[][] maze = new int[][] {{Integer.MAX_VALUE, -1, 			   0, 				  Integer.MAX_VALUE},
									{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1			   },
									{Integer.MAX_VALUE, -1,				   Integer.MAX_VALUE, -1			   },
									{0, 				-1,				   Integer.MAX_VALUE, Integer.MAX_VALUE}};
		
		new WallsAndGates().distance(maze);
		
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				System.out.print(maze[i][j] + "   ");
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println(counter);
	}
	
	public void distance(int[][] maze) {
		int[][] visited = new int[maze.length][maze[0].length];
		
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if(maze[i][j] == 0) {
					distanceInternally(maze, i, j, 0, visited);
				}
			}
		}
	}
	
	public void distanceInternally(int[][] maze, int x, int y, int distance, int[][] visited) {
		counter++;
		
		visited[x][y] = 1;
		
		if(maze[x][y] > distance && distance > 0){
			maze[x][y] = distance;
		}
		
		if(y-1 >= 0 && visited[x][y-1] == 0 && maze[x][y-1] != 0 && maze[x][y-1] != -1 
				&& maze[x][y-1] > distance+1
		){
			distanceInternally(maze, x, y-1, distance+1, visited);
		}	
		
		if(y+1 < maze.length && visited[x][y+1] == 0 && maze[x][y+1] != 0 && maze[x][y+1] != -1 
				&& maze[x][y+1] > distance+1
		){
			distanceInternally(maze, x, y+1, distance+1, visited);
		}
		
		if(x+1 < maze[0].length && visited[x+1][y] == 0 && maze[x+1][y] != 0 && maze[x+1][y] != -1 
				&& maze[x+1][y] > distance+1
		)
		{
			distanceInternally(maze, x+1, y, distance+1, visited);
		}
		
		if(x-1 >= 0 && visited[x-1][y] == 0 && maze[x-1][y] != 0 && maze[x-1][y] != -1 
				&& maze[x-1][y] > distance+1
		){
			distanceInternally(maze, x-1, y, distance+1, visited);
		}
		
		visited[x][y] = 0;
	}
	
}

