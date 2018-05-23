package Main;

import java.util.HashMap;
import java.util.Map;

public class SequenceDepth {

	private static Map<Character, Character> map = new HashMap<>();
	private static Map<Character, Integer> counter = new HashMap<>();
	private static Map<Character, Integer> max = new HashMap<>();
	
	static {
		map.put(')', '(');
		map.put(']', '[');
		map.put('}', '{');
		
		counter.put('(', 0);
		counter.put('[', 0);
		counter.put('{', 0);
		
		max.put('(', 0);
		max.put('[', 0);
		max.put('{', 0);
	}
	
	public static void main(String[] args) {
	
		String input = "[()]({}";
		
		boolean isValid = new SequenceDepth().maxDepth(input);
		System.out.println(isValid);
	}

	private boolean maxDepth(String input) {
		boolean isValid = true;
		
		Stack s = new Stack();
		char[] in = input.toCharArray();
		
		for(char c: in) {
			if(c == '(' || c == '[' || c == '{') {
				s.push(c);
				counter.put(c, counter.get(c) + 1);
			}
			if(c == ')' || c == ']' || c == '}') {
				if(counter.get(map.get(c)) > 0) {
					max.put(map.get(c), counter.get(map.get(c)) > max.get(map.get(c)) ? counter.get(map.get(c)) : max.get(map.get(c)));
					counter.put(map.get(c), 0);
				}
				
				char head = s.pop();
				if(head != map.get(c)) {
					isValid = false;
					return isValid;
				}
			}
		}
		
		if(s.size() != 0) {
			isValid = false;
		}
		
		return isValid;
	}
	
	public class Stack {
		
		private Node head;
		
		public void push(char c) {
			Node n = new Node();
			n.val = c;
			
			if(head == null) {
				head = n;
			} else {
				Node temp = head;
				head = n;
				n.next = temp;
			}
		}
		
		public char pop() {
			char c = 'n';
			
			if(head == null) {
				return c;
			}
			if(head.next == null) {
				c = head.val;
				head = null;
				return c;
			}
			else {
				c = head.val;
				head = head.next;
			}
			
			return c;
		}
		
		public int size() {
			int size = 0;
			Node temp = head;

			while(temp != null) {
				size++;
				temp = temp.next;
			}
			
			return size;
		}
		
	}
	
	public class Node {
		char val;
		Node next;
	}
	
}
