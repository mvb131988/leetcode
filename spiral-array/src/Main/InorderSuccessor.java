package Main;

import java.util.Stack;

public class InorderSuccessor {

	public static void main(String[] args) {
		//	       1(20)
		//        /    \
		//       2(8)   3(22)
		//  	/   \   
		//     4(4) 5(12)   
		//	 	  	/   \	
		//        6(10) 7(14)  
		
		TreeNode root = new TreeNode(20);
		
		TreeNode n2 = new TreeNode(8);
		TreeNode n3 = new TreeNode(22);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(12);
		TreeNode n6 = new TreeNode(10);
		TreeNode n7 = new TreeNode(14);
		
		root.left = n2;
		root.right = n3;
		
		n2.left = n4;
		n2.right = n5;
		
		n5.left = n6;
		n5.right = n7;
		
		int target = 14;
		
		System.out.println(new InorderSuccessor().next(root, target).val);
	}
	
	public TreeNode next(TreeNode node, int target) {
		Stack<TreeNode> callStack = get(node, target);
		
		TreeNode startNode = callStack.pop();
		
		//case 1 (exists right subtree)
		if(startNode.right != null) {
			startNode = startNode.right;
			while(startNode.left != null) {
				startNode = startNode.left;
			}
			
			return startNode;
		}
		
		//case 2 (no right subtree)
		while(!callStack.isEmpty()) {
			TreeNode parent = callStack.pop();
			if(parent.val > startNode.val) {
				return parent;
			}
		}
		
		return null;
	}
	
	public Stack<TreeNode> get(TreeNode node, int target) {
		Stack<TreeNode> callStack = new Stack<>();
		
		TreeNode n = node;
		
		while(n != null) {
			callStack.push(n);

			if(n.val == target) {
				break;
			}
			else if(n.val > target) {
				n = n.left;
			} else if(n.val < target) {
				n = n.right;
			}
		}
		
		return n == null ? null : callStack;
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
