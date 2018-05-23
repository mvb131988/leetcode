package Main;

public class SearchInRotatedSortedArray {
	
	public static void main(String[] args) {
		int target = 1;
		
		int[] input = new int[] {3, 4, 5, 1, 2};
												
		new SearchInRotatedSortedArray().search(input, target);
	}

	public boolean search(int[] input, int target) {
		int pivot = searchPivot(input, 0, input.length - 1);
		int iTarget = -1;
		
		//left subarray
		if(target >= input[0] && target <= input[pivot]) {
			iTarget = search(input, 0, pivot, target);
		}
		
		if(target >= input[pivot+1] && target <= input[input.length - 1]) {
			iTarget = search(input, pivot+1, input.length - 1, target);
		}
		
		return iTarget == -1;
	}
	
	public int search(int input[], int start, int end, int target) {
		if(end - start == 0 && input[start] != target) {
			return -1;
		}
		
		int middle = (end+start)/2;
		System.out.println("[middle = " + middle + "]");
		
		if(input[middle] == target) {
			return middle;
		}
		
		if (input[middle] < target) {
			return search(input, middle+1, end, target);
		}
		
		if (input[middle] > target) {
			return search(input, start, middle-1, target);
		}
		
		return -1;
	}
	
	public int searchPivot(int[] input, int start, int end) {
		System.out.println("[" + start + "," + end + "]");
		
		int middle = (end+start)/2;
		
		if(middle + 1 <= input.length - 1 && input[middle] > input[middle + 1]) {
			return middle;
		}
		
		if(middle - 1 >=0 && input[middle] < input[middle - 1]) {
			return middle - 1;
		}
		
		if(input[middle] < input[input.length - 1]){
			return searchPivot(input, 0, middle-1);
		} else {
			return searchPivot(input, middle+1, input.length-1);
		}
	}
	
}
