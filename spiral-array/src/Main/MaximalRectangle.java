package Main;

public class MaximalRectangle {

	public static void main(String[] args) {
		char[][] matrix = new char[][]
		{
				{'1','0','1','0','0'},
				{'1','0','1','1','1'},
				{'1','1','1','1','1'},
				{'1','0','0','1','0'}
		};
		
//		char[][] matrix = new char[][]
//		{
//				{'1','1','1','1','0','0','0','1'},
//				{'1','1','1','1','0','0','0','1'},
//				{'1','1','1','0','1','1','1','1'},
//				{'1','1','1','0','1','1','0','1'},
//				{'0','0','0','0','1','1','1','1'},
//				{'0','0','0','0','1','1','1','1'}
//		};
		
		MaximalRectangle inv = new MaximalRectangle();
		System.out.println(inv.maximalRectangle(matrix));
	}
	
	public int maximalRectangle(char[][] matrix) {
		int maxArea = 0;
		
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix[0].length; j++) {
				if (matrix[i][j] == '1'){
					//find square 2x2
					//right-top
					if( i > 0 && j<matrix.length-1 && matrix[i-1][j+1] == '1' && matrix[i-1][j] == '1' && matrix[i][j+1] == '1') {
						int area = maxArea(matrix, i, j, i-1, j+1, 4);						
						maxArea = area > maxArea ? area : maxArea;
					}
				}
			}
		}
		
		return maxArea;
    }
	
	public int maxArea(char[][] matrix, int x1, int y1, int x2, int y2, int area) {
		boolean extendable = true;
		int maxArea = 0;
		
		//extend right border
		if(y2+1 < matrix[0].length) {
			for(int i=x2; i<=x1; i++) {
				if(matrix[i][y2+1] == '0') {
					extendable = false;
				}
			}
			
			if(extendable) {
				int maxArea1 = maxArea(matrix, x1, y1, x2, y2+1, area+(x1-x2+1));
				maxArea = maxArea < maxArea1 ? maxArea1 : maxArea; 
			}
		}
		
		extendable = true;
		//extend left border
		if(y1-1 > -1) {
			for(int i=x2; i<=x1; i++) {
				if(matrix[i][y1-1] == '0') {
					extendable = false;
				}
			}
			
			if(extendable) {
				int maxArea1 = maxArea(matrix, x1, y1-1, x2, y2, area+(x1-x2+1));
				maxArea = maxArea < maxArea1 ? maxArea1 : maxArea;
			}
		}
		
		extendable = true;
		//extend top border
		if(x2-1 > -1) {
			for(int i=y1; i<=y2; i++) {
				if(matrix[x2-1][i] == '0') {
					extendable = false;
				}
			}
			
			if(extendable) {
				int maxArea1 = maxArea(matrix, x1, y1, x2-1, y2, area+(y2-y1+1));
				maxArea = maxArea < maxArea1 ? maxArea1 : maxArea;
			}
		}
		
		extendable = true;
		//extend bottom border
		if(x1+1 < matrix.length) {
			for(int i=y1; i<=y2; i++) {
				if(matrix[x1+1][i] == '0') {
					extendable = false;
				}
			}
			
			if(extendable) {
				int maxArea1 = maxArea(matrix, x1+1, y1, x2, y2, area+(y2-y1+1));
				maxArea = maxArea < maxArea1 ? maxArea1 : maxArea;
			}
		}
		
		return maxArea > area ? maxArea : area;
	}
	
}
