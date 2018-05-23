package Main;

public class DiameterOfBinaryTree {

	public static void main(String[] args) {
//	       1
//        / \
//       2   3
//     	/ \   \  
//     4   8   6
//		        \
//		  	     7	
		
		TreeNode root = new TreeNode(1);
		
		TreeNode n1 = new TreeNode(2);
		TreeNode n2 = new TreeNode(3);
		TreeNode n3 = new TreeNode(4);
		TreeNode n4 = new TreeNode(5);
		TreeNode n5 = new TreeNode(6);
		TreeNode n6 = new TreeNode(7);
		TreeNode n7 = new TreeNode(8);
		
		root.left = n1;
		root.right = n2;
		
		n1.left = n3;
		n1.right = n7;
		
		
		n5.right = n6;
		
		Context c = new DiameterOfBinaryTree().diameter(root);
		System.out.println(c.diameter);
		System.out.println(c.longestPath);
	}
	
	public Context diameter(TreeNode node) {
		if(node == null) {
			Context c = new Context();
			c.diameter = 0;
			c.longestPath = 0;
			return c;
		}
		
		if(node.left == null && node.right == null) {
			Context c = new Context();
			c.diameter = 1;
			c.longestPath = 1;
			return c;
		}
		
		Context leftContext = diameter(node.left); 
		Context rightContext = diameter(node.right);
		int leftPath = leftContext == null ? 0 : leftContext.longestPath;
		int rightPath = rightContext == null ? 0 : rightContext.longestPath;

		int leftDiameter = leftContext != null ? leftContext.diameter : 0;
		int rightDiameter = rightContext != null ? rightContext.diameter : 0;
		int diameter = leftDiameter > rightDiameter ? leftDiameter : rightDiameter;
		
		Context c1 = new Context();
		c1.diameter = leftPath + rightPath + 1 > diameter ? leftPath + rightPath + 1 : diameter;
		c1.longestPath = leftPath > rightPath ? leftPath + 1 : rightPath + 1;
		
		return c1;
	}
	
	public static class Context {
		int diameter;
		int longestPath;
	}
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

}
