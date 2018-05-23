package Main;

import java.util.Stack;

public class BinarySearchTreeIterator {

	public static void main(String[] args) {
		//	       1
		//        / \
		//       2   3
		//  	/ \   \  
		//     4   8   6
		//	  /	      / \
		//	 5	  	11   7	
		//              / \
		//             9   10
		
		TreeNode root = new TreeNode(5);
		
		TreeNode n2 = new TreeNode(3);
		TreeNode n3 = new TreeNode(6);
		TreeNode n4 = new TreeNode(2);
		TreeNode n5 = new TreeNode(1);
		TreeNode n6 = new TreeNode(8);
		TreeNode n7 = new TreeNode(10);
		TreeNode n8 = new TreeNode(4);
		TreeNode n9 = new TreeNode(9);
		TreeNode n10 = new TreeNode(11);
		TreeNode n11 = new TreeNode(7);
		
		root.left = n2;
		root.right = n3;
		
		n2.left = n4;
		n2.right = n8;
		
		n3.right = n6;
		n4.left = n5;
		
		n6.left = n11;
		n6.right = n7;
		
		n7.left = n9;
		n7.right = n10;
		
		BSTIterator iterator = new BSTIterator(root);
		while(iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
	}
	
	public static class BSTIterator {

		private TreeNode root;
		
		private Stack<TreeNode> stack;
		
	    public BSTIterator(TreeNode root) {
	        this.root = root;
	    }

	    /** @return whether we have a next smallest number */
	    public boolean hasNext() {
	        if(stack == null) {
	        	stack = new Stack<>();
	        	TreeNode current = root;
	        	while(current != null) {
	        		stack.push(current);
	        		current = current.left;
	        	}
	        }
	    	return stack.size() != 0;
	    }

	    /** @return the next smallest number */
	    public int next() {
	        TreeNode head = stack.pop();
	        
	        TreeNode current = head.right;
        	while(current != null) {
        		stack.push(current);
        		current = current.left;
        	}
	        
	    	return head.val;
	    }
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
