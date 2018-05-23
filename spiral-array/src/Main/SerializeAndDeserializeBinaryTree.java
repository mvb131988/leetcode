package Main;

import java.util.Arrays;

public class SerializeAndDeserializeBinaryTree {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
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
		
		SerializeAndDeserializeBinaryTree codec = new SerializeAndDeserializeBinaryTree();
		String sTree = codec.serialize(root);
		codec.deserialize(sTree);
	}

	  // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        int length = longestBranch(root);
    	int size = calculate(length);
        int[] dTree = new int[size+1];
    	
        serializeInternally(root, 1, dTree);
        
    	return Arrays.toString(dTree);
    }
    
    private void serializeInternally(TreeNode current, int currentIndex, int[] dTree) {
        dTree[currentIndex] = current.val;
        
        if(current.left != null) {
        	serializeInternally(current.left, currentIndex*2, dTree);
        }
        if(current.right != null) {
        	serializeInternally(current.right, currentIndex*2+1, dTree);
        }
    }

    public int calculate(int length){
    	int size = 1;
    	for(int i=1; i<length; i++) {
    		size += Math.pow(2, i);
    	}
    	
    	return size;
    }
    
    public int longestBranch(TreeNode node) {
    	if(node == null) {
    		return 0;
    	}
    	
    	int leftLength = longestBranch(node.left);
    	int rightLength = longestBranch(node.right);
    	
    	int length = 0;
    	if(leftLength > rightLength){
    		length = leftLength + 1;
    	} else {
    		length = rightLength + 1;
    	}
    	
    	return length;
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
    	
    	int[] arData = fromStrtoArr(data);
    	
    	if(arData[1] == 0) {
    		return null;
    	}
    	
    	TreeNode root = new TreeNode(arData[1]);

    	deserializeInternally(root, 1, arData);
    	
    	return root;
    }
    
    public void deserializeInternally(TreeNode current, int currentIndex, int[] arData) {
    	if(currentIndex*2 < arData.length && arData[currentIndex*2] != 0) {
    		TreeNode left = new TreeNode(arData[currentIndex*2]);
    		current.left = left;
    		deserializeInternally(left, currentIndex*2, arData);
    	}
    	
    	if(currentIndex*2 < arData.length && arData[currentIndex*2 + 1] != 0) {
    		TreeNode right = new TreeNode(arData[currentIndex*2+1]);
    		current.right = right;
    		deserializeInternally(right, currentIndex*2 + 1, arData);
    	}
    }
    
    private int[] fromStrtoArr(String data) {
    	int size = 1;
    	for(int i=0; i<data.length(); i++) {
    		if(data.charAt(i) == ',') {
    			size++;
    		}
    	}
    	
    	int[] arr = new int[size];
    	int i=-1;
    	int j=0;
    	String temp = "";
    	while(i<data.length()-1) {
    		i++;
    		
    		if(data.charAt(i) != '[' && data.charAt(i) != ']' && data.charAt(i) != ',' && data.charAt(i) != ' ') {
    			temp += data.charAt(i);
    			continue;
    		}
    		
    		if(temp != "") {
    			arr[j] = Integer.parseInt(temp);
    			temp = "";
    			j++;
    		}
    	}
    	
    	return arr;
    }
	
}
