package Main;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {

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
		
		List<List<TreeNode>> paths = new BinaryTreePaths().getPaths(root);
		
		for(List<TreeNode> path: paths) {
			for(int i= path.size()-1; i>-1; i--) {
				System.out.print(path.get(i).val + " -> ");
			}
			System.out.println();
		}
		
	}
	
	public List<List<TreeNode>> getPaths(TreeNode n) {
		if(n == null) {
			return new ArrayList<List<TreeNode>>();
		}
		
		List<List<TreeNode>> leftPaths = getPaths(n.left);
		List<List<TreeNode>> rightPaths = getPaths(n.right);
		
		if(leftPaths.size() == 0 && rightPaths.size() == 0) {
			List<TreeNode> temp = new ArrayList<>();
			temp.add(n);
			leftPaths.add(temp);
			return leftPaths;
		}
		
		if(leftPaths.size() != 0 && rightPaths.size() == 0) {
			for(List<TreeNode> temp: leftPaths){
				temp.add(n);
			}
			return leftPaths;
		}
		
		if(leftPaths.size() == 0 && rightPaths.size() != 0) {
			for(List<TreeNode> temp: rightPaths){
				temp.add(n);
			}
			return rightPaths;
		}
		
		if(leftPaths.size() != 0 && rightPaths.size() != 0) {
			for(List<TreeNode> temp: leftPaths){
				temp.add(n);
			}
			for(List<TreeNode> temp: rightPaths){
				temp.add(n);
				leftPaths.add(temp);
			}
			return leftPaths;
		}
		
		return null;
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
