package Main;

public class MergeSortedArray {

	public static void main(String[] args) {
//		int[] a = new int[]{0,  0,  0,  0,  0,  0,  0, 7, 8, 10, 11, 12, 23, 25, 27};
//		int[] b = new int[]{21, 22, 24, 26, 28, 29};
		
		int[] a = new int[] {0, 0, 0, 0, 1, 3, 4, 5};  
		int[] b = new int[] {2, 4, 6, 8};
		
		int[] c = new MergeSortedArray().merge(a, 4, b, b.length);
		System.out.println(c);
	}
	
	public int[] merge(int[] a, int aSize, int[] b, int bSize) {
		int i = a.length - aSize, j = 0, iInsert = 0;
		
		while(j<bSize) {
			if(i < a.length) {
				if(a[i] <= b[j]) {
					a[iInsert] = a[i++];
				} else if(a[i] > b[j]) {
					a[iInsert] = b[j++];
				}
			} else {
				a[iInsert] = b[j++];
			}
			iInsert++;
		}
		
		return a;
	}
	
}
