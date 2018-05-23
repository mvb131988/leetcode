package Main;

import java.util.HashMap;
import java.util.Map;

public class LruCacheProblem {

	public static void main(String[] args) {
		LruCache cache = new LruCache(2);
//		cache.put(1, 1);
//		cache.put(2, 2);
//		System.out.println(cache.get(1)); // returns 1
//		cache.put(3, 3); // evicts key 2
//		System.out.println(cache.get(2)); // returns -1 (not found)
//		cache.put(4, 4); // evicts key 1
//		System.out.println(cache.get(1)); // returns -1 (not found)
//		System.out.println(cache.get(3)); // returns 3
//		System.out.println(cache.get(4)); // returns 4

		 System.out.println("=========================");
		 System.out.println("7 0 1 2 0 3 0 4 2 3 0 3 2");
		 System.out.println("=========================");
		 cache = new LruCache(4);
		 cache.put(7,7);
		 cache.put(0,0);
		 cache.put(1,1);
		 cache.put(2,2);
		 cache.put(0,0);
		 cache.put(3,3);
		 cache.put(0,0);
		 cache.put(4,4);
		 cache.put(2,2);cache.put(3,3);cache.put(0,0);cache.put(3,3);
		 cache.put(2,2);
		
		
//		cache.put(1, 1);
//		cache.put(2, 2);
//		cache.put(1, 3);
//		System.out.println(cache.get(2));
//		cache.put(4, 4);
//		System.out.println(cache.get(1));
//		
//		System.out.println(cache.get(1)); // returns 1
//		cache.put(3, 3); // evicts key 2
//		System.out.println(cache.get(2)); // returns -1 (not found)
//		cache.put(4, 4); // evicts key 1
//		System.out.println(cache.get(1)); // returns -1 (not found)
//		System.out.println(cache.get(3)); // returns 3
//		System.out.println(cache.get(4)); // returns 4
	}

	public static class LruCache {
		private Node head;
		private Node tail;
		private Map<Integer, Node> map;
		
		private int capacity;

		public LruCache(int capacity) {
			this.capacity = capacity;
			map = new HashMap<>();
		}
		
		public int get(int key) {
			Node n = map.get(key);
			
			if(n != null) {
				update(key, n.value);
			}
				
			return n == null ? -1 : n.value;
		}
		
		public void put(int key, int value) {
			// case1: initial add
			if(map.size() < capacity) {
				add(key, value);
				return;
			}
			
			//case2: update
			if(map.get(key) != null) {
				update(key, value);
				return;
			}
			
			//case3: add + evict
			evict();
			add(key,value);
		}
		
		public void evict() {
			System.out.println("EVICT: " + tail.value);
			
			Node n = tail;
			tail = tail.left;
			tail.right = null;
			n.left = null;
			map.remove(n.key);
			n = null;
		}
		
		public void update(int key, int value) {
			//case 1: key is head, no actions required
			Node n = map.get(key);
			n.value = value;
			if(head == n) {
				return;
			}
			
			//case2: key is tail
			if(tail == n) {
				tail = tail.left;
				tail.right = null;

				n.left = null;
				n.right = head;
				
				head.left = n;
				head = n;
				return;
			}
			
			//case3: key is in the middle
			n.left.right = n.right;
			n.right.left = n.left;
			n.left = null;
			n.right = head;
			head.left = n;
			head = n;
		}
		
		public void add(int key, int value) {
			Node n = new Node(key, value);
			map.put(key, n);
			
			if(head == null) {
				head = n;
				tail = n;
			} else {
				n.right = head;
				head.left = n;
				head = n;
			}
		}
	}
	
	public static class Node {
		Node left;
		Node right;
		int key;
		int value;
		
		Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}
}
