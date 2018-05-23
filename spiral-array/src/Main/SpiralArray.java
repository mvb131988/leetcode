package Main;

public class SpiralArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new SpiralArray().spiral(8);
	}
	
	public int[][] spiral(int n) {
		int[][] r = new int[n][n];
		
		Context c = new Context();
		innerSpiral(r, 0, 0, 1, n, c);
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++){
				System.out.print(r[i][j] + "  ");
			}
			System.out.println();
		}
		
		return r;
	}
	
	public void innerSpiral(int[][] r, int xs, int ys, Integer nextValue, int n, Context c) {
		//right
		if((c.y+1) < n && r[c.x][c.y+1] == 0) {
			innerSpiralRight(r, n, c);
		}
		
		//bottom
		if((c.x+1) < n && r[c.x+1][c.y] == 0) {
			innerSpiralBottom(r, n, c);
		}
		
		//left
		if((c.y-1) > -1 && r[c.x][c.y-1] == 0) {
			innerSpiralLeft(r, n, c);
		}
		
		//top
		if((c.x-1) > -1 && r[c.x-1][c.y] == 0) {
			innerSpiralTop(r, n, c);
		}
		
		if(r[c.x][c.y+1] == 0) {
			innerSpiral(r, c.x, c.y+1, c.val, n, c);
		}
	}

	private void innerSpiralRight(int[][] r, int n, Context c) {
		r[c.x][c.y] = c.val;
		if((c.y+1) < n && r[c.x][c.y+1] == 0) {
			c.y++;
			c.val++;
			innerSpiralRight(r, n, c);
		}
	}
	
	private void innerSpiralBottom(int[][] r, int n, Context c) {
		r[c.x][c.y] = c.val;
		if((c.x+1) < n && r[c.x+1][c.y] == 0) {
			c.x++;
			c.val++;
			innerSpiralBottom(r, n, c);
		} 
	}
	
	private void innerSpiralLeft(int[][] r, int n, Context c) {
		r[c.x][c.y] = c.val;
		if((c.y-1) > -1 && r[c.x][c.y-1] == 0) {
			c.y--;
			c.val++;
			innerSpiralLeft(r, n, c);
		}
	}
	
	private void innerSpiralTop(int[][] r, int n, Context c) {
		r[c.x][c.y] = c.val;
		if((c.x-1) > -1 && r[c.x-1][c.y] == 0) {
			c.x--;
			c.val++;
			innerSpiralTop(r, n, c);
		} 
	}
	
	public class Context {
		int val = 1;
		int x;
		int y;
	}
	
}
