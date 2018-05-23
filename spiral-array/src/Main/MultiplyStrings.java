package Main;

public class MultiplyStrings {

	public static void main(String[] args) {
		
		String a = "9923234245325054654645645645645654654654654654654654654654654628709780799898123456789098754";
		String b = "12343423423423423423";
		
		System.out.println(new MultiplyStrings().multiply(a, b));
		
	}
	
	public String multiply(String a, String b) {
		
		int[] n1 = new int[(a.toCharArray().length)];
		int[] n2 = new int[(b.toCharArray().length)];
		
		int[] n3 = new int[n1.length + n2.length + 1];

		for(int i=n1.length - 1, j=0; i>-1; i--, j++) {
			n1[j] = Character.getNumericValue(a.charAt(i));
		}
		
		for(int i=n2.length - 1, j=0; i>-1; i--, j++) {
			n2[j] = Character.getNumericValue(b.charAt(i));
		}
		
		for(int j=0; j<n2.length; j++) {
			
			// multiply
			int high = 0;
			int[] temp = new int[n1.length + 1];
			for(int i=0; i<n1.length; i++) {
				int low = (n2[j] * n1[i] + high) % 10;
				
				temp[i] += low%10;
				
				high = (n2[j] * n1[i] + high) / 10;
				
			}
			temp[n1.length] += high;
			
			// sum with n3
			int transfer = 0;
			int nextTransfer = 0;
			for(int i=0; i<temp.length; i++) {
				nextTransfer = (n3[i+j]+ temp[i] + transfer) / 10;
				n3[i+j] = (n3[i+j]+ temp[i] + transfer) % 10;
				transfer = nextTransfer;
			}
			n3[temp.length + 1] += transfer;
			
		}
		
		String output = "";
		
		int i = n3[n3.length-1] == 0 ? n3.length-2 : n3.length-1;
		for(int ii=i; ii>-1; ii--) {
			output += n3[ii];
		}
		
		return output;
	}
	
}
