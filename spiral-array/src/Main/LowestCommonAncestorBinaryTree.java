package Main;

public class LowestCommonAncestorBinaryTree {

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
		
		System.out.println(new LowestCommonAncestorBinaryTree().lowestCommonAncestor(root, n2, n6).val);
	}
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if((root.val > p.val && root.val < q.val) || (root.val < p.val && root.val > q.val) 
												  ||  root.val == p.val || root.val == q.val) 
		{
			if (exists(root, p) && exists(root, q)) {
				return root;
			} else {
				return null;
			}
		}
		
		if(root.left != null && (root.val > p.val && root.val > q.val)) {
			return lowestCommonAncestor(root.left, p, q);
		}
		
		if(root.right != null && (root.val < p.val && root.val < q.val)) {
			return lowestCommonAncestor(root.right, p, q);
		}
		
		return null;
    }
	
	public boolean exists(TreeNode root, TreeNode n) {
		if(root.val == n.val) {
			return true;
		} else {
			if(root.val > n.val) {
				return exists(root.left, n);
			} else {
				return exists(root.right, n);
			}
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
