package Main;

public class HistogramLargestRectangle {

	public static void main(String[] args) {
		
		int[] histogram = {1,3,2};
		
		int maxSquare = new HistogramLargestRectangle().maxSquare(histogram);
		
		System.out.println(maxSquare);
		
	}
	
	public int maxSquare(int[] histogram) {
		Stack pos = new Stack();
		Stack val = new Stack();
		int max = 0;
		
		for(int i=0; i<histogram.length; i++) {
			if(val.size() == 0 || val.peek() < histogram[i]) {
				pos.push(i);
				val.push(histogram[i]);
			} else {
				int p = -1;
				int v = -1;
				
				while(val.size()>0 && histogram[i] < val.peek()) {
					p = pos.pop();
					v = val.pop();
					int s = v*(i-p);
					max = s>max ? s : max;
				}
				
				if(p != -1) {
					pos.push(p);
					val.push(histogram[i]);
				}
			}
		}
		
		while(pos.size() >0) {
			int p = pos.pop();
			int v = val.pop();
			int s = v*(histogram.length-p);
			max = s>max ? s : max;
		}
		
		return max;
	}
	
	public class Stack {
		
		Node head;
		
		public void push(int val) {
			Node n = new Node();
			n.val = val;
			if(head == null) {
				head = n;
			} else {
				n.next = head;
				head = n;
			}
			
		}
		
		public int pop() {
			if(head == null) {
				return -1;
			}
			
			int val = head.val;
			head = head.next;
			return val;
		}
		
		public int peek() {
			if(head == null) {
				return -1;
			}
			
			int val = head.val;
			return val;
		}
		
		public int size() {
			if(head == null) {
				return 0;
			}
			
			int size = 1;
			Node n = head;
			while(n.next != null) {
				size++;
				n = n.next;
			}
			
			return size;
		}

	}
	
	public class Node {
		int val;
		Node next;				
	}
	
}
