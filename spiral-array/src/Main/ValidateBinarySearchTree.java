package Main;

public class ValidateBinarySearchTree {

	public static void main(String[] args) {
		
		Node n4 = new Node(5);
		
		Node n5 = new Node(10);
//		Node n6 = new Node(6);
//		Node n7 = new Node(12);
		
		Node n1 = new Node(3);
		Node n2 = new Node(1);
		Node n3 = new Node(4);
		
		n4.left = n1;
		n4.right = n5;
		n1.left = n2;
		n1.right = n3;
//		n5.left = n6;
//		n5.right = n7;
//		
		System.out.println(new ValidateBinarySearchTree().validate(n4).isValid);
	}
	
	public Context validate(Node n) {
		if(n.left == null && n.right == null) {
			Context c = new Context();
			c.max = n.value;
			c.min = n.value;
			c.isValid = true;
			return c;
		}
		
		Context leftContext = validate(n.left); 
		Context	rightContext = validate(n.right);
		
		Context c = new Context();
		c.isValid = false;
		if(leftContext.isValid && rightContext.isValid) {
			if(leftContext.max < n.value && rightContext.min > n.value) {
				c.isValid = true;
				c.min = leftContext.min;
				c.max = rightContext.max;
			}
		}
		
		return c;
	}
	
	public Integer validateLeft(Node n) {
		return null;
	}
	
	public Integer validateRight(Node r) {
		return null;
	}
	
	public static class Context {
		Integer max;
		Integer min;
		boolean isValid;
	}
	
	public static class Node {
		
		Node left;
		Node right;
		Node parent;
		int value;
		
		public Node(int value) {
			this.value = value;
		}
		
	}
	
}
