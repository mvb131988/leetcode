package Main;

public class MergeKSortedList {

	public static void main(String[] args) {
		// 1, 5, 10, 13, 15, 20
		ListNode head1 = new ListNode(1);
		head1.next = new ListNode(5);
		head1.next.next = new ListNode(10);
		head1.next.next.next = new ListNode(13);
		head1.next.next.next.next = new ListNode(15);
		head1.next.next.next.next.next = new ListNode(20);
		
		// 4, 6, 7, 17
		ListNode head2 = new ListNode(4);
		head2.next = new ListNode(6);
		head2.next.next = new ListNode(7);
		head2.next.next.next = new ListNode(17);
		
		// 3, 4, 5, 8, 12
		ListNode head3 = new ListNode(3);
		head3.next = new ListNode(4);
		head3.next.next = new ListNode(5);
		head3.next.next.next = new ListNode(8);
		head3.next.next.next.next = new ListNode(12);
		
		ListNode[] list = new ListNode[3];
		list[0] = head1;
		list[1] = head2;
		list[2] = head3;
		
		ListNode result = new MergeKSortedList().mergeKLists(list);
		
		while(result != null) {
			System.out.print(result.val + " ");
			result = result.next;
		}
	}
	
	public ListNode mergeKLists(ListNode[] lists) {
		ListNode[] indexes = new ListNode[lists.length];
		
		for(int i=0; i<lists.length; i++) {
			indexes[i] = lists[i];
		}
		
		ListNode result = null;
		
		int countNull = 0;
		while(true) {
			
			int minIndex = -1;
			ListNode minValue = new ListNode(Integer.MAX_VALUE);
			for(int i=0; i<lists.length; i++) {
				if(indexes[i] == null) {
					countNull++;
				} else {
					if(indexes[i].val < minValue.val) {
						minValue = indexes[i];
						minIndex = i;
					}
				}
			}

			if(countNull == indexes.length) {
				break;
			} else {
				countNull = 0;
			}
			
			///
			if(result == null) {
				result = new ListNode(minValue.val);
			} else {
				ListNode current = result;
				while(current.next != null) {
					current = current.next;
				}
				current.next = new ListNode(minValue.val); 
			}
			///
			
			indexes[minIndex] = indexes[minIndex].next; 
			
		}
		
		return result;
	}
	
	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
}
