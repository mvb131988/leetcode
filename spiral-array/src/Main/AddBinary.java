package Main;

public class AddBinary {

	public static void main(String[] args){
		String a = "11111";
		String b = "1";
		
		for(int i=0; i<1; i++) {
			a = new AddBinary().addBinary(a, b);
			System.out.println(a);
		}
	}
	
	public String addBinary(String a, String b) {
		int[] n1 = new int[a.length()];
		int[] n2 = new int[b.length()]; 
		
		for (int i = a.length() - 1, j = 0; i > -1; i--, j++) {
			n1[j] = Character.getNumericValue(a.charAt(i));
		}
		
		for (int i = b.length() - 1, j = 0; i > -1; i--, j++) {
			n2[j] = Character.getNumericValue(b.charAt(i));
		}
		
		int size = a.length() > b.length() ? a.length()+1 : b.length()+1;
		int[] n3 = new int[size];
		
		if(n1.length < n2.length){
			int[] temp = n1;
			n1 = n2;
			n2 = temp;
		}
		
		int transfer = 0;
		int digit = 0;
		for(int j=0; j<n2.length; j++) {
			if(n1[j] + n2[j] + transfer < 2) {
				digit = n1[j] + n2[j] + transfer;
				transfer = 0;
			}
			else if(n1[j] + n2[j] + transfer == 2) {
				digit = 0;
				transfer = 1;
			}
			else if(n1[j] + n2[j] + transfer == 3) {
				digit = 1;
				transfer = 1;
			}
			n3[j] = digit;
		}
		
		if(transfer == 1) {
			for(int j = n2.length; j< n1.length; j++) {
				if(n1[j] + transfer < 2) {
					n3[j] = n1[j] + transfer;
					transfer = 0;
				} else if(n1[j] + transfer == 2) {
					n3[j] = 0;
					transfer = 1;
				}
			}
			n3[n3.length-1] = transfer;
		}
		
		StringBuilder sb = new StringBuilder();
		boolean skipZero = true;
		for(int i = n3.length-1; i>-1; i--) {
			if(n3[i] != 0) {
				skipZero = false;
			}
			if(!skipZero) {
				sb.append(n3[i]);
			}
		}
		
		return sb.toString();
    }
	
}
