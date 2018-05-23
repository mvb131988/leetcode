package Main;

public class MinPath {

	public static void main(String[] args) {
		
		int xStart = 3, yStart = 4;
		
		int xEnd = 3, yEnd = 1;
		
		int[][] map = {{0, 1, 1, 0, 0}, 
			   	   	   {0, 0, 1, 0, 0}, 
			   	   	   {0, 1, 1, 1, 1},
			   	   	   {1, 1, 0, 0, 1},
			   	   	   {1, 1, 1, 1, 1}};
		
		int[][] visitMap = {{0,0,0,0,0},
							{0,0,0,0,0},
							{0,0,0,0,0},
							{0,0,0,0,0},
							{0,0,0,0,0}};
		
		visitMap[xStart][yStart] = 1;
		
		Context c = new MinPath().path(map, visitMap, xStart, yStart, xEnd, yEnd);
		
		System.out.println(c.length);
		for(Point p: c.path) {
			System.out.print("[" + p.x + "," + p.y + "] <- ");
		}
		System.out.print("[" + xStart + "," + yStart + "]");
		
	}
	
	public Context path(int[][] map, int[][] visitMap, int xStart, int yStart, int xEnd, int yEnd) {
		
		visitMap[xStart][yStart] = 1;

		if (xStart == xEnd && yStart == yEnd) {
			Context c = new Context();
			return c;
		}
		
		Context c = null;
		Context c1 = null;
		Context c2 = null;
		Context c3 = null;
		Context c4 = null;
		
		// move left
		if (yStart - 1 > -1 && map[xStart][yStart - 1] == 1 && visitMap[xStart][yStart - 1] == 0) {
			c1 = path(map, copy(visitMap), xStart, yStart - 1, xEnd, yEnd);
			if (c1 != null) {
				c1.length++;
				c1.path = add(c1.path, new Point(xStart, yStart - 1));
			}
			c = c1;
		}
		
		// move right
		if (yStart + 1 < map[0].length && map[xStart][yStart + 1] == 1 && visitMap[xStart][yStart + 1] == 0) {
			c2 = path(map, copy(visitMap), xStart, yStart + 1, xEnd, yEnd);
			if (c2 != null) {
				c2.length++;
				c2.path = add(c2.path, new Point(xStart, yStart + 1));
				if(c == null || (c != null && c2.length < c.length)) {
					c = c2;
				}
			}
		}
		
		// move upward
		if (xStart - 1 > -1 && map[xStart - 1][yStart] == 1 && visitMap[xStart - 1][yStart] == 0) {
			c3 = path(map, copy(visitMap), xStart - 1, yStart, xEnd, yEnd);
			if (c3 != null) {
				c3.length++;
				c3.path = add(c3.path, new Point(xStart - 1, yStart));
				if(c == null || (c != null && c3.length < c.length)) {
					c = c3;
				}
			}
		}
		
		
		//move downward
		if (xStart + 1 < map.length && map[xStart + 1][yStart] == 1 && visitMap[xStart + 1][yStart] == 0) {
			c4 = path(map, copy(visitMap), xStart + 1, yStart, xEnd, yEnd);
			if (c4.length != 0) {
				c4.length++;
				c4.path = add(c4.path, new Point(xStart + 1, yStart));
				if(c == null || (c != null && c4.length < c.length)) {
					c = c4;
				}
			}
		}
		
		return c;
	}
	
	public int[][] copy(int[][] original) {
		int[][] c = new int[original.length][original[0].length];
		
		for(int i=0; i<original.length; i++)
			for(int j=0; j<original[0].length; j++)
				c[i][j] = original[i][j];
		
		return c;
	}
	
	public Point[] add(Point[] original, Point el) {
		Point[] c = new Point[original.length + 1];
		
		for (int i = 0; i < original.length; i++) {
			c[i] = original[i];
		}
		c[original.length] = el;
		
		return c;
	}
	
	class Point {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	class Context {
		Point[] path = new Point[0];
		int length = 1;
	}
	
}
