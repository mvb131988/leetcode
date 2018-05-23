package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryTreeVerticalOrderTraversal {

	public static void main(String[] args) {
		//	         1
		//        /     \
		//       2       3
		//  	/ \     / \  
		//     4   8  12   6
		//	  /	          / \
		//	 5	  	    11   7	
		//                  / \
		//                 9   10
		
		TreeNode root = new TreeNode(1);
		
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		TreeNode n7 = new TreeNode(7);
		TreeNode n8 = new TreeNode(8);
		TreeNode n9 = new TreeNode(9);
		TreeNode n10 = new TreeNode(10);
		TreeNode n11 = new TreeNode(11);
		TreeNode n12 = new TreeNode(12);
		
		root.left = n2;
		root.right = n3;
		
		n2.left = n4;
		n2.right = n8;
		
		n3.right = n6;
		n3.left = n12;
		
		n4.left = n5;
		
		n6.left = n11;
		n6.right = n7;
		
		n7.left = n9;
		n7.right = n10;
		
		Context c = new BinaryTreeVerticalOrderTraversal().traverse(root);
		
		for(int i=c.minLevel; i<=c.maxLevel; i++) {
			List<TreeNode> level = c.verticalOrder.get(i);
			System.out.print("Level[" + i + "]: ");
			for(TreeNode node: level) {
				System.out.print(node.val + " ");
			}
			System.out.println();
		}
	}
	
	public Context traverse(TreeNode root) {
		Map<Integer, List<TreeNode>> verticalOrder = new HashMap<>();
		Context c = new Context();
		
		innerTraversal(root, verticalOrder, 0, c);
		c.verticalOrder = verticalOrder;
		
		return c;
	}
	
	private void innerTraversal(TreeNode node, Map<Integer, List<TreeNode>> verticalOrder, int offset, Context c) {
		List<TreeNode> level = verticalOrder.get(offset);
		if(level == null) {
			level = new ArrayList<>();
		}
		
		level.add(node);
		verticalOrder.put(offset, level);
		c.minLevel = c.minLevel > offset ? offset : c.minLevel;
		c.maxLevel = c.maxLevel < offset ? offset : c.maxLevel;
		
		if(node.left != null) {
			innerTraversal(node.left, verticalOrder, offset-1, c);
		}
		
		if(node.right != null) {
			innerTraversal(node.right, verticalOrder, offset+1, c);
		}
	}
	
	class Context {
		Map<Integer, List<TreeNode>> verticalOrder;
		int minLevel = Integer.MAX_VALUE;
		int maxLevel = Integer.MIN_VALUE;
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
