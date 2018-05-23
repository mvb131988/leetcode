package Main;

public class ReverseLinkedList {

	public static void main(String[] args) {
		
		ListNode n = new ListNode(1);
		ListNode temp = n;
		for(int i=2; i<15; i++) {
			temp.next = new ListNode(i);
			temp = temp.next;
		}
		
		ListNode reversedList = new ReverseLinkedList().reverseRecursive(n);
		System.out.println(reversedList);
		
	}
	
	public ListNode reverseRecursive(ListNode n) {
		return reverseRecursiveInternally(null, n);
	}
	
	public ListNode reverseRecursiveInternally(ListNode current, ListNode next) {
		ListNode head = null;
		if(next.next != null) {
			head = reverseRecursiveInternally(next, next.next);
		} else {
			head = next;
		}
		next.next = current;
		
		return head;
	}
	
	public ListNode reverseIterative(ListNode n) {
		ListNode firstNode = n;
		ListNode secondNode = firstNode.next;
		ListNode thirdNode = secondNode.next;
		
		while (thirdNode != null) {
			secondNode.next = firstNode;
			if (firstNode == n) {
				firstNode.next = null;
			}

			firstNode = secondNode;
			secondNode = thirdNode;
			thirdNode = thirdNode.next;
		}
		
		secondNode.next = firstNode;
		return secondNode;
	}
	
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
	
}
